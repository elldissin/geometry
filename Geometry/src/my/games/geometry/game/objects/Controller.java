package my.games.geometry.game.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Timer;

import my.games.geometry.networking.PlayerInput;
import my.games.geometry.networking.ServerCommunicator;

public class Controller implements KeyListener {
	ServerCommunicator comm;
	private final Set<Integer> pressed = new HashSet<Integer>();
	protected ArrayList<Controllable> controlledArray;

	public Controller(final ServerCommunicator comm, final int clientID) {
		controlledArray = new ArrayList<Controllable>();
		this.comm = comm;
		// Check every 100ms if there's keys pressed
		// (This is the Swing Timer they talk about)
		new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!pressed.isEmpty()) {
					for (Integer pressedCode : pressed) {
						comm.sendInput(new PlayerInput(clientID, pressedCode));
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
		pressed.remove(code);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void takeControlOf(Controllable obj) {
		controlledArray.add(obj);
	}

	public void stopControlOf(Controllable obj) {
		controlledArray.remove(obj);
	}

}
