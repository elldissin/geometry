package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

/**
 * @author LCrystal Special case mover to avoid null
 */
public class NoMover extends GeneralMover implements Mover, Serializable {
	private static final long serialVersionUID = 1L;

	public NoMover(GameObject objectToMove) {
		super(objectToMove);
	}

	@Override
	public Mover copy() {
		return this;
	}
}
