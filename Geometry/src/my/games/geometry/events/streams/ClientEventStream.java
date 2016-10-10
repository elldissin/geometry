package my.games.geometry.events.streams;

import java.util.LinkedList;
import java.util.Queue;

import my.games.geometry.events.GameEvent;

public class ClientEventStream implements EventStream {

	private Queue<GameEvent> eventsQueue;

	public ClientEventStream() {
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
