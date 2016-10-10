package my.games.geometry.game;

import my.games.geometry.exceptions.WrongPositionException;

/**
 * @author LCrystal Represents game object 2D position within a model, using double precision.
 */
public class ObjectPosition {
	private double x, y;
	private final double MAX_VALUE = 9999.9;

	public ObjectPosition() {
		checkAndAssignValues(0.0, 0.0);
	}

	public ObjectPosition(double x, double y) {
		checkAndAssignValues(x, y);
	}

	public void setPos(double x, double y) {
		checkAndAssignValues(x, y);
	}

	public void setPos(ObjectPosition newPos) {
		double newX, newY;
		newX = newPos.getX();
		newY = newPos.getY();
		checkAndAssignValues(newX, newY);
	}

	public double getX() {
		return x;
	}

	public int getIntX() {
		return (int) Math.round(x);
	}

	public double getY() {
		return y;
	}

	public int getIntY() {
		return (int) Math.round(y);
	}

	private void checkAndAssignValues(double x, double y) {
		if (x >= 0.0 && y >= 0.0) {
			if (x <= MAX_VALUE && y <= MAX_VALUE) {
				this.x = x;
				this.y = y;
			} else {
				throw new WrongPositionException();
			}
		} else {
			throw new WrongPositionException();
		}
	}

	public ObjectPosition copy() {
		ObjectPosition copy = new ObjectPosition();
		copy.setPos(this);
		return copy;
	}

}
