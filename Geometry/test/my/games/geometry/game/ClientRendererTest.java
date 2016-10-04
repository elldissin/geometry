package my.games.geometry.game;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import my.games.geometry.ui.GameCameraPanel;

public class ClientRendererTest {

	@Test
	public void cameraViewOffsetTest() {
		// arrange
		World worldTest = new World();
		GameCameraPanel cameraTest = new GameCameraPanel();
		worldTest.createGameObject("player", new Point(0, 0), 0.0);
		// act
		Point p = new Point((int) worldTest.getObjectByID(1).getPos().x - cameraTest.getViewWidth() / 2,
				(int) worldTest.getObjectByID(1).getPos().y - cameraTest.getViewHeight() / 2);
		cameraTest.setViewOffset(p);
		// assert
		assertEquals(cameraTest.getViewOffset().x, -400);
		assertEquals(cameraTest.getViewOffset().y, -300);
	}

}
