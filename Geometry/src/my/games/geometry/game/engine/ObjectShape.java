package my.games.geometry.game.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LCrystal Represents object appearance consisting of collection of elements, which are
 *         then drawn by renderers when called.
 *
 */
public class ObjectShape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ShapeElement> elementsList;

	public ObjectShape() {
		elementsList = new ArrayList<ShapeElement>();
	}

	public void addElement(ShapeElement elem) {
		elementsList.add(elem);
	}

	public ShapeElement getElement(int i) {
		return elementsList.get(i);
	}

	public int size() {
		return elementsList.size();
	}

}
