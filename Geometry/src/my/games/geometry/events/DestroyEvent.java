package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class DestroyEvent extends GameEvent {

	public DestroyEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = getSourceObject();
		if (obj != null) {
			world.destroyGameObject(obj);
		}
	}

}
