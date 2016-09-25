package my.games.geometry.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		clientWindow.getContentPane().removeAll();
		clientWindow.getContentPane().add(client.getRenderEngine().getCamera(3), BorderLayout.PAGE_START);
		clientWindow.getContentPane().add(client.getRenderEngine().getCamera(3), BorderLayout.PAGE_END);
		// JButton loginButt1 = new JButton();
		// loginButt1.setText("Log in");
		// loginButt1.setBounds(280, 205, 70, 30);
		// clientWindow.add(loginButt1, BorderLayout.PAGE_END);
		// clientWindow.add(clientToDisplay.getRenderEngine().getCamera(3),
		// BorderLayout.PAGE_END);
		clientWindow.repaint();
	}

}
