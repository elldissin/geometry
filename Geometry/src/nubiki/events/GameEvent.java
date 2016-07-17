package nubiki.events;

import nubiki.game.GameObjectManager;

public abstract class GameEvent {
	private int targetID;

	public GameEvent(int targetID) {
		this.targetID = targetID;
	}

	public abstract void doEvent(GameObjectManager mng);
}
