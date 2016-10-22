package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.events.ObjectUpdatedEvent;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;

/**
 * @author LCrystal Provides general implementation of common Mover methods
 */
public abstract class GeneralMover implements Mover, Serializable {

	private static final long serialVersionUID = 1L;
	protected ObjectPosition currentPos;
	protected ObjectPosition prevPos;
	protected double angle;
	protected boolean isMoving;
	protected boolean isTurningCW;
	protected boolean isTurningCCW;
	protected int maxSpeed;
	protected int speed;
	protected double turnSpeed;
	protected GameObject ownerObject;

	public GeneralMover(GameObject ownerObject) {
		this.ownerObject = ownerObject;
		isMoving = false;
		isTurningCW = false;
		isTurningCCW = false;
		maxSpeed = 0;
		speed = 0;
		turnSpeed = 0;
	}

	@Override
	public void function(double delta) {
		moveIfMoving(delta);
		turnIfTurning(delta);
	}

	@Override
	public void setMoving(boolean value) {
		isMoving = value;
	}

	protected void moveIfMoving(double delta) {
		if (isMoving) {
			prevPos = currentPos.copy(); // do not assign, but clone
			double newX = getPos().getX() + getSpeedX() * delta;
			double newY = getPos().getY() + getSpeedY() * delta;
			ObjectPosition newPos = new ObjectPosition(newX, newY);
			setPos(newPos);
			ownerObject.notifyObserversAbout(new ObjectUpdatedEvent(ownerObject));
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

	protected void turnIfTurning(double delta) {
		if (isTurningCW) {
			turnDir(1, delta);
			ownerObject.notifyObserversAbout(new ObjectUpdatedEvent(ownerObject));
		}
		if (isTurningCCW) {
			turnDir(-1, delta);
			ownerObject.notifyObserversAbout(new ObjectUpdatedEvent(ownerObject));
		}
	}

	private void turnDir(int dir, double delta) {
		setAngle(angle + turnSpeed * dir * delta);
	}

	@Override
	public int getSpeedX() {
		return (int) (speed * Math.cos(angle));
	}

	@Override
	public int getSpeedY() {
		return (int) (speed * Math.sin(angle));
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

	@Override
	public ObjectPosition getPos() {
		return currentPos;
	}

	@Override
	public void setPos(ObjectPosition currentPos) {
		this.currentPos = currentPos;
		ownerObject.body().clear();
	}

	@Override
	public ObjectPosition getPrevPos() {
		return prevPos;
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public void setAngle(double angle) {
		this.angle = angle;
		ownerObject.body();
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
