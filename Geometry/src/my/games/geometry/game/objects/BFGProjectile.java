package my.games.geometry.game.objects;

import my.games.geometry.game.ObjectPosition;
import my.games.geometry.game.movers.BFGProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class BFGProjectile extends Projectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BFGProjectile(ObjectPosition position, double angle) {
		super(position, angle);
		mover = new BFGProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 50;
		objWidth = 50;
		body();
	}

}
