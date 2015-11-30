package nubiki.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class GameObject {
	private static final long serialVersionUID = 1L;
	protected double posX;
	protected double posY;	
	protected double objWidth;
	protected double objHeight;
	protected int speedX,speedY;
	protected int speed;
	protected double angle;
	protected double turnSpeed;
	protected boolean obsolete;
	protected int distTravelled;
	protected int liveDistance;
	protected ArrayList <Point> points;
	protected ArrayList <GameObject> ignoredObjects;
	
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		points.clear();
		this.posX = posX;
		body();
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public double getObjWidth() {
		return objWidth;
	}
	public void setObjWidth(double objWidth) {
		this.objWidth = objWidth;
	}
	public double getObjHeight() {
		return objHeight;
	}
	public void setObjHeight(double objHeight) {
		this.objHeight = objHeight;
	}
	
	public GameObject() {
		super();
		posX=100.0;
		posY=100.0;
		objWidth=20.0;
		objHeight=20.0;
		speedX=0;
		speedY=0;
		speed=0;
		angle=0;
		turnSpeed=0;
		distTravelled=0;
		liveDistance=400;
		obsolete=false;
		points=new ArrayList<Point>();
		ignoredObjects = new ArrayList <GameObject>(); 
	}
	
	public double getTurnSpeed() {
		return turnSpeed;
	}
	public void setTurnSpeed(double turnSpeed) {
		this.turnSpeed = turnSpeed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public int getSpeedX() {
		return (int) (speed*Math.cos((double)angle));
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public int getSpeedY() {
		return speedY=(int) (speed*Math.sin((double)angle));
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public void setObsolete(boolean value) {
		obsolete=value;
	}
	
	public abstract void draw(Graphics g);
	public abstract  ArrayList<Point> body();

	boolean isColliding(GameObject o) {
		if(!ignoredObjects.contains(o)){
			Rectangle2D rect1 = new Rectangle ((int)(this.getPosX()),(int)
					(this.getPosY()),(int)(this.getObjWidth())*2,(int)(this.getObjHeight())*2);
			Rectangle2D rect2 = new Rectangle ((int)(o.getPosX()),(int)
					(o.getPosY()),(int)(o.getObjWidth())*2,(int)(o.getObjHeight())*2);
			if (rect1.intersects(rect2))
				return true;
			return false;
		}
		return false;
	}
	
	void addIgnoreObject(GameObject o) {
		if(o!=null)
			ignoredObjects.add(o);
	}
	
	public abstract void move();
	public abstract void turn();
}
