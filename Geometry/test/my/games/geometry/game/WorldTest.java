package my.games.geometry.game;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.World;
import my.games.geometry.game.objects.GameObject;

public class WorldTest {

	@Test
	public void playerPositionTest() {
		// arrange
		World worldTest = new World();
		// act
		GameObject playerTest = worldTest.createGameObject("player", new ObjectPosition(0, 0), 0.0);
		// assert
		Point playerPositionTest = new Point(playerTest.getPos().getIntX(), playerTest.getPos().getIntY());
		assertEquals(playerPositionTest.x, 0);
		assertEquals(playerPositionTest.y, 0);
	}

}
