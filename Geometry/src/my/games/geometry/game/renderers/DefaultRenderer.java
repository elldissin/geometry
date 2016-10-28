package my.games.geometry.game.renderers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.io.Serializable;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.ObjectShape;
import my.games.geometry.game.engine.ShapeElement;
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
		ObjectPosition p = objectToDraw.getMover().getPos().copy();
		p.setPos(p.getX() - objectToDraw.getObjHeight() / 2 + 10, p.getY() - objectToDraw.getObjWidth() / 2 + 20);
		g.drawString("ID: " + objectToDraw.getObjectID(), p.getIntX(), p.getIntY());
		ObjectShape originShape = objectToDraw.rebuildShape();
		// ObjectShape shapeToDraw = animator.animatePoints(originShape, center);

		for (int i = 0; i < originShape.size(); i++) {
			Point center = new Point(objectToDraw.getMover().getPos().toPoint());
			if (i > 0) {
				ShapeElement elementToDraw = animator.animatePoints(originShape.getElement(i), center);
				drawElement(g, elementToDraw);
			} else
				drawElement(g, originShape.getElement(i));
		}
		// drawing direction line
		Point pos = new Point(objectToDraw.getMover().getPos().toPoint());
		int objWidth = objectToDraw.getObjWidth();
		int objHeight = objectToDraw.getObjHeight();
		double angle = objectToDraw.getMover().getAngle();
		Graphics2D g2 = (Graphics2D) g;
		g2.drawLine(pos.x, pos.y, (int) (pos.x + (objWidth * Math.cos(angle))),
				(int) (pos.y + (objHeight * Math.sin(angle))));
		// drawing life %
		g.drawString(String.valueOf(objectToDraw.getHealth()), pos.x, (pos.y - objHeight));
	}

	private void drawElement(Graphics g, ShapeElement element) {
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(element.getPoint(0).getX(), element.getPoint(0).getY());
		for (int i = 1; i < element.size(); i++) {
			Point p = element.getPoint(i);
			path.lineTo(p.getX(), p.getY());
		}
		path.closePath();
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(path);
	}

	private void drawBoundingRect(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(objectToDraw.boundingRect().x, objectToDraw.boundingRect().y, objectToDraw.boundingRect().width,
				objectToDraw.boundingRect().height);
		g.setColor(Color.black);
	}

}
