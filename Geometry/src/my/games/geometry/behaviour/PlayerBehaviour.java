package my.games.geometry.behaviour;

public class PlayerBehaviour extends GeneralBehaviour implements Behaviour {
	public PlayerBehaviour() {
		super();
		bumping=true;
		vulnerable=true;
		slowable=true;
	}
}
