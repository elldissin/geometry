package my.games.geometry.game;

import static org.junit.Assert.assertNotNull;

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
		GameCameraPanel cam1 = clientTest.getRenderEngine().getCamera();
		assertNotNull(cam1);
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
