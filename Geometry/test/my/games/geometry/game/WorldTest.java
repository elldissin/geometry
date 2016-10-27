package my.games.geometry.game;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;

public class WorldTest {

	@Test
	public void playerPositionTest() {
		// arrange
		GameObject playerTest = new Player(new ObjectPosition(0, 0), 0.0);
		// act

		// assert
		Point playerPositionTest = new Point(playerTest.getMover().getPos().getIntX(),
				playerTest.getMover().getPos().getIntY());
		assertEquals(playerPositionTest.x, 0);
		assertEquals(playerPositionTest.y, 0);
	}

}
