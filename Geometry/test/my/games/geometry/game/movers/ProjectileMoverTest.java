package my.games.geometry.game.movers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.World;
import my.games.geometry.game.objects.GameObject;

public class ProjectileMoverTest {

	@Test
	public void bulletDestroyTest() {
		// arrange
		World worldTest = new World();
		GameObject bulletTest = worldTest.createGameObject("projectile", new ObjectPosition(0, 0), 0.0);

		// act
		for (int i = 0; i < 58; i++) {
			bulletTest.getMover().function(1);
		}
		// assert
		System.out.println(bulletTest.getMover().getPos().getX());
		System.out.println(bulletTest.isDestroyed());

		assertTrue(bulletTest.isDestroyed());
	}

}
