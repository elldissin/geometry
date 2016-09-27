package my.games.geometry.networking;

import java.io.Serializable;

public class PlayerInput implements Serializable {

	/**
	 * Carries input and client ID (keys pressed) from client to server
	 */
	private static final long serialVersionUID = 1L;
	private int clientID;
	private int keyCode;

	public PlayerInput(int playerID, int keyCode) {
		this.clientID = playerID;
		this.keyCode = keyCode;
	}

	public int getClientID() {
		return clientID;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setClientID(int playerID) {
		this.clientID = playerID;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
}
