package my.games.geometry.events;

import geometry.networking.events.GameEvent;
import my.games.geometry.game.GameObjectManager;
import my.games.geometry.game.objects.GameObject;

public class EventHandler {
	
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
		case TURNCW:
			obj = GameObjectManager.getObjectByID(ev.getTargetID());
			obj.getMover().turn(obj,1);
			break;
		case TURNCCW:
			obj = GameObjectManager.getObjectByID(ev.getTargetID());
			obj.getMover().turn(obj,-1);
			break;
		case SHOOT:
			obj = GameObjectManager.getObjectByID(ev.getTargetID());
			obj.getWeapon().shoot(obj);
			break;
		default:
			break;
		}
	}
}
