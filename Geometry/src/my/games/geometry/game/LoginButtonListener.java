package my.games.geometry.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class LoginButtonListener implements ActionListener {
	private ClientWindow clientWindow;
	private Client client;

	// public ClientWindow(Client client) {
	// clientToDisplay = client;
	// }

	public LoginButtonListener(Client client, ClientWindow clientWindow) {
		this.client = client;
		this.clientWindow = clientWindow;
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
	}

}
