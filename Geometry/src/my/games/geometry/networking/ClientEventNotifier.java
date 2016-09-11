package my.games.geometry.networking;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import my.games.geometry.events.EventObserver;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;

public class ClientEventNotifier implements EventObserver, Serializable {

	private Queue<GameEvent> eventsQueue;

	public ClientEventNotifier() {
		eventsQueue = new LinkedList<GameEvent>();
	}

	@Override
	public void notifyAboutEvent(GameObject obj, GameEvent event) {
		eventsQueue.add(event);
	}

	public void notifyClients(List<ConnectedClient> clientList) {
		NetworkMessage msg = new NetworkMessage();
		if (eventsQueue.size() > 0) {
			msg.setEvent(eventsQueue.poll());
			for (int i = 0; i < clientList.size(); i++)
				clientList.get(i).sendMessage(msg);
		}
	}

}
