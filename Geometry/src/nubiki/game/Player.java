package nubiki.game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player extends GameObject implements Drawable,
Updatable, Controllable, Shooting, Collidable {
	private static final long serialVersionUID = 1L;
	private int health;
	private int level;

	public Player() {
		super(); 
		health=100;
		level=1;
		body();
	}
	
	public Player(int x, int y) {
		super(); 
		health=100;
		level=1;
		maxSpeed=5;
		setPosX(x);
		setPosY(y);
		body();
	}

	public ArrayList<Point> body() {
//		points.clear();
		if(points.isEmpty()) {
//			System.out.println("player points recalculation...");
			for(int i=0;i<level+2;i++) {
				int x1=(int) (posX+(objWidth*Math.cos(2*Math.PI/(level+2)*i+angle)));
				int y1=(int) (posY+(objHeight*Math.sin(2*Math.PI/(level+2)*i+angle)));
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

	//@Override
	public void draw(Graphics g) {
		super.draw(g); //invoked to draw the bounding rect first
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		if(!points.isEmpty()) {
			path.moveTo(points.get(0).getX(), points.get(0).getY());
			for(int i=1;i<points.size();i++) {
				path.lineTo(points.get(i).getX(), points.get(i).getY());	
			}		
			path.closePath();
			g2.draw(path);
			//drawing direction line
			g2.drawLine((int)posX, (int)posY, (int) (posX+(objWidth*Math.cos(angle))),
					(int) (posY+(objHeight*Math.sin(angle))));
			//drawing life %
			g.drawString(String.valueOf(health), (int)posX, (int)(posY-objHeight));
		}
//		g2.fill(path);
	}
	
	public void move() {
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
