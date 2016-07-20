package nubiki.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Timer;

import nubiki.events.EventManager;
import nubiki.events.GameEvent;
import nubiki.events.MoveEvent;
import nubiki.events.StopEvent;
import nubiki.events.TurnEventCW;
import nubiki.networking.NetworkMessage;
import nubiki.networking.ServerCommunicator;

public class Controller implements KeyListener {

	// ServerCommunicator comm;
	EventManager manager;
	ServerCommunicator comm;
	private final Set<Integer> pressed = new HashSet<Integer>();
	protected ArrayList<Controllable> controlledArray;

	public Controller(EventManager manager, final ServerCommunicator comm) {
		controlledArray = new ArrayList<Controllable>();
		// comm = new ServerCommunicator();
		// comm.openConnectionTo("localhost");
		this.manager = manager;
		this.comm = comm;
		//Check every 100ms if there's keys pressed
		//(This is the Swing Timer they talk about)
		new Timer(20, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String keysString = "";
				if(!pressed.isEmpty()){
					for(Integer pressedCode: pressed) {
						if (pressedCode == KeyEvent.VK_W) {
							NetworkMessage msg=new NetworkMessage(pressedCode);
							GameEvent ev = new MoveEvent(0);
							msg.setEvent(ev);
							comm.sendMessage(msg);
						}
						if (pressedCode == KeyEvent.VK_D) {
							NetworkMessage msg=new NetworkMessage(pressedCode);
							GameEvent ev = new TurnEventCW(0);
							msg.setEvent(ev);
							comm.sendMessage(msg);
						}
						if (pressedCode == KeyEvent.VK_A) {
							controlledArray.get(0).setTurningCCW();
						}
						if (pressedCode == KeyEvent.VK_Q) {
							controlledArray.get(0).shoot();
						}

						if (controlledArray.size() > 1) {
							if (pressedCode == KeyEvent.VK_UP) {
								NetworkMessage msg=new NetworkMessage(pressedCode);
								GameEvent ev = new MoveEvent(1);
								msg.setEvent(ev);
								comm.sendMessage(msg);
							}
							if (pressedCode == KeyEvent.VK_RIGHT) {
								NetworkMessage msg=new NetworkMessage(pressedCode);
								GameEvent ev = new TurnEventCW(1);
								msg.setEvent(ev);
								comm.sendMessage(msg);
							}
							if (pressedCode == KeyEvent.VK_LEFT) {
								controlledArray.get(1).setTurningCCW();
							}
							if (pressedCode == KeyEvent.VK_CONTROL) {
								controlledArray.get(1).shoot();
							}
						}
					}
				} 
			}
		}).start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("Key pressed...");
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

}
