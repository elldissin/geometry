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
public class ShapeElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Point> pointList;

	public ShapeElement() {
		pointList = new ArrayList<Point>();
	}

	public void addPoint(Point p) {
		pointList.add(p);
	}

	public Point getPoint(int i) {
		return pointList.get(i);
	}

	public int size() {
		return pointList.size();
	}

	public void clear() {
		pointList.clear();
	}

}
