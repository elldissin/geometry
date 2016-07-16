package nubiki.events;

import nubiki.game.Controllable;

public class StopEvent implements GameEvent {
	private Controllable target;

	public StopEvent(Controllable target) {
		this.target=target;
	}

	@Override
	public void doEvent() {
		target.setStopped();
	}
}
