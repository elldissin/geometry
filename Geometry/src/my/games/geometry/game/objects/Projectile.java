package my.games.geometry.game.objects;

import java.awt.Point;

import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.ObjectShape;
import my.games.geometry.game.engine.ShapeElement;
import my.games.geometry.game.movers.ProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;
import my.games.geometry.game.weapons.NoWeapon;

public class Projectile extends GameObject {
	private static final long serialVersionUID = 1L;

	public Projectile(ObjectPosition position, double angle) {
		super(position, angle);
		mover = new ProjectileMover(this, position, angle);
		renderer = new ProjectileRenderer(this);
		weapon = new NoWeapon(this);
		behaviour = new ProjectileBehaviour(this);
		objHeight = 5;
		objWidth = 5;
		rebuildShape();
	}

	@Override
	public ObjectShape rebuildShape() {
		ShapeElement element = new ShapeElement();
		if (shape.size() == 0) {
			Point p = new Point(mover.getPos().getIntX() + objWidth / 2, mover.getPos().getIntY() + objHeight / 2);
			element.addPoint(p);
			p = new Point(mover.getPos().getIntX() - objWidth / 2, mover.getPos().getIntY() + objHeight / 2);
			element.addPoint(p);
			p = new Point(mover.getPos().getIntX() - objWidth / 2, mover.getPos().getIntY() - objHeight / 2);
			element.addPoint(p);
			p = new Point(mover.getPos().getIntX() + objWidth / 2, mover.getPos().getIntY() - objHeight / 2);
			element.addPoint(p);
			shape.addElement(element);
		}
		return shape;
	}

	@Override
	public boolean isDestroyed() {
		return obsolete;
	}

	@Override
	public void getHit(int amount) {
		return;
	}

	@Override
	public GameObject copy() {
		GameObject copy = new Projectile(this.mover.getPos().copy(), this.mover.getAngle());
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
