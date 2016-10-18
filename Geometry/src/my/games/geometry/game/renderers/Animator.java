package my.games.geometry.game.renderers;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Animator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Point> pointsForCurrentFrame;
	private int frameCounter;

	public Animator() {
		frameCounter = 0;
		pointsForCurrentFrame = new ArrayList<Point>();
	}

	public List<Point> animatePoints(List<Point> initialPoints, Point center) {
		pointsForCurrentFrame.clear();
		frameCounter++;
		if (frameCounter > 360) {
			frameCounter = 0;
		}
		double angle = Math.toRadians(frameCounter) * 2;
		for (int i = 0; i < initialPoints.size(); i++) {
			Point p = (Point) initialPoints.get(i).clone();
			RotateTransform.transform(p, center, angle);
			pointsForCurrentFrame.add(p);
		}
		return pointsForCurrentFrame;
	}
}
