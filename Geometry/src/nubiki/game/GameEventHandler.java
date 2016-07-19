package nubiki.game;

import nubiki.events.GameEvent;

public class GameEventHandler {
	
	public static void handleEvent(GameEvent ev) {
		Controllable obj;
		switch (ev.doEvent()) {
		case MOVE:
			obj = (Controllable)GameObjectManager.getObjectByID(ev.getTargetID());
			obj.setMoving();
			break;
		case STOP:
			obj = (Controllable)GameObjectManager.getObjectByID(ev.getTargetID());
			obj.setStopped();
			break;
		case TURNCW:
			break;
		case TURNCCW:
			break;
		default:
			break;
		}
	}
}
