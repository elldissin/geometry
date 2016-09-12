package my.games.geometry.game.objects;

import my.games.geometry.game.movers.BFGProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class BFGProjectile extends Projectile {

	public BFGProjectile(int x, int y, double angle) {
		super(x, y, angle);
		mover = new BFGProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 50;
		objWidth = 50;
		body();
	}

}
