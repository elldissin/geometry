package my.games.geometry.game.engine;

import my.games.geometry.ui.GameCameraPanel;

public class NoRenderEngine implements RenderEngine {

	public NoRenderEngine() {
		// do nothing here
	}

	@Override
	public void render() {
		// do nothing here
	}

	@Override
	public GameCameraPanel getCamera(int cameraNum) {
		return new GameCameraPanel(); // empty camera to show nothing
	}

	@Override
	public void lockCameraOn(int objID) {

	}

	@Override
	public int getCameraLockedID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
