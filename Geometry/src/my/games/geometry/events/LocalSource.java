package my.games.geometry.events;

import java.util.LinkedList;
import java.util.Queue;

public class LocalSource implements EventSource {

	private Queue<GameEvent> eventsQueue;

	public LocalSource() {
		eventsQueue = new LinkedList<GameEvent>();
	}

	@Override
	public boolean hasNext() {
		if (eventsQueue.size() > 0)
			return true;
		return false;
	}

	@Override
	public GameEvent getNext() {
		return eventsQueue.poll();
	}

	@Override
	public void addEvent(GameEvent event) {
		eventsQueue.add(event);
	}

}
