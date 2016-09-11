package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ShootEvent extends GameEvent {

	/**
	 * ShootEvent is not sent to client, as he only creates objects
	 */
	private static final long serialVersionUID = 1L;

	public ShootEvent(int targetID) {
		super(targetID);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(targetID);
		obj.getWeapon().shoot(obj);
	}

}
