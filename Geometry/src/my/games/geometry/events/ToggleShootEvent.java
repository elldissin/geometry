package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ToggleShootEvent extends GameEvent {

	/**
	 * ShootEvent is not sent to client, as he only creates objects
	 */
	private static final long serialVersionUID = 1L;
	private boolean shooting;

	public ToggleShootEvent(GameObject sourceObject, boolean value) {
		super(sourceObject);
		shooting = value;
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(sourceObject.getObjectID());
		if (obj != null)
			obj.getWeapon().setShooting(shooting);
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new ToggleShootEvent(this.sourceObject.copy(), this.shooting);
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
