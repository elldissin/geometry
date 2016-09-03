package my.games.geometry.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import my.games.geometry.game.objects.GameObject;

public class GameCamera extends JPanel {

	private static final long serialVersionUID = 1L;

	private int viewWidth;
	private int viewHeight;
	private List<GameObject> drawableObjects;
	private Point viewOffset;

	public GameCamera() {
		super();
		viewWidth = 400;
		viewHeight = 600;
		setPreferredSize(new Dimension(viewWidth, viewHeight));
		viewOffset = new Point(0, 0);
	}

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

	public Point getViewOffset() {
		return viewOffset;
	}

	public void setViewOffset(Point viewOffset) {
		this.viewOffset = viewOffset;
	}

	public void show(List<GameObject> objects) {
		drawableObjects = objects;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// drawing rectangle for split screen
		g.drawRect(0, 0, 399, 600);
		g.translate((int) -viewOffset.getX(), (int) -viewOffset.getY());
		if (drawableObjects != null)
			for (int i = 0; i < drawableObjects.size(); i++)
				drawableObjects.get(i).draw(g);
	}
}