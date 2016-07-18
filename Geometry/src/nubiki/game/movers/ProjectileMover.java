package nubiki.game.movers;

import nubiki.game.GameObject;

public class ProjectileMover implements Mover {

	@Override
	public void move(GameObject obj) {
//		super.move();
//		if(speed>0) {
//			distTravelled+=Math.sqrt(Math.pow(getSpeedX(), 2)+Math.pow(getSpeedY(), 2));
//			posX+=getSpeedX();
//			posY+=getSpeedY();
//			points.clear();
//			body();
//		}
//		if(distTravelled>liveDistance)
//			setObsolete(true);
	}

	@Override
	public void turn(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSpeedX(GameObject obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpeedY(GameObject obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxSpeed(int maxSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSpeed(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTurnSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTurnSpeed(double turnSpeed) {
		// TODO Auto-generated method stub
		
	}

}
