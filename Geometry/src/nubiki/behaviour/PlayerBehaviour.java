package nubiki.behaviour;

import nubiki.game.GameObject;

public class PlayerBehaviour extends GeneralBehaviour implements Behaviour {
	public PlayerBehaviour() {
		super();
		bumping=true;
		vulnerable=true;
		slowable=true;
	}
}
