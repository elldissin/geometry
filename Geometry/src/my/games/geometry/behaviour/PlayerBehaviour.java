package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class PlayerBehaviour extends GeneralBehaviour implements Behaviour {
	public PlayerBehaviour() {
		super();
		bumping=true;
		vulnerable=true;
		slowable=true;
	}
}
