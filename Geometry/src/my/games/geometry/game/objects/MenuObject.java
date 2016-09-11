package my.games.geometry.game.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import my.games.geometry.game.GameCamera;

public class MenuObject extends GameCamera {

	private static final long serialVersionUID = 1L;

	private int Width;
	private int Height;
	private List<GameObject> drawableObjects;

	public MenuObject() {
		super();
		Width = 1000;
		Height = 50;
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
		g.drawRect(0, 0, Width, Height);
		if (drawableObjects != null) {
			g.drawString("Player HP:" + Integer.toString(drawableObjects.get(1).getHealth()), 0, 20);
			g.drawString("Player weapon:" + drawableObjects.get(1).getWeapon(), 100, 20);
			g.drawString("Player level:" + drawableObjects.get(1).getLevel(), 230, 20);
			g.drawString("Exp:" + drawableObjects.get(1).getCurrentExperience() + "/"
					+ drawableObjects.get(1).getExperienceForUp(), 0, 40);
		}
	}

}
