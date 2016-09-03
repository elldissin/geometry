package my.games.geometry.events;

import java.io.Serializable;

public abstract class GameEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum EventType {STOP, MOVE, TURNCW, TURNCCW, SHOOT}
	protected int targetID;
	protected EventType eventType;
	public GameEvent(int targetID) {
		this.targetID = targetID;
	}


	public abstract EventType getEventType();
	
	public int getTargetID() {
		return targetID;
	}
}