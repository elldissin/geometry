package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.movers.Mover;
import my.games.geometry.game.objects.GameObject;

public class ToggleTurnEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mover.TurnDirection dir;
	private boolean turning;

	public ToggleTurnEvent(GameObject sourceObject, Mover.TurnDirection dir, boolean isPressed) {
		super(sourceObject);
		this.dir = dir;
		this.turning = isPressed;
	}

	@Override
	public void applyEventToWorld(World world) {
		sourceObject.setTurning(dir, turning);

	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new ToggleTurnEvent(this.sourceObject.copy(), this.dir, this.turning);
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
