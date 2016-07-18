package nubiki.game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import nubiki.behaviour.DmgEffect;
import nubiki.behaviour.ProjectileBehaviour;
import nubiki.behaviour.SlowEffect;

public class Player extends GameObject implements Drawable,
Updatable, Controllable, Collidable {
	private static final long serialVersionUID = 1L;
	private int health;
	private int level;
//	Animator anim;

	public Player() {
		super(); 
		health=100;
		level=1;
		body();
	}
	
	public Player(int x, int y) {
		super(); 
		health=100;
		level=5;
		maxSpeed=5;
		setPos(new Point(x,y));
		body();
//		anim=new Animator((int)posX, (int)posY);
	}

	public ArrayList<Point> body() {
//		points.clear();
		if(points.isEmpty()) {
//			System.out.println("player points recalculation...");
			for(int i=0;i<level+2;i++) {
				int x1=(int) (currentPos.x+(objWidth*Math.cos(2*Math.PI/(level+2)*i+angle)));
				int y1=(int) (currentPos.y+(objHeight*Math.sin(2*Math.PI/(level+2)*i+angle)));
				Point p = new Point(x1,y1);
				points.add(i, p);
			}
		}
		return points;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void move() {
		super.move();
		speed=Math.min(speed,maxSpeed);
		if(speed>0) {
			posX+=getSpeedX();
			posY+=getSpeedY();
			points.clear();
			body();
		}
	}
	public void turn() {
		if(turnSpeed!=0) {
//			System.out.println("angle"+angle);
			angle+=turnSpeed;
			points.clear();
			body();
		}
	}
	//try to handle projectile moving and drawing inside player
	@Override
	public void shoot() {
		Projectile projectile = new Projectile((int)posX, (int)posY);
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(1));
		projectile.setBehaviour(new ProjectileBehaviour());
		projectile.setSpeed(7);
		projectile.setAngle(angle);
		GameManager.addProjectile(projectile); 
		//add each other to ignore list to avoid collisions
		addIgnoreObject(projectile);
		projectile.addIgnoreObject(this);
	}

	@Override
	public void setMoving() {
		// TODO Auto-generated method stub
		setSpeed(maxSpeed);
	}

	@Override
	public void setStopped() {
		// TODO Auto-generated method stub
		setSpeed(0);
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
		System.out.println("player maxspeed: " + maxSpeed);
	}

	@Override
	public void destroy() {
		setObsolete(true);
	}

	@Override
	public void setTurningCW() {
		turnSpeed=0.1;
	}
	
	@Override
	public void setTurningCCW() {
		turnSpeed=-0.1;
	}

	@Override
	public void setNotTurning() {
		turnSpeed=0;
	}

	@Override
	public boolean isDestroyed() {
		return obsolete;
	}

	@Override
	public void update() {
		move();
		turn();
	}

	@Override
	public void getHit(int amount) {
		if(health-amount<=0)
			health=0;
		else 
			health-=amount;
	}
}
