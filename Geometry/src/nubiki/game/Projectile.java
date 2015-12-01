package nubiki.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Projectile extends GameObject implements  Drawable {
	private static final long serialVersionUID = 1L;

	public Projectile(int x, int y) {
		super();
		posX=x;
		posY=y;
	}
	@Override
	public ArrayList<Point> body() {
		if(points.isEmpty()) {
//			System.out.println("calculating projectile points");
			Point p = new Point((int)(posX+5),(int)(posY+5));
			points.add(0, p);
			p = new Point((int)(posX+10),(int)(posY+5));
			points.add(1,p);
		}
		return points;
	}

	//@Override
	public void draw(Graphics g){
		body();
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(points.get(0).getX(), points.get(0).getY());
		for(int i=1;i<points.size();i++) {
			path.lineTo(points.get(i).getX(), points.get(i).getY());	
		}		
		path.closePath();
		g2.draw(path);
//		g2.fill(path);
	}
	
	public void move() {
		if(speed>0) {
			distTravelled+=Math.sqrt(Math.pow(getSpeedX(), 2)+Math.pow(getSpeedY(), 2));
			posX+=getSpeedX();
			posY+=getSpeedY();
			points.clear();
			body();
		}
		if(distTravelled>liveDistance)
			setObsolete(true);
			
	}
	
	public void turn() {
		angle+=turnSpeed;
		body();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isDestroyed() {
		return obsolete;
	}
	@Override
	public void update() {
		move();
	}
}
