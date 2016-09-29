package my.games.geometry.networking;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import my.games.geometry.events.EventObserver;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;

public class ClientEventNotifier implements EventObserver, Serializable {
	// LATER why ClientEventNotifier is sent over network too?
	private Queue<GameEvent> eventsQueue;

	public ClientEventNotifier() {
		eventsQueue = new LinkedList<GameEvent>();
	}

	@Override
	public void notifyAboutEvent(GameObject obj, GameEvent event) {
		eventsQueue.add(event);
	}

	public Queue<GameEvent> processEventQueue() {
		// LATER check for multithreading issues etc
		Queue<GameEvent> copyOfEventQueue = new LinkedList<GameEvent>(eventsQueue);
		eventsQueue.clear();
		return copyOfEventQueue;
	}

}
