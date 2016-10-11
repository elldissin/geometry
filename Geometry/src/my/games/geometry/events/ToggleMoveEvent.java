package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ToggleMoveEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean moving;

	public ToggleMoveEvent(GameObject sourceObject, boolean value) {
		super(sourceObject);
		this.moving = value;
	}

	@Override
	public void applyEventToWorld(World world) {
		sourceObject.setMoving(moving);
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new ToggleMoveEvent(this.sourceObject.copy(), this.moving);
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
