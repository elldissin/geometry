package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.engine.ObjectPosition;

public class NoObject extends GameObject {

	public NoObject(ObjectPosition position, double angle) {
		super(position, angle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> body() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getHit(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
