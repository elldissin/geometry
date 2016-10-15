package my.games.geometry.ui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import my.games.geometry.game.objects.GameObject;

public class GameStatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameObject displayedObject;
	private int width = 800;
	private int height = 65;
	JLabel playerHPLabel = new JLabel();
	JLabel playerWeaponLabel = new JLabel();
	JLabel playerLevelLabel = new JLabel();
	JLabel playerExpLabel = new JLabel();

	public GameStatusPanel(GameObject displayedObject) {
		super();
		this.displayedObject = displayedObject;
		setPreferredSize(new Dimension(width, height));
		if (displayedObject != null) {
			playerHPLabel.setText("Player HP:" + Integer.toString(displayedObject.getHealth()));
			playerWeaponLabel.setText("Player weapon:" + displayedObject.getWeapon());
			playerLevelLabel.setText("Player level:" + displayedObject.getLevel());
			playerExpLabel.setText(
					"Exp:" + displayedObject.getCurrentExperience() + "/" + displayedObject.getExperienceForUp());
			this.add(playerHPLabel);
			this.add(playerWeaponLabel);
			this.add(playerLevelLabel);
			this.add(playerExpLabel);
		}
	}

}
