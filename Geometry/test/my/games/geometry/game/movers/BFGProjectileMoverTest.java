package my.games.geometry.game.movers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.BFGProjectile;
import my.games.geometry.game.objects.GameObject;

public class BFGProjectileMoverTest {

	@Test
	public void bfgBulletDestroyTest() {
		// arrange
		GameObject bfgProjectileTest = new BFGProjectile(new ObjectPosition(0, 0), 0.0);

		// act
		for (int i = 0; i < 101; i++) {
			bfgProjectileTest.getMover().function(1);
		}
		// assert
		System.out.println(bfgProjectileTest.getMover().getPos().getX());
		System.out.println(bfgProjectileTest.isDestroyed());

		assertTrue(bfgProjectileTest.isDestroyed());
	}

	@Test
	public void bfgBulletPositionTest() {
		// arrange
		GameObject bfgProjectileTest = new BFGProjectile(new ObjectPosition(0, 0), Math.toRadians(180));
		// act
		bfgProjectileTest.getMover().function(1);
		// assert
		assertEquals(bfgProjectileTest.getMover().getPos().getIntX(), -4);
	}

}
