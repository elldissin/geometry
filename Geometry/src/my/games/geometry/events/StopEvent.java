package my.games.geometry.events;

import my.games.geometry.game.World;

public class StopEvent extends GameEvent {
	private static final long serialVersionUID = 1L;

	public StopEvent(int targetID) {
		super(targetID);
	}

	@Override
	public void applyEventToWorld(World world) {
		// REMOVE remove stop event ?
	}
}
