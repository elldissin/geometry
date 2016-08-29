package my.games.geometry.events;

import java.util.LinkedList;
import java.util.Queue;

import geometry.networking.NetworkMessage;
import geometry.networking.events.GameEvent;
import my.games.geometry.networking.ServerCommunicator;

/**
 * Represents a source of events affecting the game world taken from remote
 * server (can be local server as well)
 */
public class RemoteSource implements EventSource {
	private ServerCommunicator communicator;
	private Queue<GameEvent> eventsQueue;

	public RemoteSource(ServerCommunicator communicator) {
		eventsQueue = new LinkedList<GameEvent>();
		this.communicator = communicator;
	}

	@Override
	public boolean hasNext() {
		NetworkMessage nm = null;
		if ((nm = communicator.getNextMessage()) != null) {
			GameEvent event = nm.getEvent();
			if (event != null) {
				eventsQueue.add(event);
				return true;
			}
		}
		return false;
	}

	@Override
	public GameEvent getNext() {
		return eventsQueue.poll();
	}

}
