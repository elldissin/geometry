package my.games.geometry.game.engine;

import my.games.geometry.game.GameCamera;

public interface RenderEngine {
	public void render();

	public GameCamera getCamera(int cameraNum);
}
