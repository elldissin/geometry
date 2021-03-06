package my.games.geometry.game.objects;

import java.awt.Point;

import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.ObjectUpdatedEvent;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.ObjectShape;
import my.games.geometry.game.engine.ShapeElement;
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
		mover = new DefaultMover(this, position, angle);
		weapon = new DefaultWeapon(this);
		renderer = new DefaultRenderer(this);
		behaviour = new PlayerBehaviour(this);
		rebuildShape();
	}

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

			element = new ShapeElement();
			for (int i = 0; i < 5; i++) {
				int x1 = (int) (60 + mover.getPos().getX() + (objWidth / 2 * Math.cos(2 * Math.PI / (5) * i)));
				int y1 = (int) (mover.getPos().getY() + (objHeight / 2 * Math.sin(2 * Math.PI / (5) * i)));
				Point p = new Point(x1, y1);
				element.addPoint(p);
			}
			shape.addElement(element);
			element = new ShapeElement();
			for (int i = 0; i < 5; i++) {
				int x1 = (int) (mover.getPos().getX() + (objWidth / 2 * Math.cos(2 * Math.PI / (5) * i)));
				int y1 = (int) (60 + mover.getPos().getY() + (objHeight / 2 * Math.sin(2 * Math.PI / (5) * i)));
				Point p = new Point(x1, y1);
				element.addPoint(p);
			}
			shape.addElement(element);
			element = new ShapeElement();
			for (int i = 0; i < 5; i++) {
				int x1 = (int) (-60 + mover.getPos().getX() + (objWidth / 2 * Math.cos(2 * Math.PI / (5) * i)));
				int y1 = (int) (mover.getPos().getY() + (objHeight / 2 * Math.sin(2 * Math.PI / (5) * i)));
				Point p = new Point(x1, y1);
				element.addPoint(p);
			}
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
		GameObject copy = new Player(this.mover.getPos().copy(), this.mover.getAngle());
		// angle, x and y are already copied above by constructor;
		super.finishCopy(copy);
		return copy;
	}
}
