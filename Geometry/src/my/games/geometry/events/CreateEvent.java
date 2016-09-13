package my.games.geometry.events;

import my.games.geometry.game.World;
import my.games.geometry.game.objects.GameObject;

public class CreateEvent extends GameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateEvent(int targetID) {
		super(targetID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyEventToWorld(World world) {
		GameObject obj = getCarriedObject();
		if (obj != null)
			world.createGameObject(obj);
	}

}
