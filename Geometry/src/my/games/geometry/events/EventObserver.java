package my.games.geometry.events;

import my.games.geometry.game.objects.GameObject;

public interface EventObserver {
	public void notifyAboutEvent(GameObject obj);
}
