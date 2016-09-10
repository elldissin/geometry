package my.games.geometry.events;

import java.io.Serializable;

import my.games.geometry.game.World;

public abstract class GameEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int targetID;

	public GameEvent(int targetID) {
		this.targetID = targetID;
	}

	public int getTargetID() {
		return targetID;
	}

	public abstract void applyEventToWorld(World world);

}