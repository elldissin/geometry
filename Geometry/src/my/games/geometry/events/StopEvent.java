package my.games.geometry.events;

public class StopEvent extends GameEvent {
	private static final long serialVersionUID = 1L;

	public StopEvent(int targetID) {
		super(targetID);
		eventType=EventType.STOP;
	}

	@Override
	public EventType getEventType() {
		return eventType;	
	}
}