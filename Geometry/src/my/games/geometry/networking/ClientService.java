package my.games.geometry.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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
		clientList = new CopyOnWriteArrayList<ConnectedClient>();
		newClientList = new CopyOnWriteArrayList<ConnectedClient>();
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
			ConnectedClient newClient = new ConnectedClient(clientSocket);
			newClientList.add(newClient);
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

	public void sendWorldStateToNewClients(World world, Map<Integer, Integer> clientToPlayerMap) {
		for (int i = 0; i < newClientList.size(); i++) {
			createPlayerForClient(newClientList.get(i), world, clientToPlayerMap);
			NetworkMessagePacket messagePacket = new NetworkMessagePacket();
			for (int j = 0; j < world.getGameObjectsList().size(); j++) {
				NetworkMessage msg = new NetworkMessage();
				GameObject object = world.getGameObjectsList().get(j);
				GameEvent event = new CreateEvent(object);
				msg.setEvent(event);
				messagePacket.addMessage(msg);
			}
			newClientList.get(i).sendMessagePacket(messagePacket);
			clientList.add(newClientList.get(i));
		}
		newClientList.clear(); // consider all new clients became old (notified)
	}

	private void createPlayerForClient(ConnectedClient client, World world, Map<Integer, Integer> clientToPlayerMap) {
		GameObject newPlayer = world.addNewConnectedPlayer(client.getClientID());
		clientToPlayerMap.put(client.getClientID(), newPlayer.getObjectID());
	}

	public List<ConnectedClient> getClientList() {
		return clientList;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
