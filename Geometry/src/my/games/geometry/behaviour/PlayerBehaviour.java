package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class PlayerBehaviour extends GeneralBehaviour implements Behaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerBehaviour(GameObject ownerObject) {
		super(ownerObject);
		bumping = true;
		vulnerable = true;
		slowable = true;
	}

	@Override
	public Behaviour copy() {
		PlayerBehaviour copy = new PlayerBehaviour(this.ownerObject.copy());
		finishCopy(copy);
		return copy;
	}
}
