package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.movers.NoMover;
import my.games.geometry.game.renderers.DefaultRenderer;

public class StaticObject extends GameObject {

	public StaticObject(Point position, double angle) {
		super(position, angle);
		health = 100;
		level = 2;
		objWidth = 50;
		objHeight = 50;
		mover = new NoMover();
		// weapon = new DefaultWeapon();
		renderer = new DefaultRenderer();
		body();
	}

	@Override
	public List<Point> body() {
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
		GameObject copy = new StaticObject((Point) (this.currentPos.clone()), this.angle);
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
		if (this.behaviour != null)
			copy.behaviour = this.behaviour.copy();
		if (this.mover != null)
			copy.mover = this.mover.copy();
		if (this.weapon != null)
			copy.weapon = this.weapon.copy();
		// no need to copy renderer copy.renderer = this.renderer.copy();
		// no need to copy EventObservers
		// for (int i = 0; i < this.ignoredObjects.size(); i++) {
		// copy.ignoredObjects.add(this.ignoredObjects.get(i).copy());
		// }
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
