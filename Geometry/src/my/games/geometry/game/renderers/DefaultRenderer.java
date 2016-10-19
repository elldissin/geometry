package my.games.geometry.game.renderers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.util.List;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;

public class DefaultRenderer implements Renderer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameObject objectToDraw;
	private Animator animator = new RotatingAnimator();

	public DefaultRenderer(GameObject objectToDraw) {
		this.objectToDraw = objectToDraw;
	}

	@Override
	public void draw(Graphics g) {
		drawBoundingRect(g);
		ObjectPosition p = objectToDraw.getPos().copy();
		p.setPos(p.getX() - objectToDraw.getObjHeight() / 2 + 10, p.getY() - objectToDraw.getObjWidth() / 2 + 20);
		g.drawString("ID: " + objectToDraw.getObjectID(), p.getIntX(), p.getIntY());
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		List<Point> origPoints = objectToDraw.body();
		List<Point> points = animator.animatePoints(origPoints, new Point(objectToDraw.getPos().toPoint()));

		if (!points.isEmpty()) {
			path.moveTo(points.get(0).getX(), points.get(0).getY());
			for (int i = 1; i < points.size(); i++) {
				path.lineTo(points.get(i).getX(), points.get(i).getY());
			}
			path.closePath();
			g2.draw(path);
			// drawing direction line
			Point pos = new Point(objectToDraw.getPos().getIntX(), objectToDraw.getPos().getIntY());
			int objWidth = objectToDraw.getObjWidth();
			int objHeight = objectToDraw.getObjHeight();
			double angle = objectToDraw.getAngle();
			g2.drawLine(pos.x, pos.y, (int) (pos.x + (objWidth * Math.cos(angle))),
					(int) (pos.y + (objHeight * Math.sin(angle))));
			// drawing life %
			g.drawString(String.valueOf(objectToDraw.getHealth()), pos.x, (pos.y - objHeight));
		}
		// g2.fill(path);
	}

	private void drawBoundingRect(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(objectToDraw.boundingRect().x, objectToDraw.boundingRect().y, objectToDraw.boundingRect().width,
				objectToDraw.boundingRect().height);
		g.setColor(Color.black);
	}

}
