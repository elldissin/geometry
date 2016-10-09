package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import my.games.geometry.game.objects.GameObject;

public class GameStatusPanel extends GameCameraPanel {

	private static final long serialVersionUID = 1L;
	private GameObject displayedObject;

	public GameStatusPanel() {
		super();
		setViewWidth(800);
		setViewHeight(65);
		setPreferredSize(new Dimension(getViewWidth(), getViewHeight()));
	}

	public void setDisplayedObject(GameObject obj) {
		displayedObject = obj;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawRect(1, 1, getWidth() - 1, getHeight() - 10);
		if (displayedObject != null) {
			g.drawString("Player HP:" + Integer.toString(displayedObject.getHealth()), 0, 20);
			g.drawString("Player weapon:" + displayedObject.getWeapon(), 100, 20);
			g.drawString("Player level:" + displayedObject.getLevel(), 230, 20);
			g.drawString("Exp:" + displayedObject.getCurrentExperience() + "/" + displayedObject.getExperienceForUp(),
					0, 40);
		}
	}

}
