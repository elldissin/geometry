package my.games.geometry.game.renderers;

import java.awt.Point;

import my.games.geometry.game.engine.ObjectShape;

public class RotatingAnimator extends Animator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RotatingAnimator() {
		super();
	}

	@Override
	protected void modifyPoints(ObjectShape originShape, Point center) {
		double angle = Math.toRadians(frameCounter) * 2;
		originShape.getElement(i)
		for (int i = 0; i < initialPoints.size(); i++) {
			Point p = (Point) initialPoints.get(i).clone();
			RotateTransform.transform(p, center, angle);
			pointsForCurrentFrame.add(p);
		}
	}
}
