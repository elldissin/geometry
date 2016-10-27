package my.games.geometry.game.renderers;

import java.awt.Point;

import my.games.geometry.game.engine.ObjectShape;

public class OrbitingAnimator extends Animator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void modifyPoints(ObjectShape originShape, Point center) {
		double angle = Math.toRadians(frameCounter) * 2;
		for (int i = 0; i < initialPoints.size(); i++) {
			Point p = (Point) initialPoints.get(i).clone();
			pointsForCurrentFrame.add(p);
		}
		Point rotatingParticle = new Point(center);
		rotatingParticle.setLocation(rotatingParticle.getX() + 40, rotatingParticle.getY() + 40);
		RotateTransform.transform(rotatingParticle, center, angle);
		pointsForCurrentFrame.add(rotatingParticle);
	}

}
