package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;

public class DefaultMover extends GeneralMover implements Mover, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultMover(GameObject ownerObject, ObjectPosition position, double angle) {
		super(ownerObject, position, angle);
		speed = 5;
		maxSpeed = 10;
		turnSpeed = 0.1;
	}

	@Override
	public Mover copy() {
		DefaultMover copy = new DefaultMover(ownerObject, this.currentPos.copy(), this.angle);
		finishCopy(copy);
		return copy;
	}
}
