package my.games.geometry.networking;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import my.games.geometry.events.EventObserver;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;

public class GameEventObserver implements EventObserver, Serializable {
	// LATER why ClientEventNotifier is sent over network too?
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

	public Queue<GameEvent> processEventQueue() {
		// FIXME deep copy the queue before clearing
		synchronized (eventsQueue) {
			if (eventsQueue.size() > 0) {
				Queue<GameEvent> copyOfEventQueue = new LinkedList<GameEvent>(eventsQueue);
				eventsQueue.clear();
				return copyOfEventQueue;
			}
		}
		return new LinkedList<GameEvent>();
	}

}
