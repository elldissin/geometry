package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import my.games.geometry.game.engine.ObjectPosition;

public class NoObject extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoObject(ObjectPosition position, double angle) {
		super(position, angle);
	}

	@Override
	public List<Point> body() {
		return new ArrayList<Point>();
	}

	@Override
	public void getHit(int amount) {
		// TOBE EMPTY
	}

	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return this;
	}

}
