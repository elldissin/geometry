package my.games.geometry.game;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import my.games.geometry.game.objects.GameObject;

public class WorldTest {

	@Test
	public void playerPositionTest() {
		// arrange
		World worldTest = new World();
		// act
		GameObject playerTest = worldTest.createGameObject("player", 0, 0, 0.0);
		// assert
		Point playerPositionTest = playerTest.getPos();
		assertEquals(playerPositionTest.x, 0);
		assertEquals(playerPositionTest.y, 0);
	}

}
