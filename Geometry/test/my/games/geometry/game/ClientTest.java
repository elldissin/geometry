package my.games.geometry.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.awt.event.KeyListener;

import org.junit.Ignore;
import org.junit.Test;

import my.games.geometry.game.engine.Client;
import my.games.geometry.ui.GameCameraPanel;

public class ClientTest {

	@Ignore
	@Test
	public void getCameraTest() {
		Client clientTest = new Client();
		GameCameraPanel cam1 = clientTest.getRenderEngine().getCamera(1);
		GameCameraPanel cam2 = clientTest.getRenderEngine().getCamera(2);
		GameCameraPanel cam3 = clientTest.getRenderEngine().getCamera(3);
		assertNull("Camera 3 must not exists", cam3);
		assertNotNull(cam1);
		assertNotNull(cam2);
		// fail("Not yet implemented");
	}

	@Test
	public void testGetController() {
		Client clientTest = new Client();
		KeyListener ctrl1 = clientTest.getController();
		assertNotNull(ctrl1);
		// fail("Not yet implemented");
	}

}
