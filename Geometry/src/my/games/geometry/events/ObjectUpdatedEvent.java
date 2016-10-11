package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ObjectUpdatedEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectUpdatedEvent(GameObject sourceObject) {
		super(sourceObject);
	}

	@Override
	public void applyEventToWorld(World world) {
		if (sourceObject != null) {
			GameObject objToBeUpdated = world.getObjectByID(sourceObject.getObjectID());
			if (objToBeUpdated != null) {
				objToBeUpdated.setPos(sourceObject.getPos());
				objToBeUpdated.setAngle(sourceObject.getAngle());
			}
		}
	}

	@Override
	public GameEvent copy() {
		GameEvent copy = new ObjectUpdatedEvent(this.sourceObject.copy());
		copy.timeStamp = this.timeStamp;
		return copy;
	}

}
