package my.games.geometry.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import my.games.geometry.game.engine.Client;

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
		DisplayElement gameCameraPanel = client.getRenderEngine().getCamera();
		DisplayElement gameStatusPanel = client.getRenderEngine().getStatusBar();
		clientWindow.getContentPane().removeAll();
		clientWindow.add(gameStatusPanel);
		clientWindow.add(gameCameraPanel);
		clientWindow.repaint();
		clientWindow.pack();
		clientWindow.setVisible(true);
		client.setClientID(Integer.parseInt(panel.loginField.getText()));
		client.start(); // first set the ID, then start!
		clientWindow.addKeyListener(client.getController()); // add controller
																// from running
																// client
	}

}
