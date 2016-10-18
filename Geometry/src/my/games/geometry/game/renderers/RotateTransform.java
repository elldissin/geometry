package my.games.geometry.game.renderers;

import java.awt.Point;

public class RotateTransform {
	public static void transform(Point p, Point center, double angle) {
		p.setLocation(p.getX() - center.getX(), p.getY() - center.getY());
		p.setLocation(p.getX() * Math.cos(angle) + p.getY() * Math.sin(angle),
				p.getX() * Math.sin(angle) * -1 + p.getY() * Math.cos(angle));
		p.setLocation(p.getX() + center.getX(), p.getY() + center.getY());
	}
}
