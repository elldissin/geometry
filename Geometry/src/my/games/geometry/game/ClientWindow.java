package my.games.geometry.game;

import java.awt.BorderLayout;

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
			this.setSize(800, 700);
			this.setResizable(false);
			this.setLayout(new BorderLayout());
			// this.add(clientToDisplay.getRenderEngine().getCamera(1),
			// BorderLayout.LINE_START);
			// this.add(clientToDisplay.getRenderEngine().getCamera(2),
			// BorderLayout.LINE_END);
			// this.add(clientToDisplay.getRenderEngine().getCamera(3),
			// BorderLayout.PAGE_END);
			this.add(new LoginPanel(client, this), BorderLayout.LINE_START);
			this.setVisible(true);
			this.setFocusable(true); // important call to allow listening to
										// keys
			this.addKeyListener(client.getController());
		} else
			System.out.println("Client to display was not set");
	}

}
