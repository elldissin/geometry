package my.games.geometry.events.util;

import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;

public interface EventObserver {
	public void notifyAboutEvent(GameObject obj, GameEvent event);
	// LATER no need in GameObject?
}
