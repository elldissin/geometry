package my.games.geometry.game.movers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import my.games.geometry.game.objects.BFGProjectile;

public class BFGProjectileMoverTest {

	@Test
	public void bfgBulletDestroyTest() {
		// arrange
		BFGProjectile bfgProjectileTest = new BFGProjectile(0, 0, 0);

		// act
		for (int i = 0; i < 101; i++) {
			bfgProjectileTest.move();
		}
		// assert
		System.out.println(bfgProjectileTest.getPos().x);
		System.out.println(bfgProjectileTest.getLiveDistance());
		System.out.println(bfgProjectileTest.isDestroyed());

		assertTrue(bfgProjectileTest.isDestroyed());
	}

	@Test
	public void bfgBulletPositionTest() {
		// arrange
		BFGProjectile bfgProjectileTest = new BFGProjectile(0, 0, Math.toRadians(180));
		// act
		bfgProjectileTest.move();
		// assert
		assertEquals(bfgProjectileTest.getPos().x, -4);
	}

}
