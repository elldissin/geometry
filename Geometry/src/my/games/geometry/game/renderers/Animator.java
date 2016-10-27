package my.games.geometry.game.renderers;

import java.awt.Point;
import java.io.Serializable;

import my.games.geometry.game.engine.ObjectShape;

public abstract class Animator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ObjectShape shapeForCurrentFrame;
	protected int frameCounter;

	public Animator() {
		frameCounter = 0;
		shapeForCurrentFrame = new ObjectShape();
	}

	public ObjectShape animatePoints(ObjectShape originShape, Point center) {
		shapeForCurrentFrame.clear();
		frameCounter++;
		if (frameCounter > 360) {
			frameCounter = 0;
		}
		modifyPoints(originShape, center);
		return shapeForCurrentFrame;
	}

	protected abstract void modifyPoints(ObjectShape originShape, Point center);
}
