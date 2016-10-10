package my.games.geometry.events.util;

import my.games.geometry.events.GameEvent;
import my.games.geometry.game.World;

/**
 * 
 * @author LCrystal This class takes event and delegates world to event, which
 *         can do changes to world depending of what the event type is
 */
public class EventHandler {
	private World world;

	public EventHandler(World world) {
		this.world = world;
	}

	public void handleEvent(GameEvent event) {
		event.applyEventToWorld(world);
	}
}
