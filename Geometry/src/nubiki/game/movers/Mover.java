package nubiki.game.movers;

import java.awt.Point;

import nubiki.game.GameObject;

public interface Mover {
	public void moveTo(GameObject obj, Point p);
	public void turn(GameObject obj);
}
