package my.games.geometry.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import my.games.geometry.events.CreateEvent;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ClientService implements Runnable {

	private List<ConnectedClient> clientList;
	private List<ConnectedClient> newClientList;
	private boolean isRunning = false;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private int portNumber = 4444;

	public ClientService() {
		clientList = Collections.synchronizedList(new ArrayList<ConnectedClient>());
		newClientList = Collections.synchronizedList(new ArrayList<ConnectedClient>());
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Waiting for new connection...");
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			newClientList.add(new ConnectedClient(clientSocket));
			System.out.println("New connection accepted from " + clientSocket.getInetAddress());
			try {
				Thread.sleep(5); // To reduce CPU load
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		new Thread(this).start();
	}

	public void sendWorldStateToNewClients(World world) {
		NetworkMessage msg = new NetworkMessage();
		GameEvent event = null;
		for (int i = 0; i < newClientList.size(); i++) {
			for (int j = 0; j < world.getGameObjectsList().size(); j++) {
				GameObject object = world.getGameObjectsList().get(j);
				event = new CreateEvent(object.getObjectID());
				event.setSourceObject(object);
				msg.setEvent(event);
				newClientList.get(i).sendMessage(msg);
			}
			clientList.add(newClientList.get(i));
		}
		newClientList.clear(); // consider all new clients became old (notified)
	}

	public List<ConnectedClient> getClientList() {
		return clientList;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
