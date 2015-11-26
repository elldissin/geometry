package nubiki.game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Player extends GameObject implements Shooting {
	private static final long serialVersionUID = 1L;
	private int health;
	private int level;

	public Player() {
		super(); 
		health=100;
		level=1;
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
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		if(!points.isEmpty()) {
			path.moveTo(points.get(0).getX(), points.get(0).getY());
			for(int i=1;i<points.size();i++) {
				path.lineTo(points.get(i).getX(), points.get(i).getY());	
			}		
			path.closePath();
			g2.draw(path);
		}
//		g2.fill(path);
	}
	
	public void move() {
		if(speed>0) {
			for(int i=0;i<points.size();i++)
				points.get(i).translate(getSpeedX(), getSpeedY());
			posX+=getSpeedX();
			posY+=getSpeedY();
		}
	}
	public void turn() {
		if(turnSpeed!=0) {
//			System.out.println("angle"+angle);
			angle+=turnSpeed;
			points.clear();
			body();
	//		System.out.println("rotating speed:"+turnSpeed+" posX"+posX+"posY"+posY);
	//		AffineTransform t=AffineTransform.getRotateInstance(turnSpeed,posX,posY);
	//		for(int i=0;i<points.size();i++)
	//			t.transform(points.get(i),points.get(i));
		}
	}
	//try to handle projectile moving and drawing inside player
	@Override
	public void shoot() {
		Projectile projectile = new Projectile((int)posX, (int)posY);
		projectile.setSpeed(20);
		projectile.setAngle(angle);
		GameCanvas.addObject(projectile); //add each other to ignore list to avoid collisions
		addIgnoreObject(projectile);
		projectile.addIgnoreObject(this);
	}
}
