package my.games.geometry.game.movers;

import my.games.geometry.game.objects.GameObject;

public interface Mover {
	public void move(GameObject obj);

	public void turn(GameObject obj, int dir); // LATER change radians to deg

	int getSpeedX(GameObject obj);

	int getSpeedY(GameObject obj);

	int getMaxSpeed();

	void setMaxSpeed(int maxSpeed);

	int getSpeed();

	void setSpeed(int speed);

	double getTurnSpeed();

	void setTurnSpeed(double turnSpeed);
}
