package nubiki.game.movers;

import java.awt.Point;

import nubiki.game.GameObject;

public interface Mover {
	public void move(GameObject obj);
	public void turn(GameObject obj);
}
