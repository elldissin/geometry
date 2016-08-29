package my.games.geometry.events;

import geometry.networking.events.GameEvent;
import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class EventHandler {
	private World world;

	public EventHandler(World world) {
		this.world = world;
	}

	public void handleEvent(GameEvent ev) {
		// Controllable obj;
		GameObject obj;
		switch (ev.getEventType()) {
		case MOVE:
			// obj =
			// (Controllable)GameObjectManager.getObjectByID(ev.getTargetID());
			// obj.setMoving();
			// System.out.println("Handling event:"+ ev.doEvent());
			obj = world.getObjectByID(ev.getTargetID());
			obj.getMover().move(obj);
			break;
		case TURNCW:
			obj = world.getObjectByID(ev.getTargetID());
			obj.getMover().turn(obj, 1);
			break;
		case TURNCCW:
			obj = world.getObjectByID(ev.getTargetID());
			obj.getMover().turn(obj, -1);
			break;
		case SHOOT:
			obj = world.getObjectByID(ev.getTargetID());
			obj.getWeapon().shoot(obj);
			break;
		default:
			break;
		}
	}
}
