package nubiki.game;

public class DmgBehaviour extends GeneralBehaviour implements Behaviour {

	public DmgBehaviour(GameObject obj) {
		super(obj);
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
