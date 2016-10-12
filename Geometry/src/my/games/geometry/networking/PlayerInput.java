package my.games.geometry.networking;

import java.io.Serializable;

public class PlayerInput implements Serializable {

	/**
	 * Carries input and client ID (keys pressed) from client to server
	 */
	private static final long serialVersionUID = 1L;
	private int clientID;
	private int keyCode;
	private boolean isKeyPressed;

	public PlayerInput(int clientID, int keyCode, boolean isKeyPressed) {
		this.clientID = clientID;
		this.keyCode = keyCode;
		this.isKeyPressed = isKeyPressed;
	}

	public int getClientID() {
		return clientID;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public boolean isKeyPressed() {
		return isKeyPressed;
	}
}
