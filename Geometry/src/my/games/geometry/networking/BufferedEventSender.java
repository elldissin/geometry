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
	private int totalEventsCount;

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
		synchronized (messagePacket) {
			messagePacket.addMessage(message);
		}
	}

	private void sendBufferToClients() {
		synchronized (messagePacket) {
			if (messagePacket.size() > 0) {
				totalEventsCount += messagePacket.size();
				System.out.println("Total sent:" + totalEventsCount);
				for (int i = 0; i < clientList.size(); i++) {
					clientList.get(i).sendMessagePacket(messagePacket);
				}
			}
			messagePacket.clear();
		}
	}

	public void sendMessageTo(NetworkMessage message, List<ConnectedClient> clientList) {
		this.clientList = clientList;
		messagePacket.addMessage(message);
	}
}
