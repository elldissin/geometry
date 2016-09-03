package my.games.geometry.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

//TODO add code to close connections on exceptions
public class ClientConnection implements AutoCloseable, Runnable {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private boolean isConnected = false;
	private Queue<PlayerInput> inputQueue;

	public ClientConnection(Socket socket) throws IOException {
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		inputQueue = new LinkedList<PlayerInput>();
		isConnected = true;
	}

	public ObjectOutputStream getOutputStream() throws IOException {
		return out;
	}

	public ObjectInputStream getInputStream() throws IOException {
		return in;
	}

	@Override
	public void close() throws IOException {
		if (out != null)
			out.close();
		if (in != null)
			in.close();
		if (socket != null)
			socket.close();
	}

	@Override
	public void run() {
		// System.out.println("Another connection is started in new thread");
		// The below is continously scanning for new input from clients
		PlayerInput inputFromPlayer = null;
		try {
			while ((inputFromPlayer = (PlayerInput) in.readObject()) != null) {
				inputQueue.add(inputFromPlayer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			isConnected = false;
			e.printStackTrace();
		}
	}

	public PlayerInput getInput() {
		return inputQueue.poll();
	}

	public boolean isConnected() {
		return isConnected;
	}
}
