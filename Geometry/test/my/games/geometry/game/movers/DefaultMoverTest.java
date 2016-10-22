package my.games.geometry.game.movers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.World;
import my.games.geometry.game.objects.GameObject;

public class DefaultMoverTest {

	@Test
	public void gameObjectMovetest() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getMover().function(1);
		// assert
		assertEquals(playerTest.getMover().getPos().getIntX(), 5);
		assertEquals(playerTest.getMover().getPos().getIntY(), 0);
	}

	@Test
	public void gameObjectMoveAngle90test() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", new ObjectPosition(0, 0), Math.toRadians(90));
		// act
		playerTest.getMover().function(1);
		// assert
		assertEquals(playerTest.getMover().getPos().getIntX(), 0);
		assertEquals(playerTest.getMover().getPos().getIntY(), 5);
	}

	@Test
	public void gameObjectTurntest() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", new ObjectPosition(0, 0), 0.0);
		// act
		playerTest.getMover().function(1);
		// assert
		assertEquals(playerTest.getMover().getAngle(), -0.1, 0.001);

	}

}
