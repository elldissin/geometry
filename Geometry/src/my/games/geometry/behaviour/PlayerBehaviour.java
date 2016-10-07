package my.games.geometry.behaviour;

public class PlayerBehaviour extends GeneralBehaviour implements Behaviour {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerBehaviour() {
		super();
		bumping = true;
		vulnerable = true;
		slowable = true;
	}

	@Override
	public Behaviour copy() {
		PlayerBehaviour copy = new PlayerBehaviour();
		finishCopy(copy);
		return copy;
	}
}
