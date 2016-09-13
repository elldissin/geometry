package my.games.geometry.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClientWindow implements Runnable {

	private Client clientToDisplay;

	public ClientWindow(Client client) {
		clientToDisplay = client;
	}

	@SuppressWarnings("unused")
	private ClientWindow() {
		// TO DISABLE WINDOW WITHOUT CLIENT
	}

	@Override
	public void run() {
		if (clientToDisplay != null) {
			JFrame frame = new JFrame("Geometry game");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 700);
			frame.setResizable(false);
			frame.add(clientToDisplay.getRenderEngine().getCamera(1), BorderLayout.LINE_START);
			frame.add(clientToDisplay.getRenderEngine().getCamera(2), BorderLayout.LINE_END);
			frame.add(clientToDisplay.getRenderEngine().getCamera(3), BorderLayout.PAGE_END);
			frame.setVisible(true);
			frame.setFocusable(true); // important call to allow listening to keys
			frame.addKeyListener(clientToDisplay.getController());
		} else
			System.out.println("Client to display was not set");
	}

}
