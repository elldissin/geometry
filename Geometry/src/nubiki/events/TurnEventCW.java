package nubiki.events;

public class TurnEventCW extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurnEventCW(int targetID) {
		super(targetID);
		eventType=EventType.TURNCW;
	}
	
	@Override
	public EventType doEvent() {
		return eventType;
	}

}
