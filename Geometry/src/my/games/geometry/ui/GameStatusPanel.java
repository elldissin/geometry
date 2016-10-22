package my.games.geometry.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;

import my.games.geometry.game.objects.GameObject;

public class GameStatusPanel extends DisplayElement {

	private static final long serialVersionUID = 1L;
	JLabel playerHPLabel = new JLabel();
	JLabel playerWeaponLabel = new JLabel();
	JLabel playerLevelLabel = new JLabel();
	JLabel playerExpLabel = new JLabel();
	JLabel playerPositionLabel = new JLabel();

	public GameStatusPanel() {
		super();
		viewWidth = 800;
		viewHeight = 65;
		setPreferredSize(new Dimension(viewWidth, viewHeight));
		this.add(playerHPLabel);
		this.add(playerWeaponLabel);
		this.add(playerLevelLabel);
		this.add(playerExpLabel);
		this.add(playerPositionLabel);
	}

	public void show(List<GameObject> objects) {
		if (focusedObject != null) {
			playerHPLabel.setText("Player HP:" + Integer.toString(focusedObject.getHealth()));
			playerWeaponLabel.setText("Player weapon:" + focusedObject.getWeapon());
			playerLevelLabel.setText("Player level:" + focusedObject.getLevel());
			playerExpLabel
					.setText("Exp:" + focusedObject.getCurrentExperience() + "/" + focusedObject.getExperienceForUp());
			playerPositionLabel.setText("Position: " + focusedObject.getMover().getPos());
		}
		repaint();
	}

}
