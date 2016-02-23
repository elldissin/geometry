package nubiki.game;

public class SlowBehaviour extends GeneralBehaviour implements Behaviour {

	public SlowBehaviour(GameObject obj) {
		super(obj);
		slowable=true;
		vulnerable=true;
	}
	
	@Override
	public boolean isSlowable() {
		return slowable;
	}

	@Override
	public boolean isVulnerable() {
		return vulnerable;
	}
	
	
}
