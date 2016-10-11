package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.ObjectPosition;
import my.games.geometry.game.movers.NoMover;
import my.games.geometry.game.renderers.DefaultRenderer;

public class StaticObject extends GameObject {

	public StaticObject(ObjectPosition position, double angle) {
		super(position, angle);
		health = 100;
		level = 2;
		objWidth = 50;
		objHeight = 50;
		mover = new NoMover(this);
		// weapon = new DefaultWeapon();
		renderer = new DefaultRenderer();
		body();
	}

	@Override
	public List<Point> body() {
		if (points.isEmpty()) {
			for (int i = 0; i < level + 2; i++) {
				int x1 = (int) (currentPos.getIntX() + (objWidth * Math.cos(2 * Math.PI / (level + 2) * i + angle)));
				int y1 = (int) (currentPos.getIntY() + (objHeight * Math.sin(2 * Math.PI / (level + 2) * i + angle)));
				Point p = new Point(x1, y1);
				points.add(i, p);
			}
		}
		return points;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getHit(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameObject copy() {
		GameObject copy = new StaticObject(this.currentPos.copy(), this.angle);
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
