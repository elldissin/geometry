package my.games.geometry.networking;

import java.util.ArrayList;

public class JoinClientService implements Runnable {

	private ArrayList<ConnectedClient> clientList;
	private boolean isRunning = false;

	public JoinClientService() {
		clientList = new ArrayList<ConnectedClient>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public void start() {
		new Thread(this).start();
	}

}
