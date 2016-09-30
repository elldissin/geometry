package my.games.geometry.networking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

public class BufferedEventSender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NetworkMessagePacket messagePacket;
	private List<ConnectedClient> clientList;
	private Timer timer;

	public BufferedEventSender() {
		messagePacket = new NetworkMessagePacket();
		timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendBufferToClients();
			}
		});
		timer.start();
	}

	public void sendMessage(NetworkMessage message) {
		messagePacket.addMessage(message);
	}

	private void sendBufferToClients() {
		if (messagePacket.size() > 0) {
			for (int i = 0; i < clientList.size(); i++) {
				clientList.get(i).sendMessagePacket(messagePacket);
				System.out.println("buffer sent:" + messagePacket.size());
			}
		}
		messagePacket.clear();
	}

	public void sendMessageTo(NetworkMessage message, List<ConnectedClient> clientList) {
		this.clientList = clientList;
		messagePacket.addMessage(message);
		messagePacket.addMessage(message);
		System.out.println("message with event added to buffer: " + message.getEvent() + " , obj:"
				+ message.getEvent().getSourceObject());
	}
}
