package my.games.geometry.game.engine;

import my.games.geometry.game.GameCamera;

public class NoRenderEngine implements RenderEngine {

	public NoRenderEngine() {
		// do nothing here
	}

	@Override
	public void render() {
		// do nothing here
	}

	@Override
	public GameCamera getCamera(int cameraNum) {
		return new GameCamera(); // empty camera to show nothing
	}

}
