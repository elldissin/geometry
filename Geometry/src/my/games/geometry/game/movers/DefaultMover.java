package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public class DefaultMover extends GeneralMover implements Mover, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultMover(GameObject objectToMove) {
		super(objectToMove);
		speed = 5;
		maxSpeed = 10;
		turnSpeed = 0.1;
	}

	@Override
	public Mover copy() {
		DefaultMover copy = new DefaultMover(ownerObject.copy());
		finishCopy(copy);
		return copy;
	}
}
