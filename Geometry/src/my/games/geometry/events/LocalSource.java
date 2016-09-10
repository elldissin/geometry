package my.games.geometry.events;

import my.games.geometry.game.objects.GameObject;

public class LocalSource implements EventSource, EventObserver {

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameEvent getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyAboutEvent(GameObject obj) {
		// TODO Auto-generated method stub

	}

}
