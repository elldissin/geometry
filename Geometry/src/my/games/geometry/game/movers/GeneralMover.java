package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.events.ObjectUpdatedEvent;
import my.games.geometry.game.ObjectPosition;
import my.games.geometry.game.objects.GameObject;

/**
 * @author LCrystal Provides general implementation of common Mover methods
 */
public abstract class GeneralMover implements Mover, Serializable {

	private static final long serialVersionUID = 1L;
	protected boolean isMoving;
	protected boolean isTurningCW;
	protected boolean isTurningCCW;
	protected int maxSpeed;
	protected int speed;
	protected double turnSpeed;
	protected GameObject objectToMove;

	public GeneralMover(GameObject objectToMove) {
		this.objectToMove = objectToMove;
		isMoving = false;
		isTurningCW = false;
		isTurningCCW = false;
		maxSpeed = 0;
		speed = 0;
		turnSpeed = 0;
	}

	@Override
	public void function() {
		moveIfMoving();
		turnIfTurning();
	}

	@Override
	public void setMoving(boolean value) {
		isMoving = value;
	}

	protected void moveIfMoving() {
		if (isMoving) {
			objectToMove.setPrevPos(objectToMove.getPos().copy()); // do not assign, but clone
			double newX = objectToMove.getPos().getX() + getSpeedX();
			double newY = objectToMove.getPos().getY() + getSpeedY();
			ObjectPosition newPos = new ObjectPosition(newX, newY);
			objectToMove.setPos(newPos);
			objectToMove.body().clear(); // LATER need to call twice
			objectToMove.body();
			objectToMove.notifyObserversAbout(new ObjectUpdatedEvent(objectToMove));
		}
	}

	@Override
	public void setTurning(TurnDirection dir, boolean value) {
		switch (dir) {
		case CW:
			isTurningCW = value;
			isTurningCCW = false;
			break;
		case CCW:
			isTurningCW = false;
			isTurningCCW = value;
			break;
		}
	}

	protected void turnIfTurning() {
		if (isTurningCW) {
			turnDir(1);
			objectToMove.notifyObserversAbout(new ObjectUpdatedEvent(objectToMove));
		}
		if (isTurningCCW) {
			turnDir(-1);
			objectToMove.notifyObserversAbout(new ObjectUpdatedEvent(objectToMove));
		}
	}

	private void turnDir(int dir) {
		objectToMove.setAngle(objectToMove.getAngle() + turnSpeed * dir);
		objectToMove.body().clear(); // LATER called twice
		objectToMove.body();
	}

	@Override
	public int getSpeedX() {
		return (int) (speed * Math.cos(objectToMove.getAngle()));
	}

	@Override
	public int getSpeedY() {
		return (int) (speed * Math.sin(objectToMove.getAngle()));
	}

	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(int speed) {
		if (speed <= maxSpeed)
			this.speed = speed;
		else
			this.speed = maxSpeed;
	}

	@Override
	public double getTurnSpeed() {
		return turnSpeed;
	}

	@Override
	public void setTurnSpeed(double turnSpeed) {
		this.turnSpeed = turnSpeed;
	}

	protected void finishCopy(GeneralMover copy) {
		copy.speed = this.speed;
		copy.maxSpeed = this.maxSpeed;
		copy.turnSpeed = this.turnSpeed;
		copy.isMoving = this.isMoving;
		copy.isTurningCW = this.isTurningCW;
		copy.isTurningCCW = this.isTurningCCW;
	}

}
