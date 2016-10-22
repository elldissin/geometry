package my.games.geometry.game.movers;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.ObjectSystem;

public interface Mover extends ObjectSystem {

	public enum TurnDirection {
		CW, CCW
	}

	void setMoving(boolean value);

	void setTurning(TurnDirection dir, boolean value);

	int getSpeedX();

	int getSpeedY();

	int getMaxSpeed();

	void setMaxSpeed(int maxSpeed);

	int getSpeed();

	void setSpeed(int speed);

	double getTurnSpeed();

	void setTurnSpeed(double turnSpeed);

	Mover copy();

	ObjectPosition getPos();

	void setPos(ObjectPosition position);

	ObjectPosition getPrevPos();
}
