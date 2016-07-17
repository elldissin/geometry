package nubiki.events;

import java.util.LinkedList;
import java.util.Queue;

public class EventManager {
	private Queue<GameEvent> eventsQueue;
	
	public EventManager() {
		eventsQueue=new LinkedList<GameEvent>();
	}
	
	public GameEvent nextEvent() {
//		System.out.println("New event requested from queue");
		return eventsQueue.poll();
	}
	
	public void addEvent(GameEvent ev) {
//		System.out.println("New event added to queue");
		eventsQueue.add(ev);
	}
}