package my.games.geometry.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerCommunicator {

	private String hostName;
	private int portNumber = 4444;
	private int totalEventsCount;
	private Socket mySocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Queue<NetworkMessage> messageQueue;

	public ServerCommunicator() {
		messageQueue = new ConcurrentLinkedQueue<NetworkMessage>();
	}

	public void sendMessage(NetworkMessage event) {
		if (out != null)
			try {
				out.writeObject(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void sendInput(PlayerInput input) {
		if (out != null)
			try {
				out.writeObject(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public NetworkMessage getNextMessage() {
		synchronized (messageQueue) {
			return messageQueue.poll();
		}
	}

	public void closeConnection() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (mySocket != null)
				mySocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openConnectionTo(String hostName, int clientID) {
		try {
			this.hostName = hostName;
			mySocket = new Socket(hostName, portNumber);
			out = new ObjectOutputStream(mySocket.getOutputStream());
			in = new ObjectInputStream(mySocket.getInputStream());
			out.writeObject(clientID);

			// This part is continously waiting for events from server
			Thread inputThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						NetworkMessagePacket messagePacketFromServer;
						NetworkMessage msg = null;
						while ((messagePacketFromServer = (NetworkMessagePacket) in.readObject()) != null) {
							totalEventsCount += messagePacketFromServer.size();
							System.out.println("Total received:" + totalEventsCount);
							while ((msg = messagePacketFromServer.getNextMessage()) != null) {
								synchronized (messageQueue) {
									messageQueue.add(msg);
								}
							}
							// TODO add something to exit properly
						}
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			inputThread.start();
		} catch (IOException e) {
			System.out.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}
