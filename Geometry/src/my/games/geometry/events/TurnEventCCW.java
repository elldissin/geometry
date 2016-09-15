package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class TurnEventCCW extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurnEventCCW(int targetID) {
		super(targetID);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(targetID);
		if (obj != null)
			obj.turn(-1);
	}
}
