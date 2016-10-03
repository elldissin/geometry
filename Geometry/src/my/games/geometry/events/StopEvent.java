package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class StopEvent extends GameEvent {
	private static final long serialVersionUID = 1L;

	public StopEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		// REMOVE remove stop event ?
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new StopEvent(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}
}
