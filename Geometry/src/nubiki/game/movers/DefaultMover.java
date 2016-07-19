package nubiki.game.movers;

import java.awt.Point;

import nubiki.game.GameObject;

public class DefaultMover implements Mover {
	private int maxSpeed, speed;
	private double turnSpeed;

	public DefaultMover() {
		speed = 0;
		maxSpeed = 10;
		turnSpeed = 0;
	}
	
	@Override
	public void move(GameObject obj) {
		if (getSpeed() > 0) {
			obj.setPrevPos( (Point) (obj.getPos().clone()) ); //important, do not assign, but clone
			obj.getPos().x += getSpeedX(obj);
			obj.getPos().y += getSpeedY(obj);
			obj.body().clear(); //no method to get points directly, body() method shall be fixed
			obj.body();
		}
	}

	@Override
	public int getSpeedX(GameObject obj) {
		return (int) (speed * Math.cos(obj.getAngle()));
	}

	@Override
	public int getSpeedY(GameObject obj) {
		return (int) (speed * Math.sin(obj.getAngle()));
	}

	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	public void setSpeed(int speed) {
		if (speed <= maxSpeed)
			this.speed = speed;
		else
			this.speed = maxSpeed;
	}

	@Override
	public void turn(GameObject obj) {
		if(turnSpeed!=0) {
			obj.setAngle(obj.getAngle()+turnSpeed);
			obj.body().clear(); //no method to get points directly, body() method shall be fixed
			obj.body();
		}
	}
	
	@Override
	public double getTurnSpeed() {
		return turnSpeed;
	}

	@Override
	public void setTurnSpeed(double turnSpeed) {
		this.turnSpeed = turnSpeed;
	}
}
