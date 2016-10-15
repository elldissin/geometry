package my.games.geometry.ui;

import java.util.List;

import javax.swing.JPanel;

import my.games.geometry.game.objects.GameObject;

/**
 * @author LCrystal Represents a view in a game window, which can be camera, panels, menus etc
 *
 */
public abstract class DisplayElement extends JPanel {

	private static final long serialVersionUID = 1L;
	protected GameObject focusedObject;
	protected int viewWidth, viewHeight;

	public DisplayElement() {
		super();
	}

	public void setFocusedObject(GameObject focusedObject) {
		this.focusedObject = focusedObject;
	}

	public abstract void show(List<GameObject> objects);

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}
}
