package my.games.geometry.ui.engine;

import my.games.geometry.ui.GameCameraPanel;
import my.games.geometry.ui.GameStatusPanel;

public interface RenderEngine {

	public void render();

	public GameCameraPanel getCamera();

	public GameStatusPanel getStatusBar();

	public void setFocusedObjectID(int objID);
}
