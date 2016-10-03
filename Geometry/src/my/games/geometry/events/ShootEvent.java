package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ShootEvent extends GameEvent {

	/**
	 * ShootEvent is not sent to client, as he only creates objects
	 */
	private static final long serialVersionUID = 1L;

	public ShootEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(sourceObject.getObjectID());
		if (obj != null)
			obj.getWeapon().shoot(obj);
	}

	@Override
	public GameEvent copy(GameEvent toBeCopied) {
		GameEvent copy = new ShootEvent(toBeCopied.sourceObject.copy());
		copy.timeStamp = toBeCopied.timeStamp;
		return copy;
	}

}
