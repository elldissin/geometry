package my.games.geometry.game.engine;

import java.awt.Point;
import java.util.List;

import my.games.geometry.game.GameCamera;
import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class ClientRenderEngine implements RenderEngine {
	private World world;
	private GameCamera camera1, camera2;

	public ClientRenderEngine(World world) {
		camera1 = new GameCamera();
		camera2 = new GameCamera();
		this.world = world;
	}

	@Override
	public void render() {
		// ties camera 1 to player 1
		Point p = new Point((int) world.getObjectByID(0).getPos().x - camera1.getViewWidth() / 2,
				(int) world.getObjectByID(0).getPos().y - camera1.getViewHeight() / 2);
		camera1.setViewOffset(p);
		camera1.show(world.getDrawableObjectList());

		// ties camera 2 to player 2
		p = new Point((int) world.getObjectByID(1).getPos().x - camera2.getViewWidth() / 2,
				(int) world.getObjectByID(1).getPos().y - camera2.getViewHeight() / 2);
		camera2.setViewOffset(p);
		camera2.show(world.getDrawableObjectList());
	}

	public GameCamera getCamera(int number) {
		if (number == 1)
			return camera1;
		if (number == 2)
			return camera2;
		return new GameCamera();
	}
}
