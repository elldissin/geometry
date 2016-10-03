package my.games.geometry.behaviour;

public class ProjectileBehaviour extends GeneralBehaviour implements Behaviour {
	public ProjectileBehaviour() {
		super();
		bumping = true;
		destrictibleOnBump = true;
	}

	@Override
	public Behaviour copy() {
		ProjectileBehaviour copy = new ProjectileBehaviour();
		copy.slowable = this.slowable;
		copy.vulnerable = this.vulnerable;
		copy.bumping = this.bumping;
		copy.destrictibleOnBump = this.destrictibleOnBump;
		return copy;
	}
}
