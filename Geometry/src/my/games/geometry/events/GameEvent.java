package my.games.geometry.events;

import java.io.Serializable;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public abstract class GameEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int targetID;
	private GameObject carriedObject;

	public GameObject getCarriedObject() {
		return carriedObject;
	}

	public void setCarriedObject(GameObject carriedObject) {
		this.carriedObject = carriedObject;
	}

	public GameEvent(int targetID) {
		this.targetID = targetID;
	}

	public int getTargetID() {
		return targetID;
	}

	public abstract void applyEventToWorld(World world);

}