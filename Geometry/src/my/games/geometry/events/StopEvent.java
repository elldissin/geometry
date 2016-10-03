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
	public GameEvent copy(GameEvent toBeCopied) {
		GameEvent copy = new StopEvent(toBeCopied.sourceObject.copy());
		copy.timeStamp = toBeCopied.timeStamp;
		return copy;
	}
}
