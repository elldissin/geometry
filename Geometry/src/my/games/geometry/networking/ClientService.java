package my.games.geometry.networking;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientService implements Runnable {

	private List<ConnectedClient> clientList;
	private boolean isRunning = false;

	public ClientService() {
		clientList = Collections.synchronizedList(new ArrayList<ConnectedClient>());
	}

	@Override
	public void run() {
		Socket newConnect;
		ConnectionWaiter waiter = new ConnectionWaiter();
		while (true) {
			if ((newConnect = waiter.acceptConnection()) != null) {
				ConnectedClient client = new ConnectedClient(newConnect);
				clientList.add(client);
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

	public List<ConnectedClient> getClientList() {
		return clientList;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
