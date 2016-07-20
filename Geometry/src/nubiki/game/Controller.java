package nubiki.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

	public Controller(EventManager manager, ServerCommunicator comm) {
		controlledArray = new ArrayList<Controllable>();
		// comm = new ServerCommunicator();
		// comm.openConnectionTo("localhost");
		this.manager = manager;
		this.comm = comm;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("Key pressed...");
		int code = e.getKeyCode();
		pressed.add(code);
		// if(code!=0) {
		// comm.sendMessage(new NetworkMessage(code));
		// NetworkMessage nm=comm.getNextMessage();
		// if(nm!=null) //the message queue might yet be empty at this stage
		// code = nm.getKeyCode();
		// }

		if (controlledArray.size() > 0) {
			for(Integer pressedCode: pressed)
			{
				if (pressedCode == KeyEvent.VK_W) {
					 System.out.println("Key W pressed...");
					//				manager.addEvent(new MoveEvent(0));
					NetworkMessage msg=new NetworkMessage(pressedCode);
					GameEvent ev = new MoveEvent(0);
					msg.setEvent(ev);
					comm.sendMessage(msg);
				}
				if (pressedCode == KeyEvent.VK_D) {
//					controlledArray.get(0).setTurningCW();
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
			}
		}

		if (controlledArray.size() > 1) {
			if (code == KeyEvent.VK_UP) {
				manager.addEvent(new MoveEvent(1));
			}
			if (code == KeyEvent.VK_RIGHT) {
				controlledArray.get(1).setTurningCW();
			}
			if (code == KeyEvent.VK_LEFT) {
				controlledArray.get(1).setTurningCCW();
			}
			if (code == KeyEvent.VK_CONTROL) {
				controlledArray.get(1).shoot();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		pressed.remove(code);
		// this needs to be fixed, stopping even when releasing the other key
//		if (controlledArray.size() > 0) {
//			if (code == KeyEvent.VK_W) {
//				//				manager.addEvent(new StopEvent(0));
//				NetworkMessage msg=new NetworkMessage(code);
//				GameEvent ev = new StopEvent(0);
//				msg.setEvent(ev);
//				comm.sendMessage(msg);
//			}
//			if (code == KeyEvent.VK_A) {
//				controlledArray.get(0).setNotTurning();
//			}
//			if (code == KeyEvent.VK_D) {
//				controlledArray.get(0).setNotTurning();
//			}
//		}
//		if (controlledArray.size() > 1) {
//			if (code == KeyEvent.VK_UP) {
//				manager.addEvent(new StopEvent(1));
//			}
//			if (code == KeyEvent.VK_LEFT) {
//				controlledArray.get(1).setNotTurning();
//			}
//			if (code == KeyEvent.VK_RIGHT) {
//				controlledArray.get(1).setNotTurning();
//			}
//		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void takeControlOf(Controllable obj) {
		controlledArray.add(obj);
	}

}
