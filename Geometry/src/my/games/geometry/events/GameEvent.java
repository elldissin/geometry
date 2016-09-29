package my.games.geometry.events;

import java.io.Serializable;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public abstract class GameEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	// FIXME bumping make a bug with bullets position (if player in the static
	// object)
	protected GameObject sourceObject;
	protected long timeStamp;

	public GameEvent(GameObject sourceObject) {
		timeStamp = System.currentTimeMillis();
		this.sourceObject = sourceObject;
	}

	public GameObject getSourceObject() {
		return sourceObject;
	}

	public void setSourceObject(GameObject sourceObject) {
		this.sourceObject = sourceObject;
	}

	public abstract void applyEventToWorld(World world);

	public long getTimeStamp() {
		return timeStamp;
	}
}