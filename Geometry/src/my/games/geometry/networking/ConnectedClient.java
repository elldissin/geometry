package my.games.geometry.networking;

import java.io.IOException;
import java.net.Socket;

//TODO add code to close connections on exceptions
public class ConnectedClient {
	private int clientID;
	private ClientConnection connection;

	public ConnectedClient(Socket socket) {
		try {
			connection = new ClientConnection(socket);
			clientID = (Integer) (connection.getInputStream().readObject());
			System.out.println("New client with ID=" + clientID + " is connected.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		new Thread(connection).start();
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public PlayerInput getInput() {
		return connection.getInput();
	}

	public boolean isConnected() {
		return connection.isConnected();
	}

	public void sendMessagePacket(NetworkMessagePacket messagePacket) {
		if (connection != null) {
			try {
				connection.getOutputStream().writeObject(messagePacket);
				connection.getOutputStream().reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
