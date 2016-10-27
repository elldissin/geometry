package my.games.geometry.game.objects;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.movers.BFGProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class BFGProjectile extends Projectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BFGProjectile(ObjectPosition position, double angle) {
		super(position, angle);
		mover = new BFGProjectileMover(this, position, angle);
		renderer = new ProjectileRenderer(this);
		objHeight = 50;
		objWidth = 50;
		rebuildShape();
	}

}
