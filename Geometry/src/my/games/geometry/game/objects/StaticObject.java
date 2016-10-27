package my.games.geometry.game.objects;

import java.awt.Point;

import my.games.geometry.behaviour.NoBehaviour;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.ObjectShape;
import my.games.geometry.game.engine.ShapeElement;
import my.games.geometry.game.movers.NoMover;
import my.games.geometry.game.renderers.DefaultRenderer;
import my.games.geometry.game.weapons.NoWeapon;

public class StaticObject extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StaticObject(ObjectPosition position, double angle) {
		super(position, angle);
		health = 100;
		level = 2;
		objWidth = 50;
		objHeight = 50;
		mover = new NoMover(this, position, angle);
		weapon = new NoWeapon(this);
		renderer = new DefaultRenderer(this);
		behaviour = new NoBehaviour(this);
		rebuildShape();
	}

	@Override
	public ObjectShape rebuildShape() {
		ShapeElement element = new ShapeElement();
		if (shape.size() == 0) {
			for (int i = 0; i < level + 2; i++) {
				int x1 = (int) (mover.getPos().getX()
						+ (objWidth * Math.cos(2 * Math.PI / (level + 2) * i + mover.getAngle())));
				int y1 = (int) (mover.getPos().getY()
						+ (objHeight * Math.sin(2 * Math.PI / (level + 2) * i + mover.getAngle())));
				Point p = new Point(x1, y1);
				element.addPoint(p);
			}
			shape.addElement(element);
		}
		return shape;
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
		GameObject copy = new StaticObject(this.mover.getPos().copy(), this.mover.getAngle());
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
