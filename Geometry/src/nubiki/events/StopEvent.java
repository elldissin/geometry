package nubiki.events;

import nubiki.game.Controllable;
import nubiki.game.GameObjectManager;

public class StopEvent extends GameEvent {
	private int targetID;

	public StopEvent(int targetID) {
		super(targetID);
	}

	@Override
	public void doEvent(GameObjectManager mng) {
		if (mng.getObjectByID(targetID) instanceof Controllable) {
			Controllable target = (Controllable)(mng.getObjectByID(targetID));
			target.setStopped();
		}
	}
}
