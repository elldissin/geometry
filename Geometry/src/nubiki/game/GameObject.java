package nubiki.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import nubiki.behaviour.Behaviour;
import nubiki.behaviour.Effect;
import nubiki.behaviour.GeneralBehaviour;

public abstract class GameObject {
	private static final long serialVersionUID = 1L;
	protected double posX;
	protected double posY;	
	protected double objWidth;
	protected double objHeight;
	protected int speedX,speedY;
	protected int maxSpeed, speed;
	protected double angle;
	protected double turnSpeed;
	protected boolean obsolete;
	protected int distTravelled;
	protected int liveDistance;
	protected Behaviour behaviour;
	protected ArrayList <Point> points;
	protected ArrayList <GameObject> ignoredObjects;
	protected ArrayList <Effect> onHitEffects;
	
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
		points.clear();
		this.posY = posY;
		body();
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
		objWidth=20;
		objHeight=20;
		speedX=0;
		speedY=0;
		speed=0;
		maxSpeed=10;
		angle=0;
		turnSpeed=0;
		distTravelled=0;
		liveDistance=400;
		obsolete=false;
		points=new ArrayList<Point>();
		ignoredObjects = new ArrayList <GameObject>();
		onHitEffects = new ArrayList <Effect>();
		behaviour=null;
	}
	
	public ArrayList <Effect> getOnHitEffects() {
		return onHitEffects;
	}
	
	public void addOnHitEffect(Effect e) {
		onHitEffects.add(e);
	}
	
	public Behaviour getBehaviour() {
		if (behaviour!=null)
			return behaviour;
		else return new GeneralBehaviour();
	}
	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
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
		if (speed<=maxSpeed)
			this.speed = speed;
		else this.speed=maxSpeed;
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
	
	public void draw(Graphics g) { //this displays bounding rect
		g.setColor(Color.gray);
		g.drawRect(boundingRect().x,boundingRect().y,boundingRect().width, boundingRect().height);
		g.setColor(Color.black);
	}
	public abstract  ArrayList<Point> body();

	
	void addIgnoreObject(GameObject o) {
		if(o!=null)
			ignoredObjects.add(o);
	}
	
	public boolean isColliding(Collidable o) {
		if(!ignoredObjects.contains(o)) {
			GameObject temp=(GameObject)o;
			if (boundingRect().intersects(temp.boundingRect()))
				return true;
			return false;
		}
		return false;
	}
	
	public Rectangle boundingRect() {
		return new Rectangle((int)(getPosX()-getObjWidth()/2),(int)
				(getPosY()-getObjHeight()),(int)(getObjWidth())*2,(int)(getObjHeight())*2);
	}
	public abstract void move();
	public abstract void turn();
	abstract public int getMaxSpeed();
	abstract public void setMaxSpeed(int maxSpeed);
	abstract public void getHit(int amount);
}
