package nubiki.game;

import nubiki.events.GameEvent;

public class GameEventHandler {
	
	public static void handleEvent(GameEvent ev) {
//		Controllable obj;
		GameObject obj;
		switch (ev.doEvent()) {
		case MOVE:
//			obj = (Controllable)GameObjectManager.getObjectByID(ev.getTargetID());
//			obj.setMoving();
//			System.out.println("Handling event:"+ ev.doEvent());
			obj = GameObjectManager.getObjectByID(ev.getTargetID());
			obj.getMover().move(obj);
			break;
		case STOP:
//			obj = (Controllable)GameObjectManager.getObjectByID(ev.getTargetID());
//			obj.setStopped();
			break;
		case TURNCW:
//			System.out.println("Handling event:"+ ev.doEvent());
			obj = GameObjectManager.getObjectByID(ev.getTargetID());
			obj.getMover().turn(obj);
			break;
		case TURNCCW:
			break;
		default:
			break;
		}
	}
}
