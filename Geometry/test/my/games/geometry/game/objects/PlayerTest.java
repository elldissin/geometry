package my.games.geometry.game.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.World;

public class PlayerTest {

	@Test
	public void PlayerHPTest() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getHit(101);
		// assert
		System.out.println(playerTest.getHealth());
		System.out.println(playerTest.isDestroyed());

		assertEquals(playerTest.getHealth(), 0);
		assertTrue(playerTest.isDestroyed());
	}

}
