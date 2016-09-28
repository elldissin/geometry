package my.games.geometry.networking;

import java.io.Serializable;
import java.util.List;

public class BufferedEventSender implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NetworkMessagePacket messagePacket;
	List<ConnectedClient> clientList;

	public BufferedEventSender() {
		messagePacket = new NetworkMessagePacket();
	}

	public void sendMessage(NetworkMessage message) {
		messagePacket.addMessage(message);
	}

	private void sendBufferToClients() {
		for (int i = 0; i < clientList.size(); i++)
			clientList.get(i).sendMessagePacket(messagePacket);
	}

	public void sendMessageTo(NetworkMessage message, List<ConnectedClient> clientList) {
		this.clientList = clientList;
		messagePacket.addMessage(message);
	}
}
