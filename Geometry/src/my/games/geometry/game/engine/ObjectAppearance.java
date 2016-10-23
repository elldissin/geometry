package my.games.geometry.game.engine;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LCrystal Represents object appearance consisting of collection of elements, which are
 *         then drawn by renderers when called.
 *
 */
public class ObjectAppearance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AppearanceElement> elementsList;
	private int nextElementCounter;

	public ObjectAppearance() {
		elementsList = new ArrayList<AppearanceElement>();
	}

	public void addElement(AppearanceElement elem) {
		elementsList.add(elem);
	}

	public AppearanceElement getNextElement(Point p) {
		return elementsList.get(nextElementCounter++);
	}

	public boolean hasNext() {
		return (nextElementCounter + 1) < elementsList.size() ? true : false;
	}

}
