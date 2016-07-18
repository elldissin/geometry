package nubiki.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import nubiki.events.EventManager;
import nubiki.events.MoveEvent;
import nubiki.events.StopEvent;
import nubiki.networking.NetworkMessage;
import nubiki.networking.ServerCommunicator;

public class Controller implements KeyListener {
	
	ServerCommunicator comm;
	EventManager manager;
	protected ArrayList <Controllable> controlledArray;
	
	public Controller (EventManager manager) {
		controlledArray = new ArrayList <Controllable>();
		comm = new ServerCommunicator();
		comm.openConnectionTo("localhost");
		this.manager=manager;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//		System.out.println("Key pressed...");
		int code=0;
		if(e!=null)
			code = e.getKeyCode();
//		if(code!=0) {
//			comm.sendMessage(new NetworkMessage(code));
//			NetworkMessage nm=comm.getNextMessage();
//			if(nm!=null) //the message queue might yet be empty at this stage
//				code = nm.getKeyCode();
//		}
		
		if(controlledArray.size()>0) {
			if (code==KeyEvent.VK_W){
				manager.addEvent(new MoveEvent(0));
			}
			if (code==KeyEvent.VK_D){
				controlledArray.get(0).setTurningCW();
			}
			if (code==KeyEvent.VK_A){
				controlledArray.get(0).setTurningCCW();
			}
			if (code==KeyEvent.VK_Q) {
				controlledArray.get(0).shoot();
			}
		}

		if(controlledArray.size()>1) {
			if (code==KeyEvent.VK_UP){
				manager.addEvent(new MoveEvent(1));
			}
			if (code==KeyEvent.VK_RIGHT){
				controlledArray.get(1).setTurningCW();
			}
			if (code==KeyEvent.VK_LEFT){
				controlledArray.get(1).setTurningCCW();
			}
			if (code==KeyEvent.VK_CONTROL) {
				controlledArray.get(1).shoot();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		//this needs to be fixed, stopping even when releasing the other key
		if(controlledArray.size()>0) {
			if (code==KeyEvent.VK_W){
				manager.addEvent(new StopEvent(0));
			}
			if (code==KeyEvent.VK_A){
				controlledArray.get(0).setNotTurning();
			}
			if (code==KeyEvent.VK_D){
				controlledArray.get(0).setNotTurning();
			}
		}
		if(controlledArray.size()>1) {
			if (code==KeyEvent.VK_UP){
				manager.addEvent(new StopEvent(1));
			}
			if (code==KeyEvent.VK_LEFT){
				controlledArray.get(1).setNotTurning();
			}
			if (code==KeyEvent.VK_RIGHT){
				controlledArray.get(1).setNotTurning();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public void takeControlOf(Controllable obj) {
		controlledArray.add(obj);
	}

}
