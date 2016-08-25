package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.ArrayList;

import my.games.geometry.game.movers.ProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class Projectile extends GameObject {
	private static final long serialVersionUID = 1L;

	public Projectile(int x, int y) {
		super(x, y);
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

	@Override // overriding default update() in GameObject to act without
				// player's control
	public void update() {
		move();
	}
}
