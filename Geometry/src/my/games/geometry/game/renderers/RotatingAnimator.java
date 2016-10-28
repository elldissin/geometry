package my.games.geometry.game.renderers;

import java.awt.Point;

import my.games.geometry.game.engine.ShapeElement;

public class RotatingAnimator extends Animator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RotatingAnimator() {
		super();
	}

	@Override
	protected void modifyPoints(ShapeElement originShape, Point center) {
		double angle = Math.toRadians(frameCounter) * 3;
		for (int i = 0; i < originShape.size(); i++) {
			Point p = (Point) originShape.getPoint(i).clone();
			RotateTransform.transform(p, center, angle);
			shapeForCurrentFrame.addPoint(p);
		}
	}
}
