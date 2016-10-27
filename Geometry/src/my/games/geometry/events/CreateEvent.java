package my.games.geometry.events;

import my.games.geometry.game.engine.World;
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
			world.registerGameObject(sourceObject);
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new CreateEvent(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
