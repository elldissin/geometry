package nubiki.game.movers;

import java.awt.Point;

import nubiki.game.GameObject;

public class DefaultMover implements Mover {

	@Override
	public void move(GameObject obj) {
		obj.setPrevPos(obj.getPos());
		if (obj.getSpeed() > 0) {
			obj.getPos().x += obj.getSpeedX();
			obj.getPos().y += obj.getSpeedY();
			obj.body().clear(); //no method to get points directly, body() method shall be fixed
			obj.body();
		}
	}

	@Override
	public void turn(GameObject obj) {
		if(obj.getTurnSpeed()!=0) {
			obj.setAngle(obj.getAngle()+obj.getTurnSpeed());
			obj.body().clear(); //no method to get points directly, body() method shall be fixed
			obj.body();
		}
	}
}
