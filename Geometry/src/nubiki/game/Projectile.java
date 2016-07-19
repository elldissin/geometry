package nubiki.game;

import java.awt.Point;
import java.util.ArrayList;

import nubiki.game.movers.DefaultMover;
import nubiki.game.movers.ProjectileMover;
import nubiki.game.renderers.DefaultRenderer;
import nubiki.game.renderers.ProjectileRenderer;

public class Projectile extends GameObject {
	private static final long serialVersionUID = 1L;

	public Projectile(int x, int y) {
		super(x,y);
		mover = new ProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 5;
		objWidth = 5;
		body();
	}

	@Override
	public ArrayList<Point> body() {
		if (points.isEmpty()) {
			Point p = new Point(currentPos.x + 5, currentPos.y + 5);
			points.add(0, p);
			p = new Point(currentPos.x + 10, currentPos.y + 5);
			points.add(1, p);
			p = new Point(currentPos.x + 10, currentPos.y + 0);
			points.add(2, p);
			p = new Point(currentPos.x + 5, currentPos.y + 0);
			points.add(3, p);
		}
		return points;
	}

	@Override
	public void destroy() {
		setObsolete(true);
	}

	@Override
	public boolean isDestroyed() {
		return obsolete;
	}

	@Override
	public void getHit(int amount) {
		return;
	}
}
