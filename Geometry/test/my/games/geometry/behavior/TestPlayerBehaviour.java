package my.games.geometry.behavior;

import static org.junit.Assert.*;

import org.junit.Test;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.GeneralBehaviour;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class TestPlayerBehaviour {

	@Test
	public void testPlayerBehaviour() {
		//arrange
		PlayerBehaviour testPlayerBehaviour = new PlayerBehaviour();
		//act
		boolean testPlayerBehaviourisVulnerable = testPlayerBehaviour.isVulnerable(); 
		//assert
		assertTrue(testPlayerBehaviourisVulnerable == true) ;
	}
}

