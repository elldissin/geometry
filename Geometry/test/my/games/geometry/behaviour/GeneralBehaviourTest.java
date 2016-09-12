package my.games.geometry.behaviour;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class GeneralBehaviourTest {

	@Test
	public void slowDownTest() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", 0, 0, 0.0);
		// act
		playerTest.getMover().setSpeed((int) (playerTest.getMover().getSpeed() * (100 - 50) / 100));
		// assert
		assertEquals(playerTest.getMover().getSpeed(), 2);
	}
}
