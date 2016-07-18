package nubiki.events;

import nubiki.game.Controllable;
import nubiki.game.GameObjectManager;

public class MoveEvent extends GameEvent {
	public MoveEvent(int targetID) {
		super(targetID);
	}
	
	@Override
	public void doEvent() {
		if (GameObjectManager.getObjectByID(targetID) instanceof Controllable) {
			Controllable target = (Controllable)(GameObjectManager.getObjectByID(targetID));
			target.setMoving();
		}
	}
}
