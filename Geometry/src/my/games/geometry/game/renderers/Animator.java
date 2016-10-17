package my.games.geometry.game.renderers;

import java.awt.Point;
import java.util.ArrayList;

public class Animator {
	private ArrayList<Point> pointsForCurrentFrame;
	private int frameCounter;

	public Animator() {
		frameCounter = 0;
		pointsForCurrentFrame = new ArrayList<Point>();
	}

	public ArrayList<Point> animatePoints(ArrayList<Point> initialPoints) {
		frameCounter++;
		if (frameCounter > 10)
			frameCounter = 0;
		for (int i = 0; i < initialPoints.size(); i++) {
			Point p = initialPoints.get(i);
			// FIXME add some changing code to points
		}
		return pointsForCurrentFrame;
	}
}
