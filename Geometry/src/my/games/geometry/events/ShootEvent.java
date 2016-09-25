package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ShootEvent extends GameEvent {

	/**
	 * ShootEvent is not sent to client, as he only creates objects
	 */
	private static final long serialVersionUID = 1L;

	public ShootEvent(GameObject sourceObject, long timeStamp) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(sourceObject.getObjectID());
		if (obj != null)
			obj.getWeapon().shoot(obj);
	}

}
