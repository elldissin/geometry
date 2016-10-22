package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import my.games.geometry.game.objects.GameObject;

public class GameCameraPanel extends DisplayElement {

	private static final long serialVersionUID = 1L;
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

	public Point getViewOffset() {
		return viewOffset;
	}

	public void setViewOffset(Point viewOffset) {
		this.viewOffset = viewOffset;
	}

	@Override
	public void show(List<GameObject> objects) {
		if (focusedObject != null) {
			Point p = new Point((int) focusedObject.getMover().getPos().getX() - getViewWidth() / 2,
					(int) focusedObject.getMover().getPos().getY() - getViewHeight() / 2);
			setViewOffset(p);
		}
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
