package my.games.geometry.networking;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import my.games.geometry.events.GameEvent;
import my.games.geometry.events.util.EventObserver;
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
		Queue<GameEvent> copyOfEventQueue = new ConcurrentLinkedQueue<GameEvent>();
		synchronized (eventsQueue) {
			while (eventsQueue.size() > 0) {
				copyOfEventQueue.add(eventsQueue.poll().copy());
			}
		}
		return copyOfEventQueue;
	}

}
