package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import my.games.geometry.game.Client;

public class LoginButtonListener implements ActionListener {
	private ClientWindow clientWindow;
	private Client client;
	private LoginPanel panel;

	public LoginButtonListener(Client client, ClientWindow clientWindow, LoginPanel panel) {
		this.client = client;
		this.clientWindow = clientWindow;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel bottomStatusBar = client.getRenderEngine().getCamera(3);
		bottomStatusBar.setPreferredSize(new Dimension(800, 65));
		clientWindow.getContentPane().removeAll();
		clientWindow.add(bottomStatusBar);
		clientWindow.add(client.getRenderEngine().getCamera(1));
		clientWindow.repaint();
		clientWindow.pack();
		clientWindow.setVisible(true);
		client.setClientID(Integer.parseInt(panel.loginField.getText()));
		client.start(); // first set the ID, then start!
		clientWindow.addKeyListener(client.getController()); // add controller from running client
	}

}
