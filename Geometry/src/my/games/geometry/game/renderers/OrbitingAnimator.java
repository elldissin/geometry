package my.games.geometry.game.renderers;

import java.awt.Point;

import my.games.geometry.game.engine.ShapeElement;

public class OrbitingAnimator extends Animator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void modifyPoints(ShapeElement originShape, Point center) {
		double angle = Math.toRadians(frameCounter) * 2;
		for (int i = 0; i < originShape.size(); i++) {
			Point p = (Point) originShape.getPoint(i).clone();
			shapeForCurrentFrame.addPoint(p);
		}
		Point rotatingParticle = new Point(center);
		rotatingParticle.setLocation(rotatingParticle.getX() + 40, rotatingParticle.getY() + 40);
		RotateTransform.transform(rotatingParticle, center, angle);
		shapeForCurrentFrame.addPoint(rotatingParticle);
	}

}
