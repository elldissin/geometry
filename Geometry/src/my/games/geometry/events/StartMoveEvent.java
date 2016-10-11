package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class StartMoveEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StartMoveEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		sourceObject.setMoving(true);
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new StartMoveEvent(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
