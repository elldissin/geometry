package nubiki.events;

import nubiki.game.Controllable;
import nubiki.game.GameObjectManager;

public class StopEvent extends GameEvent {
	public StopEvent(int targetID) {
		super(targetID);
	}

	@Override
	public void doEvent() {
		if (GameObjectManager.getObjectByID(targetID) instanceof Controllable) {
			Controllable target = (Controllable)(GameObjectManager.getObjectByID(targetID));
			target.setStopped();
		}
	}
}
