package my.games.geometry.game.engine;

import java.io.Serializable;

import my.games.geometry.exceptions.WrongPositionException;

/**
 * @author LCrystal Represents game object 2D position within a model, using double precision.
 */
public class ObjectPosition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double x, y;
	private final double MAX_VALUE = 9999.9;
	private final double MIN_VALUE = -9999.9;

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
		return (int) x;
	}

	public double getY() {
		return y;
	}

	public int getIntY() {
		return (int) y;
	}

	private void checkAndAssignValues(double x, double y) {
		if (x >= MIN_VALUE && y >= MIN_VALUE) {
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

	public String toString() {
		return "(" + Math.round(x) + "," + Math.round(y) + ")";
	}

	public ObjectPosition copy() {
		ObjectPosition copy = new ObjectPosition();
		copy.setPos(this);
		return copy;
	}

}
