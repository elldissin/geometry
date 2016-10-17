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

	public List<Point> animatePoints(List<Point> initialPoints) {
		pointsForCurrentFrame.clear();
		frameCounter++;
		if (frameCounter > 10)
			frameCounter = 0;
		for (int i = 0; i < initialPoints.size(); i++) {
			Point p = (Point) initialPoints.get(i).clone();
			p.setLocation(p.getX() * 0.5, p.getY() * 0.5);
			pointsForCurrentFrame.add(p);
		}
		return pointsForCurrentFrame;
	}
}
