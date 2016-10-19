package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class NoBehaviour extends GeneralBehaviour implements Behaviour {

	public NoBehaviour(GameObject ownerObject) {
		super(ownerObject);
		// TO BE EMPTY
	}

	@Override
	public Behaviour copy() {
		return this;
	}

}
