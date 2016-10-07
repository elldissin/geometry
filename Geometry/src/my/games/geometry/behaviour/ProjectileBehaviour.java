package my.games.geometry.behaviour;

public class ProjectileBehaviour extends GeneralBehaviour implements Behaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectileBehaviour() {
		super();
		bumping = true;
		destrictibleOnBump = true;
	}

	@Override
	public Behaviour copy() {
		ProjectileBehaviour copy = new ProjectileBehaviour();
		finishCopy(copy);
		return copy;
	}
}
