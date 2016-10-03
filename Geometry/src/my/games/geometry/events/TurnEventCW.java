package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class TurnEventCW extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurnEventCW(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(sourceObject.getObjectID());
		if (obj != null)
			obj.turn(1);
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new TurnEventCW(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
