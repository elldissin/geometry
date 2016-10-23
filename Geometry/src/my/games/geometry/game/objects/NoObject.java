package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.movers.NoMover;
import my.games.geometry.game.renderers.NoRenderer;
import my.games.geometry.game.weapons.NoWeapon;

public class NoObject extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoObject(ObjectPosition position, double angle) {
		super(position, angle);
		mover = new NoMover(this, position, angle);
		weapon = new NoWeapon(this);
		renderer = new NoRenderer(this);
		destroy(); // to remove itself
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
