package my.games.geometry.behaviour;

public class PlayerBehaviour extends GeneralBehaviour implements Behaviour {
	public PlayerBehaviour() {
		super();
		bumping = true;
		vulnerable = true;
		slowable = true;
	}

	@Override
	public Behaviour copy() {
		PlayerBehaviour copy = new PlayerBehaviour();
		copy.slowable = this.slowable;
		copy.vulnerable = this.vulnerable;
		copy.bumping = this.bumping;
		copy.destrictibleOnBump = this.destrictibleOnBump;
		return copy;
	}
}
