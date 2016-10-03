package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class MoveEvent extends GameEvent {
	private static final long serialVersionUID = 1L;

	public MoveEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = world.getObjectByID(sourceObject.getObjectID());
		if (obj != null) {
			obj.move();
		}
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new MoveEvent(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}
}
