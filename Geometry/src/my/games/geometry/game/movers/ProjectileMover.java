package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public class ProjectileMover extends GeneralMover implements Mover, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int liveDistance;
	private int distTravelled;

	public ProjectileMover(GameObject objectToMove) {
		super(objectToMove);
		liveDistance = 400;
		speed = 7;
	}

	@Override
	public void move() {
		if (isMoving) {
			super.move();
			distTravelled += Math.sqrt(Math.pow(getSpeedX(), 2) + Math.pow(getSpeedY(), 2));
		}
		if (distTravelled > liveDistance) {
			objectToMove.setObsolete(true);
		}
	}

	@Override
	public Mover copy() {
		ProjectileMover copy = new ProjectileMover(objectToMove.copy());
		copy.liveDistance = this.liveDistance;
		finishCopy(copy);
		return copy;
	}

}
