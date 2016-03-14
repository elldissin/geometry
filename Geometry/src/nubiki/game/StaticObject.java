package nubiki.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class StaticObject extends GameObject implements 
Drawable, Collidable {
private int health, level;
	public StaticObject(int x, int y) {
		super(); 
		health=100;
		level=2;
		objWidth=50;
		objHeight=50;
		setPosX(x);
		setPosY(y);
		body();
	}

	@Override
	public ArrayList<Point> body() {
		if(points.isEmpty()) {
			for(int i=0;i<level+2;i++) {
				int x1=(int) (posX+(objWidth*Math.cos(2*Math.PI/(level+2)*i+angle)));
				int y1=(int) (posY+(objHeight*Math.sin(2*Math.PI/(level+2)*i+angle)));
				Point p = new Point(x1,y1);
				points.add(i, p);
			}
		}
		return points;
	}
	
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
			g2.fill(path);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
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
	public void getHit(int amount) {
		// TODO Auto-generated method stub
		
	}
}
