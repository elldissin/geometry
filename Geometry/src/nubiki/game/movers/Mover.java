package nubiki.game.movers;

import java.awt.Point;

import nubiki.game.GameObject;

public interface Mover {
	public void move(GameObject obj);
	public void turn(GameObject obj);
	int getSpeedX(GameObject obj);
	int getSpeedY(GameObject obj);
	int getMaxSpeed();
	void setMaxSpeed(int maxSpeed);
	int getSpeed();
	void setSpeed(int speed);
	double getTurnSpeed();
	void setTurnSpeed(double turnSpeed);
}
