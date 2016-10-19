package my.games.geometry.game.renderers;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Animator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<Point> pointsForCurrentFrame;
	protected int frameCounter;

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
		modifyPoints(initialPoints, center);
		return pointsForCurrentFrame;
	}

	protected abstract void modifyPoints(List<Point> initialPoints, Point center);
}
