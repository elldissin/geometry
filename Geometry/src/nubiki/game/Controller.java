package nubiki.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import nubiki.networking.ServerCommunicator;

public class Controller implements KeyListener {
	
	ServerCommunicator comm;
	protected ArrayList <Controllable> controlledArray;
	
	public Controller () {
		controlledArray = new ArrayList <Controllable>();
		comm = new ServerCommunicator();
		comm.openConnectionTo("localhost");
	}


	@Override
	public void keyPressed(KeyEvent e) {
//		comm.sendEvent(e);
//		e=comm.getNextEvent();
		//		System.out.println("Key pressed...");
		int code=0;
		if(e!=null)
			code = e.getKeyCode();
		if(controlledArray.size()>0) {
			if (code==KeyEvent.VK_W){
				controlledArray.get(0).setMoving();
			}
			if (code==KeyEvent.VK_D){
				controlledArray.get(0).setTurningCW();
			}
			if (code==KeyEvent.VK_A){
				controlledArray.get(0).setTurningCCW();
			}
			if (code==KeyEvent.VK_Q) { //casting to be fixed
				controlledArray.get(0).shoot();
			}
		}

		if(controlledArray.size()>1) {
			if (code==KeyEvent.VK_UP){
				controlledArray.get(1).setMoving();
			}
			if (code==KeyEvent.VK_RIGHT){
				controlledArray.get(1).setTurningCW();
			}
			if (code==KeyEvent.VK_LEFT){
				controlledArray.get(1).setTurningCCW();
			}
			if (code==KeyEvent.VK_CONTROL) { //casting to be fixed
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
				controlledArray.get(0).setStopped();
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
				controlledArray.get(1).setStopped();;
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
