package my.games.geometry.networking;

import java.io.Serializable;

import my.games.geometry.events.GameEvent;

public class NetworkMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private int keyCode;
	private GameEvent event;

	public NetworkMessage() {
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public GameEvent getEvent() {
		return event;
	}

	public void setEvent(GameEvent ev) {
		event = ev;
	}
}
