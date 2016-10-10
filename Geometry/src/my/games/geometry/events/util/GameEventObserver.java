package my.games.geometry.events.util;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;

public class GameEventObserver implements EventObserver, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// LATER is it required to be Serializable?
	private Queue<GameEvent> eventsQueue;

	public GameEventObserver() {
		eventsQueue = new ConcurrentLinkedQueue<GameEvent>();
	}

	@Override
	public void notifyAboutEvent(GameObject obj, GameEvent event) {
		synchronized (eventsQueue) {
			eventsQueue.add(event);
		}
	}

	public Queue<GameEvent> getCopyForProcessing() {
		Queue<GameEvent> copyOfEventQueue = new ConcurrentLinkedQueue<GameEvent>();
		synchronized (eventsQueue) {
			while (eventsQueue.size() > 0) {
				copyOfEventQueue.add(eventsQueue.poll().copy()); // deep copy here!
			}
		}
		return copyOfEventQueue;
	}

}
