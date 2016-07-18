package nubiki.game.movers;

import java.awt.Point;

public interface Mover {
	public void moveTo(Point p);
	public void turnCW(double angle);
	public void turnCCW(double angle);
}
