package my.games.geometry.events;

public interface EventSource {

	public boolean hasNext();

	public GameEvent getNext();
}