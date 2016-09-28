package my.games.geometry.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ServerCommunicator {

	private String hostName;
	private int portNumber = 4444;
	private Socket mySocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Queue<NetworkMessage> eventsQueue;

	public ServerCommunicator() {
		eventsQueue = new LinkedList<NetworkMessage>();
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
		return eventsQueue.poll();
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
						NetworkMessage fromServer;
						while ((fromServer = (NetworkMessage) in.readObject()) != null) {
							eventsQueue.add(fromServer);
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
