package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.movers.ProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class Projectile extends GameObject {
	private static final long serialVersionUID = 1L;

	public Projectile(int x, int y, double angle) {
		super(x, y, angle);
		mover = new ProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 5;
		objWidth = 5;
		body();
	}

	@Override
	public List<Point> body() {
		if (points.isEmpty()) {
			Point p = new Point(currentPos.x + objWidth / 2, currentPos.y + objHeight / 2);
			points.add(0, p);
			p = new Point(currentPos.x - objWidth / 2, currentPos.y + objHeight / 2);
			points.add(1, p);
			p = new Point(currentPos.x + objWidth / 2, currentPos.y - objHeight / 2);
			points.add(2, p);
			p = new Point(currentPos.x - objWidth / 2, currentPos.y - objHeight / 2);
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
		GameObject copy = new Projectile(this.currentPos.x, this.currentPos.y, this.angle);
		// angle, x and y are already copied above by constructor;
		copy.objectID = this.objectID;
		copy.objWidth = this.objWidth;
		copy.objHeight = this.objHeight;
		copy.health = this.health;
		copy.level = this.level;
		copy.experienceForUp = this.experienceForUp;
		copy.currentExperience = this.currentExperience;
		copy.obsolete = this.obsolete;
		copy.distTravelled = this.distTravelled;
		copy.liveDistance = this.liveDistance;
		copy.prevPos.x = this.prevPos.x;
		copy.prevPos.y = this.prevPos.y;
		copy.behaviour = this.behaviour.copy();
		copy.mover = this.mover.copy();
		copy.weapon = this.weapon.copy();
		// no need to copy renderer copy.renderer = this.renderer.copy();
		// no need to copy EventObservers
		for (int i = 0; i < this.ignoredObjects.size(); i++) {
			copy.ignoredObjects.add(this.ignoredObjects.get(i).copy());
		}
		for (int i = 0; i < this.onHitEffects.size(); i++) {
			copy.onHitEffects.add(this.onHitEffects.get(i).copy());
			;
		}
		for (int i = 0; i < this.points.size(); i++) {
			copy.points.add(new Point(this.points.get(i)));
		}

		return copy;
	}
}
