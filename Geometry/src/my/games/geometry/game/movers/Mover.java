package my.games.geometry.game.movers;

import my.games.geometry.game.ObjectSystem;

public interface Mover extends ObjectSystem {

	public enum TurnDirection {
		CW, CCW
	}

	public void setMoving(boolean value);

	public void setTurning(TurnDirection dir, boolean value);

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
