package my.games.geometry.networking;

import java.io.Serializable;

public class PlayerInput implements Serializable {

	/**
	 * Carries input and client ID (keys pressed) from client to server
	 */
	private static final long serialVersionUID = 1L;
	private int playerID;
	private int keyCode;

	public PlayerInput(int playerID, int keyCode) {
		this.playerID = playerID;
		this.keyCode = keyCode;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
}
