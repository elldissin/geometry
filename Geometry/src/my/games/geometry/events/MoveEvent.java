package my.games.geometry.events;

public class MoveEvent extends GameEvent {
	private static final long serialVersionUID = 1L;

	public MoveEvent(int targetID) {
		super(targetID);
		eventType=EventType.MOVE;
	}
	
	@Override
	public EventType getEventType() {
		return eventType;	
	}
}