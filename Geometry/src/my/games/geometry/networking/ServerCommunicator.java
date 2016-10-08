package my.games.geometry.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerCommunicator {

	private int objectIDassignedByServer;
	private String hostName;
	private int portNumber = 4444;
	private int totalEventsCount;
	private Socket serverConnectSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Queue<NetworkMessage> messageQueue;

	public ServerCommunicator() {
		messageQueue = new ConcurrentLinkedQueue<NetworkMessage>();
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
			if (serverConnectSocket != null)
				serverConnectSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openConnectionTo(String hostName, int clientID) {
		try {
			this.hostName = hostName;
			serverConnectSocket = new Socket(hostName, portNumber);
			out = new ObjectOutputStream(serverConnectSocket.getOutputStream());
			in = new ObjectInputStream(serverConnectSocket.getInputStream());
			registerClientOnServer(clientID);
			// This part is continously waiting for events from server
			Thread inputThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						NetworkMessagePacket messagePacketFromServer;
						NetworkMessage msg = null;
						while ((messagePacketFromServer = (NetworkMessagePacket) in.readObject()) != null) {
							totalEventsCount += messagePacketFromServer.size();
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
			System.out.println("Server not started?");
			closeConnection();
			System.exit(1);
		}
	}

	private void registerClientOnServer(int clientID) throws IOException {
		out.writeObject(clientID);
		objectIDassignedByServer = in.readInt();
	}

	public int getObjectIDassignedByServer() {
		return objectIDassignedByServer;
	}
}
