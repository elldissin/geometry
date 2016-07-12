package networking;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerCommunicator {

	private String hostName;
	private int portNumber=4444;
	private Socket mySocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ServerCommunicator() {

	}

	public void sendEvent(KeyEvent event) {
		KeyEvent eventFromServer;
	}

	public void closeConnection() {
		try {
			out.close();
			in.close();
			mySocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectToServer(String hostName) {
		try {
			this.hostName = hostName;
			mySocket = new Socket(hostName, portNumber);
			out = new ObjectOutputStream(mySocket.getOutputStream());
			in = new ObjectInputStream(mySocket.getInputStream());
			System.out.println("Connection with server established");

			//This part is continously waiting and reading for user input
			//		Thread inputThread = new Thread (new Runnable(){
			//			@Override
			//			public void run() {
			//				try {
			//					String fromUser="";
			//					while ((fromUser = stdIn.readLine()) != null)
			//						if (fromUser != null)
			//							out.writeObject(new KeyEvent(fromUser));
			//				} catch (IOException e) {
			//					// TODO Auto-generated catch block
			//					e.printStackTrace();
			//				}
			//			}});
			//		inputThread.start();
			KeyEvent fromServer;
			while ((fromServer = (KeyEvent)in.readObject()) != null) {
				//			System.out.println(fromServer.getMessage());
				if (fromServer.equals("Server is stopping")) {
					break;
				}
			}
		}
		catch (IOException e ) {
			System.out.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
