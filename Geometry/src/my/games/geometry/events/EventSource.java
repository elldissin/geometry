package my.games.geometry.events;

import geometry.networking.events.GameEvent;

public interface EventSource {

	public boolean hasNext();

	public GameEvent getNext();
}
