package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;

public class ProjectileMover extends GeneralMover implements Mover, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int liveDistance;
	private int distTravelled;

	public ProjectileMover(GameObject ownerObject, ObjectPosition position, double angle) {
		super(ownerObject, position, angle);
		liveDistance = 400;
		speed = 7;
		isMoving = true;
	}

	@Override
	public void moveIfMoving(double delta) {
		if (isMoving) {
			super.moveIfMoving(delta);
			distTravelled += Math.sqrt(Math.pow(getSpeedX(), 2) + Math.pow(getSpeedY(), 2));
		}
		if (distTravelled > liveDistance) {
			isMoving = false;
			ownerObject.destroy();
		}
	}

	@Override
	public Mover copy() {
		ProjectileMover copy = new ProjectileMover(ownerObject.copy(), this.currentPos.copy(), this.angle);
		copy.liveDistance = this.liveDistance;
		copy.distTravelled = this.distTravelled;
		finishCopy(copy);
		return copy;
	}

}
