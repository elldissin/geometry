package my.games.geometry.game.movers;

import my.games.geometry.game.objects.GameObject;

public class BFGProjectileMover extends ProjectileMover {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BFGProjectileMover(GameObject objectToMove) {
		super(objectToMove);
		speed = 4;
	}

}
