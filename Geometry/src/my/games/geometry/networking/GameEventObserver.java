package my.games.geometry.networking;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import my.games.geometry.events.EventObserver;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;

public class GameEventObserver implements EventObserver, Serializable {
	// LATER why ClientEventNotifier is sent over network too?
	private Queue<GameEvent> eventsQueue;

	public GameEventObserver() {
		eventsQueue = new LinkedList<GameEvent>();
	}

	@Override
	public void notifyAboutEvent(GameObject obj, GameEvent event) {
		eventsQueue.add(event);
	}

	public Queue<GameEvent> processEventQueue() {
		// FIXME check for multithreading issues etc
		if (eventsQueue.size() > 0) {
			Queue<GameEvent> copyOfEventQueue = new LinkedList<GameEvent>(eventsQueue);
			eventsQueue.clear();
			return copyOfEventQueue;
		}
		return new LinkedList<GameEvent>();
	}

}
