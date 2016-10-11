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

	public ToggleTurnEvent(GameObject sourceObject, Mover.TurnDirection dir) {
		super(sourceObject);
		this.dir = dir;
	}

	@Override
	public void applyEventToWorld(World world) {
		sourceObject.setTurning(dir);

	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new ToggleTurnEvent(this.sourceObject.copy(), dir);
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
