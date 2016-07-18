package nubiki.game.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import nubiki.game.GameObject;

public class ProjectileRenderer implements Renderer {

	@Override
	public void draw(Graphics g, GameObject obj) {
//		super.draw(g); //invoked to draw the bounding rect first
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		ArrayList<Point> points = obj.body();
		if(!points.isEmpty()) {
			path.moveTo(points.get(0).getX(), points.get(0).getY());
			for(int i=1;i<points.size();i++) {
				path.lineTo(points.get(i).getX(), points.get(i).getY());	
			}		
			path.closePath();
			g2.draw(path);
//			//drawing direction line
//			Point pos = obj.getPos();
//			int objWidth = obj.getObjWidth();
//			int objHeight = obj.getObjHeight();
//			double angle = obj.getAngle();
//			g2.drawLine(pos.x, pos.y, (int) (pos.x+(objWidth*Math.cos(angle))),
//					(int) (pos.y+(objHeight*Math.sin(angle))));
//			//drawing life %
//			g.drawString(String.valueOf(obj.getHealth()), pos.x, (pos.y-objHeight));
		}
//		g2.fill(path);
	}

}
