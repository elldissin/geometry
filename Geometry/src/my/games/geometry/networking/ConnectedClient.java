package my.games.geometry.networking;

import java.io.IOException;
import java.net.Socket;

import my.games.geometry.UniqueIdProvider;

//TODO add code to close connections on exceptions
public class ConnectedClient {
	private int clientID;
	private ClientConnection connection;

	public ConnectedClient(Socket socket) {
		clientID = UniqueIdProvider.getID();
		try {
			connection = new ClientConnection(socket);
		} catch (IOException e) {
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

	public void sendMessage(NetworkMessage message) {
		if (connection != null) {
			try {
				System.out.println("Writing to stream: " + message.getEvent().getCarriedObject());
				connection.getOutputStream().writeObject(message);
				connection.getOutputStream().reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public PlayerInput getInput() {
		return connection.getInput();
	}

	public boolean isConnected() {
		return connection.isConnected();
	}

}
