package my.games.geometry.game.movers;

import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public class NoMover implements Mover, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void move(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(GameObject obj, int dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSpeedX(GameObject obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpeedY(GameObject obj) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public Mover copy() {
		NoMover copy = new NoMover();
		return copy;
	}

}
