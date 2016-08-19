package my.games.geometry.game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.movers.DefaultMover;
import my.games.geometry.game.renderers.DefaultRenderer;
import my.games.geometry.game.weapons.DefaultWeapon;

public class Player extends GameObject implements Controllable {
	private static final long serialVersionUID = 1L;

//	Animator anim;

	public Player(int x, int y) {
		super(x,y); 
		health=100;
		level=5;
		mover = new DefaultMover();
		weapon = new DefaultWeapon();
		renderer = new DefaultRenderer();
		body();
//		anim=new Animator((int)posX, (int)posY);
	}

	public ArrayList<Point> body() {
//		points.clear();
		if(points.isEmpty()) {
			for(int i=0;i<level+2;i++) {
				int x1=(int) (currentPos.x+(objWidth*Math.cos(2*Math.PI/(level+2)*i+angle)));
				int y1=(int) (currentPos.y+(objHeight*Math.sin(2*Math.PI/(level+2)*i+angle)));
				Point p = new Point(x1,y1);
				points.add(i, p);
			}
		}
		return points;
	}

	@Override
	public void setMoving() {
		// TODO Auto-generated method stub
		mover.setSpeed(mover.getMaxSpeed());
	}

	@Override
	public void setStopped() {
		// TODO Auto-generated method stub
		mover.setSpeed(0);
	}

	@Override
	public void destroy() {
		setObsolete(true);
	}

	@Override
	public void setTurningCW() {
		mover.setTurnSpeed(0.1);
	}
	
	@Override
	public void setTurningCCW() {
		mover.setTurnSpeed(-0.1);
	}

	@Override
	public void setNotTurning() {
		mover.setTurnSpeed(0);
	}

	@Override
	public boolean isDestroyed() {
		return obsolete;
	}

	@Override
	public void getHit(int amount) {
		if(health-amount<=0)
			health=0;
		else 
			health-=amount;
	}
}
