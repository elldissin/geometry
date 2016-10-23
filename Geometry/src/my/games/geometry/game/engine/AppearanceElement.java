package my.games.geometry.game.engine;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LCrystal Represents a collection of 2d points being one of object visual appearance
 *         elements (if many)
 * 
 */
public class AppearanceElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Point> points;
	private int nextPointCounter;

	public AppearanceElement() {
		points = new ArrayList<Point>();
	}

	public void addPoint(Point p) {
		points.add(p);
	}

	public Point getNextPoint(Point p) {
		return points.get(nextPointCounter++);
	}

	public boolean hasNext() {
		return (nextPointCounter + 1) < points.size() ? true : false;
	}

	public int size() {
		return points.size();
	}

}
