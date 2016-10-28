package my.games.geometry.game.renderers;

import java.awt.Point;
import java.io.Serializable;

import my.games.geometry.game.engine.ShapeElement;

public abstract class Animator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ShapeElement shapeForCurrentFrame;
	protected int frameCounter;

	public Animator() {
		frameCounter = 0;
		shapeForCurrentFrame = new ShapeElement();
	}

	public ShapeElement animatePoints(ShapeElement originShape, Point center) {
		shapeForCurrentFrame.clear();
		frameCounter++;
		if (frameCounter > 360) {
			frameCounter = 0;
		}
		modifyPoints(originShape, center);
		return shapeForCurrentFrame;
	}

	protected abstract void modifyPoints(ShapeElement originShape, Point center);
}
