package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class StopMoveEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StopMoveEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		sourceObject.setMoving(false);
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new StopMoveEvent(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
