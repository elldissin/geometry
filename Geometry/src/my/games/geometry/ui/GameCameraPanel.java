package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import my.games.geometry.game.objects.GameObject;

public class GameCameraPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int viewWidth;
	private int viewHeight;
	private List<GameObject> drawableObjects;
	private Point viewOffset;

	public GameCameraPanel() {
		super();
		viewWidth = 800;
		viewHeight = 600;
		setPreferredSize(new Dimension(viewWidth, viewHeight));
		viewOffset = new Point(0, 0);
		drawableObjects = new ArrayList<GameObject>();
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
		g.translate((int) -viewOffset.getX(), (int) -viewOffset.getY());
		if (drawableObjects != null)
			for (int i = 0; i < drawableObjects.size(); i++)
				drawableObjects.get(i).draw(g);
	}

}
