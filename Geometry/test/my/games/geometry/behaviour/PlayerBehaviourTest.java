package my.games.geometry.behaviour;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerBehaviourTest {

	@Test
	public void playerBehaviourTest() {
		// arrange
		PlayerBehaviour playerBehaviourTest = new PlayerBehaviour();
		// act
		boolean playerBehaviourisVulnerableTest = playerBehaviourTest.isVulnerable();
		// assert
		assertTrue(playerBehaviourisVulnerableTest == true);
	}
}
