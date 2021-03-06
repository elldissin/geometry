package my.games.geometry.game.movers;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;

public class BFGProjectileMover extends ProjectileMover {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BFGProjectileMover(GameObject ownerObject, ObjectPosition position, double angle) {
		super(ownerObject, position, angle);
		speed = 4;
	}

	public Mover copy() {
		ProjectileMover copy = new BFGProjectileMover(ownerObject, this.currentPos.copy(), this.angle);
		finishCopy(copy);
		return copy;
	}

}
