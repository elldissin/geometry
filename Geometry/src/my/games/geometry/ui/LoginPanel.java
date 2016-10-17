package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import my.games.geometry.game.engine.Client;

public class LoginPanel extends JPanel {

	private Client client;
	private ClientWindow clientwindow;
	private JLabel loginLabel, passwordLabel, serverIPLabel;
	public JTextField loginField, passwordField, serverIPField;

	LoginPanel(Client client, ClientWindow clientwindow) {
		this.clientwindow = clientwindow;
		this.client = client;

		this.setLayout(new GridLayout(4, 2, 0, 5));

		loginLabel = new JLabel("Login(numeric):");
		loginLabel.setPreferredSize(new Dimension(70, 30));
		this.add(loginLabel);

		loginField = new JTextField();
		loginField.setPreferredSize(new Dimension(70, 30));
		Integer i = (int) (Math.random() * 1000);
		loginField.setText(i.toString());
		this.add(loginField);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setPreferredSize(new Dimension(70, 30));
		this.add(passwordLabel);

		passwordField = new JTextField();
		passwordField.setPreferredSize(new Dimension(70, 30));
		this.add(passwordField);

		serverIPLabel = new JLabel("Server IP:");
		serverIPLabel.setPreferredSize(new Dimension(70, 30));
		this.add(serverIPLabel);

		serverIPField = new JTextField();
		serverIPField.setPreferredSize(new Dimension(70, 30));
		serverIPField.setText("localhost");
		this.add(serverIPField);

		JButton loginButt = new JButton();
		LoginButtonListener loginButtonListener = new LoginButtonListener(client, clientwindow, this);
		loginButt.setText("Log in");
		loginButt.addActionListener(loginButtonListener);
		loginButt.setPreferredSize(new Dimension(30, 30));
		this.add(loginButt);

	}

	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
