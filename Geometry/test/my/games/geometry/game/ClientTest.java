package my.games.geometry.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.awt.event.KeyListener;

import org.junit.Ignore;
import org.junit.Test;

public class ClientTest {

	@Ignore
	@Test
	public void getCameraTest() {
		Client clientTest = new Client();
		GameCamera cam1 = clientTest.getRenderEngine().getCamera(1);
		GameCamera cam2 = clientTest.getRenderEngine().getCamera(2);
		GameCamera cam3 = clientTest.getRenderEngine().getCamera(3);
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
