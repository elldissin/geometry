package my.games.geometry.game.movers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class DefaultMoverTest {

	@Test
	public void gameObjectMovetest() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", 0, 0, 0.0);
		// act
		playerTest.move();
		// assert
		assertEquals(playerTest.getPos().x, 5);
		assertEquals(playerTest.getPos().y, 0);
	}

	@Test
	public void gameObjectMoveAngle90test() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", 0, 0, Math.toRadians(90));
		// act
		playerTest.move();
		// assert
		assertEquals(playerTest.getPos().x, 0);
		assertEquals(playerTest.getPos().y, 5);
	}

	@Test
	public void gameObjectTurntest() {
		// arrange
		World worldTest = new World();
		GameObject playerTest = worldTest.createGameObject("player", 0, 0, 0.0);
		// act
		playerTest.getMover().turn(playerTest, -1);
		// assert
		assertEquals(playerTest.getAngle(), -0.1, 0.001);

	}

}
