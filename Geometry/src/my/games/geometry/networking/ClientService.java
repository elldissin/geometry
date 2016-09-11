package my.games.geometry.networking;

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

	public ClientService() {
		clientList = Collections.synchronizedList(new ArrayList<ConnectedClient>());
		newClientList = Collections.synchronizedList(new ArrayList<ConnectedClient>());
	}

	@Override
	public void run() {
		Socket newConnect;
		ConnectionWaiter waiter = new ConnectionWaiter();
		while (true) {
			if ((newConnect = waiter.acceptConnection()) != null) {
				ConnectedClient client = new ConnectedClient(newConnect);
				newClientList.add(client);
			}
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
			for (GameObject object : world.getGameObjectsMap().values()) {
				event = new CreateEvent(object.getObjectID());
				event.setCarriedObject(object);
				msg.setEvent(event);
				newClientList.get(i).sendMessage(msg);
			}
			clientList.add(newClientList.get(i));
		}
		newClientList.clear();
	}

	public List<ConnectedClient> getClientList() {
		return clientList;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
