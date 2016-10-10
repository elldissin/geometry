package my.games.geometry.events.streams;

import my.games.geometry.events.GameEvent;

public interface EventStream {

	public boolean hasNext();

	public GameEvent getNext();

	public void addEvent(GameEvent event);
}
