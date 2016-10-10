package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.ObjectPosition;
import my.games.geometry.game.movers.ProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class Projectile extends GameObject {
	private static final long serialVersionUID = 1L;

	public Projectile(ObjectPosition position, double angle) {
		super(position, angle);
		mover = new ProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 5;
		objWidth = 5;
		body();
	}

	@Override
	public List<Point> body() {
		if (points.isEmpty()) {
			Point p = new Point(currentPos.getIntX() + objWidth / 2, currentPos.getIntY() + objHeight / 2);
			points.add(0, p);
			p = new Point(currentPos.getIntX() - objWidth / 2, currentPos.getIntY() + objHeight / 2);
			points.add(1, p);
			p = new Point(currentPos.getIntX() + objWidth / 2, currentPos.getIntY() - objHeight / 2);
			points.add(2, p);
			p = new Point(currentPos.getIntX() - objWidth / 2, currentPos.getIntY() - objHeight / 2);
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

	@Override
	public GameObject copy() {
		GameObject copy = new Projectile(this.currentPos.copy(), this.angle);
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
