package my.games.geometry.game.movers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ProjectileMoverTest {

	@Test
	public void BulletDestroyTest() {
		// arrange
		World worldTest = new World();
		GameObject BulletTest = worldTest.createGameObject("projectile", 0, 0, 0.0);

		// act
		for (int i = 0; i < 58; i++) {
			BulletTest.getMover().move(BulletTest);
		}
		// assert
		System.out.println(BulletTest.getPos().x);
		System.out.println(BulletTest.getLiveDistance());
		System.out.println(BulletTest.isDestroyed());

		assertTrue(BulletTest.isDestroyed());
	}

}
