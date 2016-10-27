package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class ProjectileBehaviour extends GeneralBehaviour implements Behaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectileBehaviour(GameObject ownerObject) {
		super(ownerObject);
		bumping = true;
		destrictibleOnBump = true;
	}

	@Override
	public Behaviour copy() {
		ProjectileBehaviour copy = new ProjectileBehaviour(ownerObject);
		finishCopy(copy);
		return copy;
	}
}
