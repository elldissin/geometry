package nubiki.game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class GameObject {
	private static final long serialVersionUID = 1L;
	protected double posX;
	protected double posY;	
	protected double objWidth;
	protected double objHeight;
	protected int speedX,speedY;
	protected int distTravelled;
	protected int liveDistance;
	protected ArrayList <Point> points;
	
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
		distTravelled=0;
		liveDistance=400;
		points=new ArrayList<Point>();
	}
	
	public int getSpeedX() {
		return speedX;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public abstract void draw(Graphics g);
	public abstract  ArrayList<Point> body();
	public  boolean isObsolete() {
		if(distTravelled>liveDistance)
			return true;
		else 
			return false;
	}
	
//	public void move(int x, int y) {
//		for(int i=0;i<points.size();i++)
//			points.get(i).translate(x, y);
//	}
	
	public abstract void move();
}
