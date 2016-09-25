package my.games.geometry.game.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import my.games.geometry.game.GameCamera;

public class BottomStatusBar extends GameCamera {

	private static final long serialVersionUID = 1L;
	private int Width;
	private int Height;
	private List<GameObject> drawableObjects;

	public BottomStatusBar() {
		super();
		Width = 800;
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
		System.out.println("btb paint");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// drawing rectangle for split screen
		g.drawRect(0, 0, Width, Height);
		System.out.println("btb paint");
		if (drawableObjects != null) { // LATER magic number 0?
			g.drawString("Player HP:" + Integer.toString(drawableObjects.get(0).getHealth()), 0, 20);
			g.drawString("Player weapon:" + drawableObjects.get(0).getWeapon(), 100, 20);
			g.drawString("Player level:" + drawableObjects.get(0).getLevel(), 230, 20);
			g.drawString("Exp:" + drawableObjects.get(0).getCurrentExperience() + "/"
					+ drawableObjects.get(0).getExperienceForUp(), 0, 40);
		}
	}

}
