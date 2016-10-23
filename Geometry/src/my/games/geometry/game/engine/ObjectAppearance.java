package my.games.geometry.game.engine;

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

	public ObjectAppearance() {
		elementsList = new ArrayList<AppearanceElement>();
	}

	public void addElement(AppearanceElement elem) {
		elementsList.add(elem);
	}

	public AppearanceElement getElement(int i) {
		return elementsList.get(i);
	}

	public int size() {
		return elementsList.size();
	}

}
