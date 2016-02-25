package nubiki.game;

public class GeneralBehaviour implements Behaviour {
	protected GameObject ownerObject;
	public boolean slowable;
	public boolean vulnerable;
	
	protected GeneralBehaviour(GameObject obj) {
		ownerObject=obj;
		slowable=false;
		vulnerable=false;
	}
	
//	abstract public void doBehaviour(int amount);
	
	@Override
	public boolean isSlowable() {
		return slowable;
	}

	@Override
	public boolean isVulnerable() {
		return vulnerable;
	}
	
	@Override
	public void slowDown(int amount) {
		ownerObject.setMaxSpeed((int)(ownerObject.getMaxSpeed()*(100-amount)/100));
		slowable=false;
	}

	@Override
	public void doDamage(int amount) {
		System.out.println("Player got hit by " + amount +" hp");
		ownerObject.getHit(amount);
	}
}
