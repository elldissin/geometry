package my.games.geometry.game.objects;

import java.awt.Point;

import my.games.geometry.game.movers.BFGProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class BFGProjectile extends Projectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BFGProjectile(Point position, double angle) {
		super(position, angle);
		mover = new BFGProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 50;
		objWidth = 50;
		body();
	}

}
