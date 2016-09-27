package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import my.games.geometry.game.objects.GameObject;

public class GameStatusPanel extends GameCameraPanel {

	private static final long serialVersionUID = 1L;
	private int Width;
	private int Height;
	private List<GameObject> drawableObjects;

	public GameStatusPanel() {
		super();
		Width = 800;
		Height = 65;
		setPreferredSize(new Dimension(Width, Height));
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int Width) {
		this.Width = Width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int Height) {
		this.Height = Height;
	}

	public void show(List<GameObject> objects) {
		drawableObjects = objects;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// drawing rectangle for split screen
		g.drawRect(1, 1, getWidth() - 1, getHeight() - 10);
		if (drawableObjects != null) { // FIXME magic number 0?
			// g.drawString("Player HP:" +
			// Integer.toString(drawableObjects.get(0).getHealth()), 0,
			// 20);
			// g.drawString("Player weapon:" +
			// drawableObjects.get(0).getWeapon(), 100, 20);
			// g.drawString("Player level:" + drawableObjects.get(0).getLevel(),
			// 230, 20);
			// g.drawString("Exp:" +
			// drawableObjects.get(0).getCurrentExperience() + "/"
			// + drawableObjects.get(0).getExperienceForUp(), 0, 40);
		}
	}

}
