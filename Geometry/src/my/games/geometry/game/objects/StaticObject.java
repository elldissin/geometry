package my.games.geometry.game.objects;

import java.awt.Point;
import java.util.ArrayList;

import my.games.geometry.game.movers.DefaultMover;
import my.games.geometry.game.renderers.DefaultRenderer;

public class StaticObject extends GameObject {

	public StaticObject(int x, int y) {
		super(x,y);
		health = 100;
		level = 2;
		objWidth = 50;
		objHeight = 50;
		mover = new DefaultMover();
		// weapon = new DefaultWeapon();
		renderer = new DefaultRenderer();
		body();
	}

	@Override
	public ArrayList<Point> body() {
		if (points.isEmpty()) {
			for (int i = 0; i < level + 2; i++) {
				int x1 = (int) (currentPos.x + (objWidth * Math.cos(2 * Math.PI / (level + 2) * i + angle)));
				int y1 = (int) (currentPos.y + (objHeight * Math.sin(2 * Math.PI / (level + 2) * i + angle)));
				Point p = new Point(x1, y1);
				points.add(i, p);
			}
		}
		return points;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getHit(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}
}
