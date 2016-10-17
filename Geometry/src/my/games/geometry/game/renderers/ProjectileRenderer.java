package my.games.geometry.game.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.util.List;

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
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		List<Point> points = objectToDraw.body();
		if (!points.isEmpty()) {
			path.moveTo(points.get(0).getX(), points.get(0).getY());
			for (int i = 1; i < points.size(); i++) {
				path.lineTo(points.get(i).getX(), points.get(i).getY());
			}
			path.closePath();
			g2.draw(path);
		}
	}

}
