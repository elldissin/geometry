package my.games.geometry.game.engine;

import java.awt.Point;

import javax.swing.SwingUtilities;

import my.games.geometry.game.World;
import my.games.geometry.ui.GameCameraPanel;

public class ClientRenderEngine implements RenderEngine {
	private World world;
	private GameCameraPanel camera;
	private int cameraLockObjID;

	public ClientRenderEngine(World world) {
		camera = new GameCameraPanel();
		this.world = world;
	}

	@Override
	public void render() {
		// FIXME disabled camera lock until client knowns his object id
		if ((world.getObjectByID(cameraLockObjID)) != null) {
			Point p = new Point((int) world.getObjectByID(cameraLockObjID).getPos().getX() - camera.getViewWidth() / 2,
					(int) world.getObjectByID(cameraLockObjID).getPos().getY() - camera.getViewHeight() / 2);
			camera.setViewOffset(p);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				camera.show(world.getDrawableObjectList());
				// System.out.println(world.getDrawableObjectList().size());
			}
		});

		// }
	}

	public GameCameraPanel getCamera(int number) {
		if (number == 1)
			return camera;
		return new GameCameraPanel();
	}

	public void lockCameraOn(int objID) {
		cameraLockObjID = objID;
	}

	public int getCameraLockedID() {
		return cameraLockObjID;
	}
}
