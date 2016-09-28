package my.games.geometry.networking;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LCrystal Contains several NetworkMessages and transfers them as a whole over network
 *         between server and client.
 */
public class NetworkMessagePacket implements Serializable {

	private static final long serialVersionUID = 1L;
	private Queue<NetworkMessage> messageQueue;

	public NetworkMessagePacket() {
		messageQueue = new LinkedList<NetworkMessage>();
	}

	public NetworkMessage getNextMessage() {
		return messageQueue.poll();
	}

	public void addMessage(NetworkMessage message) {
		messageQueue.add(message);
	}
}
