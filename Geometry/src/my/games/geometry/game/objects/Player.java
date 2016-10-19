package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.events.ObjectUpdatedEvent;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.movers.DefaultMover;
import my.games.geometry.game.renderers.DefaultRenderer;
import my.games.geometry.game.weapons.DefaultWeapon;

public class Player extends GameObject {
	private static final long serialVersionUID = 1L;

	public Player(ObjectPosition position, double angle) {
		super(position, angle);
		health = 100;
		level = 1;
		experienceForUp = level * 1000;
		currentExperience = 0;
		mover = new DefaultMover(this);
		weapon = new DefaultWeapon(this);
		renderer = new DefaultRenderer(this);
		body();
	}

	public List<Point> body() {
		// points.clear();
		if (points.isEmpty()) {
			for (int i = 0; i < level + 2; i++) {
				int x1 = (int) (currentPos.getX() + (objWidth * Math.cos(2 * Math.PI / (level + 2) * i + angle)));
				int y1 = (int) (currentPos.getY() + (objHeight * Math.sin(2 * Math.PI / (level + 2) * i + angle)));
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
		ObjectUpdatedEvent objectUpdatedEvent = new ObjectUpdatedEvent(this);
		this.notifyObserversAbout(objectUpdatedEvent);
	}

	@Override
	public GameObject copy() {
		GameObject copy = new Player(this.currentPos.copy(), this.angle);
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
