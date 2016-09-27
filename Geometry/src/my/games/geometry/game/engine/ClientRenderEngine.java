package my.games.geometry.game.engine;

import javax.swing.SwingUtilities;

import my.games.geometry.game.World;
import my.games.geometry.ui.GameStatusPanel;
import my.games.geometry.ui.GameCameraPanel;

public class ClientRenderEngine implements RenderEngine {
	private World world;
	private GameCameraPanel camera1, camera2, camera3;

	public ClientRenderEngine(World world) {
		camera1 = new GameCameraPanel();
		camera2 = new GameCameraPanel();
		camera3 = new GameStatusPanel();
		this.world = world;
	}

	@Override
	public void render() {
		// FIXME disabled camera lock until client knowns his object id
		// if ((world.getObjectByID(1)) != null) {
		// Point p = new Point((int) world.getObjectByID(1).getPos().x - camera1.getViewWidth() / 2,
		// (int) world.getObjectByID(1).getPos().y - camera1.getViewHeight() / 2);
		// camera1.setViewOffset(p);
		// }
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				camera1.show(world.getDrawableObjectList());
				camera3.show(world.getDrawableObjectList());
				// System.out.println(world.getDrawableObjectList().size());
			}
		});

		// }
	}

	public GameCameraPanel getCamera(int number) {
		if (number == 1)
			return camera1;
		if (number == 2)
			return camera2;
		if (number == 3)
			return camera3;
		return new GameCameraPanel();
	}
}
