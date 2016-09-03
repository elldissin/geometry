package my.games.geometry.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionWaiter implements Runnable {

	private volatile Queue<Socket> connectionSocketQueue;
	int portNumber = 4444;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;

	public ConnectionWaiter() {
		connectionSocketQueue = new LinkedList<Socket>();
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Waiting for new connection...");
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("New connection accepted from " + clientSocket.getInetAddress());
			connectionSocketQueue.add(clientSocket);
		}
	}

	public Socket acceptConnection() {
		return connectionSocketQueue.poll();
	}
}
