package my.games.geometry.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import my.games.geometry.game.Client;

public class ClientWindow extends JFrame implements Runnable {

	private Client client;

	public ClientWindow(Client client) {
		this.client = client;
	}

	@SuppressWarnings("unused")
	private ClientWindow() {
		// TO DISABLE WINDOW WITHOUT CLIENT
	}

	@Override
	public void run() {
		if (client != null) {
			// JFrame frame = new JFrame("Geometry game");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setPreferredSize(new Dimension(800, 700));
			this.setResizable(true);
			this.setLayout(new GridLayout(2, 1));

			LoginPanel loginpanel = new LoginPanel(client, this);
			loginpanel.setPreferredSize(new Dimension(70, 95));
			this.add(loginpanel);
			this.setFocusable(true); // important call to allow listening to
										// keys
			this.pack();
			this.setVisible(true);
		} else
			System.out.println("Client to display was not set");
	}

}
