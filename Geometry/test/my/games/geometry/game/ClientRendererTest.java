package my.games.geometry.game;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

public class ClientRendererTest {

	@Test
	public void cameraViewOffsetTest() {
		// arrange
		World worldTest = new World();
		GameCamera cameraTest = new GameCamera();
		worldTest.createGameObject("player", 0, 0, 0.0);
		// act
		Point p = new Point((int) worldTest.getObjectByID(1).getPos().x - cameraTest.getViewWidth() / 2,
				(int) worldTest.getObjectByID(1).getPos().y - cameraTest.getViewHeight() / 2);
		cameraTest.setViewOffset(p);
		// assert
		assertEquals(cameraTest.getViewOffset().x, -200);
		assertEquals(cameraTest.getViewOffset().y, -300);
	}

}
