package my.games.geometry.game.movers;

import my.games.geometry.game.objects.GameObject;

public class BFGProjectileMover extends ProjectileMover {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int speed;
	private int distTravelled;

	public BFGProjectileMover() {
		super();
		speed = 4;
	}

	@Override
	public void turn(GameObject obj, int dir) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxSpeed(int maxSpeed) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSpeed(int speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTurnSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTurnSpeed(double turnSpeed) {
		// TODO Auto-generated method stub

	}

}
