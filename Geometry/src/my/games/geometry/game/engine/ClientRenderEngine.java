package my.games.geometry.game.engine;

import java.awt.Point;

import javax.swing.SwingUtilities;

import my.games.geometry.game.World;
import my.games.geometry.ui.GameCameraPanel;
import my.games.geometry.ui.GameStatusPanel;

public class ClientRenderEngine implements RenderEngine {
	private World world;
	private GameCameraPanel camera;
	private GameStatusPanel statusBar;
	private int cameraLockObjID;

	public ClientRenderEngine(World world) {
		camera = new GameCameraPanel();
		statusBar = new GameStatusPanel();
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
		statusBar.setDisplayedObject(world.getObjectByID(cameraLockObjID));
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				camera.show(world.getDrawableObjectList());
				statusBar.show(world.getDrawableObjectList());
				// System.out.println(world.getDrawableObjectList().size());
			}
		});

		// }
	}

	public GameCameraPanel getCamera(int number) {
		if (number == 1)
			return camera;
		if (number == 2)
			return statusBar;
		return new GameCameraPanel();
	}

	public void lockCameraOn(int objID) {
		cameraLockObjID = objID;
	}
}
