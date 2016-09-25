package my.games.geometry.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

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
			this.setLayout(new BorderLayout());
			// this.add(clientToDisplay.getRenderEngine().getCamera(1),
			// BorderLayout.LINE_START);
			// this.add(clientToDisplay.getRenderEngine().getCamera(2),
			// BorderLayout.LINE_END);
			// this.add(clientToDisplay.getRenderEngine().getCamera(3),
			// BorderLayout.PAGE_END);
			LoginPanel loginpanel = new LoginPanel(client, this);
			loginpanel.setPreferredSize(new Dimension(70, 65));
			this.add(loginpanel, BorderLayout.PAGE_START);
			this.setFocusable(true); // important call to allow listening to
										// keys
			this.addKeyListener(client.getController());
			this.pack();
			this.setVisible(true);
		} else
			System.out.println("Client to display was not set");
	}

}
