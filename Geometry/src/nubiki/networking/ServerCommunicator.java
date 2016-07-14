package nubiki.networking;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ServerCommunicator {

	private String hostName;
	private int portNumber=4444;
	private Socket mySocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Queue<NetworkMessage> eventsQueue;
	
	public ServerCommunicator() {
		eventsQueue = new LinkedList<NetworkMessage>();
	}

	public void sendMessage(NetworkMessage event) {
		if (out!=null)
			try {
				out.writeObject(event);
				System.out.println("Event was sent to server");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public NetworkMessage getNextMessage() {
		return eventsQueue.poll();
	}

	public void closeConnection() {
		try {
			if(out!=null)
				out.close();
			if(in!=null)
				in.close();
			if(mySocket!=null)
				mySocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openConnectionTo(String hostName) {
		try {
			this.hostName = hostName;
			mySocket = new Socket(hostName, portNumber);
			out = new ObjectOutputStream(mySocket.getOutputStream());
			in = new ObjectInputStream(mySocket.getInputStream());
			System.out.println("Connection with server established");

			//This part is continously waiting for events from server
			Thread inputThread = new Thread (new Runnable(){
				@Override
				public void run() {
					try {
						NetworkMessage fromServer;
						System.out.println("Server listening thread started on client");
						while ((fromServer = (NetworkMessage)in.readObject()) != null) {
							eventsQueue.add(fromServer);
							//add something to exit properly							
						}
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}});
			inputThread.start();
		}
		catch (IOException e ) {
			System.out.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
	}
}
