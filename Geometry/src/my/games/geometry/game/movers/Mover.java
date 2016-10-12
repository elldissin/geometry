package my.games.geometry.game.movers;

public interface Mover {

	public enum TurnDirection {
		CW, CCW
	}

	public void setMoving(boolean value);

	public void setTurning(TurnDirection dir, boolean value);

	void moveIfMoving();

	void turnIfTurning();

	int getSpeedX();

	int getSpeedY();

	int getMaxSpeed();

	void setMaxSpeed(int maxSpeed);

	int getSpeed();

	void setSpeed(int speed);

	double getTurnSpeed();

	void setTurnSpeed(double turnSpeed);

	public Mover copy();
}
