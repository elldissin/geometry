package nubiki.game;

import static org.junit.Assert.*;

import java.awt.event.KeyListener;

import org.junit.Ignore;
import org.junit.Test;

import my.games.geometry.game.GameCamera;
import my.games.geometry.game.Client;


public class TestGameManager {

	@Ignore
	@Test
	public void testGetCamera() {
		Client mng = new Client();
		GameCamera cam1 = mng.getRenderEngine().getCamera(1);
		GameCamera cam2 = mng.getRenderEngine().getCamera(2);
		GameCamera cam3 = mng.getRenderEngine().getCamera(3);
		assertNull("Camera 3 must not exists",cam3);
		assertNotNull(cam1);
		assertNotNull(cam2);
//		fail("Not yet implemented");
	}
	
	@Test
	public void testGetController() {
		Client mng = new Client();
		KeyListener ctrl1 = mng.getController();
		assertNotNull(ctrl1);
//		fail("Not yet implemented");
	}

}
