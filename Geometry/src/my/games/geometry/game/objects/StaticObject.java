package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.List;

import my.games.geometry.behaviour.NoBehaviour;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.movers.NoMover;
import my.games.geometry.game.renderers.DefaultRenderer;
import my.games.geometry.game.weapons.NoWeapon;

public class StaticObject extends GameObject {

	public StaticObject(ObjectPosition position, double angle) {
		super(position, angle);
		health = 100;
		level = 2;
		objWidth = 50;
		objHeight = 50;
		mover = new NoMover(this);
		mover.setPos(position);
		mover.setAngle(angle);
		weapon = new NoWeapon(this);
		renderer = new DefaultRenderer(this);
		behaviour = new NoBehaviour(this);
		body();
	}

	@Override
	public List<Point> body() {
		if (points.isEmpty()) {
			for (int i = 0; i < level + 2; i++) {
				int x1 = (int) (mover.getPos().getX()
						+ (objWidth * Math.cos(2 * Math.PI / (level + 2) * i + mover.getAngle())));
				int y1 = (int) (mover.getPos().getY()
						+ (objHeight * Math.sin(2 * Math.PI / (level + 2) * i + mover.getAngle())));
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
		GameObject copy = new StaticObject(this.mover.getPos().copy(), this.mover.getAngle());
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
