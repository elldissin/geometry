package nubiki.game;

public class DmgBehaviour extends GeneralBehaviour implements Behaviour {

	public DmgBehaviour(GameObject obj) {
		super(obj);
		vulnerable=true;
	}

	@Override
	public void doBehaviour(int amount) {
		if (vulnerable) {
			System.out.println("Player got hit by " + amount +" hp");
			ownerObject.getHit(amount);
		}
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
