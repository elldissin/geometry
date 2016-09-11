package my.games.geometry.events;

import my.games.geometry.game.World;

public class CreateEvent extends GameEvent {

	public CreateEvent(int targetID) {
		super(targetID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyEventToWorld(World world) {
		world.createGameObject("projectile", 0, 0, 0.0);
	}

}
