package my.games.geometry.ui.engine;

import javax.swing.SwingUtilities;

import my.games.geometry.game.engine.World;
import my.games.geometry.ui.GameCameraPanel;
import my.games.geometry.ui.GameStatusPanel;

public class ClientRenderEngine implements RenderEngine {
	private World world;
	private GameCameraPanel camera;
	private GameStatusPanel statusBar;
	private int focusedObjectID;

	public ClientRenderEngine(World world) {
		camera = new GameCameraPanel();
		statusBar = new GameStatusPanel();
		camera.setFocusedObject(world.getObjectByID(focusedObjectID));
		statusBar.setFocusedObject(world.getObjectByID(focusedObjectID));
		this.world = world;
	}

	@Override
	public void render() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				camera.show(world.getDrawableObjectList());
				statusBar.show(world.getDrawableObjectList());
			}
		});
	}

	@Override
	public GameCameraPanel getCamera() {
		return camera;
	}

	@Override
	public GameStatusPanel getStatusBar() {
		return statusBar;
	}

	public void setFocusedObjectID(int objID) {
		focusedObjectID = objID;
	}

	public int getCameraLockedID() {
		return focusedObjectID;
	}
}
