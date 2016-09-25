package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

/**
 * @author LCrystal Special subclass that represents empty event, required to avoid null in
 *         GameEvent returning methods
 */
public class NoEvent extends GameEvent {

	public NoEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {

	}

}
