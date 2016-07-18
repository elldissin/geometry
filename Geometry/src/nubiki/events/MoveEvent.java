package nubiki.events;

import nubiki.game.Controllable;
import nubiki.game.GameObjectManager;

public class MoveEvent extends GameEvent {
	public MoveEvent(int targetID) {
		super(targetID);
	}
	
	@Override
	public void doEvent(GameObjectManager mng) {
		if (mng.getObjectByID(targetID) instanceof Controllable) {
			Controllable target = (Controllable)(mng.getObjectByID(targetID));
			target.setMoving();
		}
	}
}
