package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.movers.DefaultMover;
import my.games.geometry.game.renderers.DefaultRenderer;
import my.games.geometry.game.weapons.DefaultWeapon;

public class Player extends GameObject {
	private static final long serialVersionUID = 1L;

	public Player(Point position, double angle) {
		super(position, angle);
		health = 100;
		level = 5;
		experienceForUp = level * 1000;
		currentExperience = 0;
		mover = new DefaultMover();
		weapon = new DefaultWeapon();
		renderer = new DefaultRenderer();
		body();
	}

	public List<Point> body() {
		// points.clear();
		if (points.isEmpty()) {
			for (int i = 0; i < level + 2; i++) {
				int x1 = (int) (currentPos.x + (objWidth * Math.cos(2 * Math.PI / (level + 2) * i + angle)));
				int y1 = (int) (currentPos.y + (objHeight * Math.sin(2 * Math.PI / (level + 2) * i + angle)));
				Point p = new Point(x1, y1);
				points.add(i, p);
			}
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
		if (health - amount <= 0) {
			health = 0;
			destroy();
		} else
			health -= amount;
	}

	@Override
	public GameObject copy() {
		GameObject copy = new Player((Point) (this.currentPos.clone()), this.angle);
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
