package my.games.geometry.game.movers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import my.games.geometry.game.ObjectPosition;
import my.games.geometry.game.objects.BFGProjectile;
import my.games.geometry.game.objects.GameObject;

public class BFGProjectileMoverTest {

	@Test
	public void bfgBulletDestroyTest() {
		// arrange
		GameObject bfgProjectileTest = new BFGProjectile(new ObjectPosition(0, 0), 0.0);

		// act
		for (int i = 0; i < 101; i++) {
			bfgProjectileTest.getMover().moveIfMoving();
		}
		// assert
		System.out.println(bfgProjectileTest.getPos().getX());
		System.out.println(bfgProjectileTest.isDestroyed());

		assertTrue(bfgProjectileTest.isDestroyed());
	}

	@Test
	public void bfgBulletPositionTest() {
		// arrange
		GameObject bfgProjectileTest = new BFGProjectile(new ObjectPosition(0, 0), Math.toRadians(180));
		// act
		bfgProjectileTest.getMover().moveIfMoving();
		// assert
		assertEquals(bfgProjectileTest.getPos().getIntX(), -4);
	}

}
