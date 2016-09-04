package nubiki.game;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;


import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class TestWorld {

	@Test
	public void test() {
		//arrange
		World testWorld1 = new World();
		//act
		GameObject testPlayer= testWorld1.createGameObject("player", 100, 10, 0.0);
		//assert
		Point testPlayerPos= testPlayer.getPos();
		assertEquals( testPlayerPos.x, 100);
		assertEquals( testPlayerPos.y, 10);
	}

}
