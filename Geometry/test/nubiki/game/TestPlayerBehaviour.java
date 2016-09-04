package nubiki.game;

import static org.junit.Assert.*;

import org.junit.Test;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.GeneralBehaviour;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class TestPlayerBehaviour {

	@Test
	public void test() {
		//arrange
		PlayerBehaviour testPlayerBehaviour = new PlayerBehaviour();
		boolean testPlayerBehaviourisVulnerable = testPlayerBehaviour.isVulnerable(); 
		//mock?
		World testWorld = new World();
		GameObject testPlayer= testWorld.createGameObject("player", 100, 100, 0.0);
		//act
		testPlayer.setBehaviour(testPlayerBehaviour);
		//assert
		assertTrue(testPlayerBehaviourisVulnerable == false) ;
	}
}

