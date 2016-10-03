package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class CreateEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		if (sourceObject != null)
			world.createGameObject(sourceObject);
	}

	@Override
	public GameEvent copy(GameEvent toBeCopied) {
		GameEvent copy = new CreateEvent(toBeCopied.sourceObject.copy());
		copy.timeStamp = toBeCopied.timeStamp;
		return copy;
	}

}
