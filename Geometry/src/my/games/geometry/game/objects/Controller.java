package my.games.geometry.game.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Timer;

import my.games.geometry.networking.PlayerInput;
import my.games.geometry.networking.ServerCommunicator;

public class Controller implements KeyListener {
	ServerCommunicator comm;
	private final Set<Integer> pressed = new HashSet<Integer>();
	private final Set<Integer> released = new HashSet<Integer>();

	public Controller(final ServerCommunicator comm, final int clientID) {
		this.comm = comm;
		new Timer(20, new ActionListener() {
			@Override // LATER add filtering for repeating input?
			public void actionPerformed(ActionEvent arg0) {
				if (!pressed.isEmpty()) {
					for (Iterator<Integer> i = pressed.iterator(); i.hasNext();) {
						Integer pressedCode = i.next();
						comm.sendInput(new PlayerInput(clientID, pressedCode, true));
						System.out.println("sending keyPress event to server");
						i.remove();
					}
				}
				if (!released.isEmpty()) {
					for (Iterator<Integer> i = released.iterator(); i.hasNext();) {
						Integer releasedCode = i.next();
						comm.sendInput(new PlayerInput(clientID, releasedCode, false));
						i.remove();
						pressed.remove(releasedCode);
						System.out.println("sending release event to server");
					}
				}
			}
		}).start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		pressed.add(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		released.add(code);
		System.out.println("keyRelesed");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
