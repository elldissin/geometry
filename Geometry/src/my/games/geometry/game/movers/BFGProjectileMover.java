package my.games.geometry.game.movers;

import my.games.geometry.game.objects.GameObject;

public class BFGProjectileMover extends ProjectileMover {
	private int speed;
	private int distTravelled;

	public BFGProjectileMover() {
		super();
		speed = 4;
	}

	@Override
	public void move(GameObject obj) {
		if (speed > 0) {
			distTravelled += Math.sqrt(Math.pow(getSpeedX(obj), 2) + Math.pow(getSpeedY(obj), 2));
			obj.getPos().x += getSpeedX(obj);
			obj.getPos().y += getSpeedY(obj);
			obj.body().clear(); // no method to get points directly, body()
								// method shall be fixed
			obj.body();
		}
		if (distTravelled > obj.getLiveDistance())
			obj.setObsolete(true);
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
