package my.games.geometry.game.movers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;

public class DefaultMoverTest {

	@Test
	public void gameObjectMovetest() {
		// arrange
		GameObject playerTest = new Player(new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getMover().function(1);
		// assert
		assertEquals(playerTest.getMover().getPos().getIntX(), 5);
		assertEquals(playerTest.getMover().getPos().getIntY(), 0);
	}

	@Test
	public void gameObjectMoveAngle90test() {
		// arrange
		GameObject playerTest = new Player(new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getMover().function(1);
		// assert
		assertEquals(playerTest.getMover().getPos().getIntX(), 0);
		assertEquals(playerTest.getMover().getPos().getIntY(), 5);
	}

	@Test
	public void gameObjectTurntest() {
		// arrange
		GameObject playerTest = new Player(new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getMover().function(1);
		// assert
		assertEquals(playerTest.getMover().getAngle(), -0.1, 0.001);

	}

}
