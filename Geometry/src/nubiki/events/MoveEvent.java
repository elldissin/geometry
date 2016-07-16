package nubiki.events;

import nubiki.game.Controllable;

public class MoveEvent implements GameEvent {
	private Controllable target;
	
	public MoveEvent(Controllable target) {
		this.target=target;
	}
	
	@Override
	public void doEvent() {
		target.setMoving();
	}
}
