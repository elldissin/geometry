package nubiki.game;

import java.awt.Point;
import java.util.ArrayList;

import nubiki.game.movers.DefaultMover;
import nubiki.game.renderers.DefaultRenderer;

public class Projectile extends GameObject implements Updatable {
	private static final long serialVersionUID = 1L;

	public Projectile(int x, int y) {
		super();
		mover = new DefaultMover();
//		weapon = new DefaultWeapon();
		renderer = new DefaultRenderer();
		setPos(new Point(x, y));
		objHeight=5;
		objWidth=5;
	}
	@Override
	public ArrayList<Point> body() {
		if(points.isEmpty()) {
//			System.out.println("calculating projectile points");
			Point p = new Point(currentPos.x+5, currentPos.x+5);
			points.add(0, p);
			p = new Point(currentPos.x+10, currentPos.x+5);
			points.add(1,p);
			p = new Point(currentPos.x+10, currentPos.x+0);
			points.add(2,p);
			p = new Point(currentPos.x+5, currentPos.x+0);
			points.add(3,p);
		}
		return points;
	}
	
	@Override
	public void destroy() {
		setObsolete(true);
	}
	@Override
	public boolean isDestroyed() {
		return obsolete;
	}
	@Override
	public void update() {
		move();
	}
	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}
	@Override
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	@Override
	public void getHit(int amount) {
		return;
	}
}
