package my.games.geometry.game.objects;

import my.games.geometry.game.movers.ProjectileMover;
import my.games.geometry.game.renderers.ProjectileRenderer;

public class BFGProjectile extends Projectile {

	public BFGProjectile(int x, int y, double angle) {
		super(x, y, angle);
		mover = new ProjectileMover();
		renderer = new ProjectileRenderer();
		objHeight = 50;
		objWidth = 50;
		body();
	}

}
