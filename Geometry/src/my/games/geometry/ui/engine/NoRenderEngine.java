package my.games.geometry.ui.engine;

import my.games.geometry.ui.GameCameraPanel;
import my.games.geometry.ui.GameStatusPanel;

public class NoRenderEngine implements RenderEngine {

	public NoRenderEngine() {
		// do nothing here
	}

	@Override
	public void render() {
		// do nothing here
	}

	@Override
	public GameCameraPanel getCamera() {
		return new GameCameraPanel(); // empty camera to show nothing
	}

	@Override
	public void setFocusedObjectID(int objID) {

	}

	@Override
	public GameStatusPanel getStatusBar() {
		return new GameStatusPanel();
	}

}
