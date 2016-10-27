package my.games.geometry.behaviour;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;

public class GeneralBehaviourTest {

	@Test
	public void slowDownTest() {
		// arrange
		GameObject playerTest = new Player(new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getMover().setSpeed((int) (playerTest.getMover().getSpeed() * (100 - 50) / 100));
		// assert
		assertEquals(playerTest.getMover().getSpeed(), 2);
	}
}
