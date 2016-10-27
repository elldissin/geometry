package my.games.geometry.game.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.io.Serializable;

import my.games.geometry.game.engine.ObjectShape;
import my.games.geometry.game.engine.ShapeElement;
import my.games.geometry.game.objects.GameObject;

public class ProjectileRenderer implements Renderer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameObject objectToDraw;

	public ProjectileRenderer(GameObject objectToDraw) {
		this.objectToDraw = objectToDraw;
	}

	@Override
	public void draw(Graphics g) {
		ObjectShape shapeToDraw = objectToDraw.rebuildShape();
		for (int i = 0; i < shapeToDraw.size(); i++) {
			drawElement(g, shapeToDraw.getElement(i));
		}
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

}
