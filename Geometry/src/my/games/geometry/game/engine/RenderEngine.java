package my.games.geometry.game.engine;

import my.games.geometry.ui.GameCameraPanel;

public interface RenderEngine {
	public void render();

	public GameCameraPanel getCamera(int cameraNum);

	public void lockCameraOn(int objID);

	public int getCameraLockedID();
}
