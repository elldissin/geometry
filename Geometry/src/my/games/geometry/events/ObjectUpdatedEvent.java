package my.games.geometry.events;

import my.games.geometry.game.engine.World;
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
				objToBeUpdated.getMover().setPos(sourceObject.getMover().getPos());
				objToBeUpdated.getMover().setAngle(sourceObject.getMover().getAngle());
				objToBeUpdated.setHealth(sourceObject.getHealth());
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
