package my.games.geometry.behaviour;

import static org.junit.Assert.fail;

import org.junit.Test;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;

public class PlayerBehaviourTest {

	@Test
	public void playerBehaviourTest() {
		// arrange
		GameObject playerTest = new Player(new ObjectPosition(0, 0), 0.0);
		PlayerBehaviour playerBehaviourTest = new PlayerBehaviour(playerTest);
		// act
		// ??
		// assert
		fail();
	}
}
