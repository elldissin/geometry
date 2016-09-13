package my.games.geometry.game.engine;

import javax.swing.SwingUtilities;

import my.games.geometry.game.GameCamera;
import my.games.geometry.game.World;
import my.games.geometry.game.objects.MenuObject;

public class ClientRenderEngine implements RenderEngine {
	private World world;
	private GameCamera camera1, camera2, camera3;

	public ClientRenderEngine(World world) {
		camera1 = new GameCamera();
		camera2 = new GameCamera();
		camera3 = new MenuObject();
		this.world = world;
	}

	@Override
	public void render() {
		// LATER enable when ID system is complete
		// ties camera 1 to player 1
		// if ((world.getObjectByID(1)) != null) {
		// Point p = new Point((int) world.getObjectByID(1).getPos().x - camera1.getViewWidth() / 2,
		// (int) world.getObjectByID(1).getPos().y - camera1.getViewHeight() / 2);
		// camera1.setViewOffset(p);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				camera1.show(world.getDrawableObjectList());
				camera3.show(world.getDrawableObjectList());
			}
		});

		// }
	}

	public GameCamera getCamera(int number) {
		if (number == 1)
			return camera1;
		if (number == 2)
			return camera2;
		if (number == 3)
			return camera3;
		return new GameCamera();
	}
}
