package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class TurnEventCW extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurnEventCW(int targetID) {
		super(targetID);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(targetID);
		obj.turn(1);
	}

}
