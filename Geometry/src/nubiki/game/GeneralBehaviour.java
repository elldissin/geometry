package nubiki.game;

public class GeneralBehaviour implements Behaviour {
	protected GameObject ownerObject;
	public boolean slowable;
	public boolean vulnerable;
	
	protected GeneralBehaviour() {
		slowable=false;
		vulnerable=false;
	}
	
	@Override
	public boolean isSlowable() {
		return slowable;
	}

	@Override
	public boolean isVulnerable() {
		return vulnerable;
	}
	
	@Override
	public void slowDown(GameObject obj, int amount) {
		obj.setMaxSpeed((int)(obj.getMaxSpeed()*(100-amount)/100));
		slowable=false; //remove after timer implementation
	}

	@Override
	public void doDamage(GameObject obj, int amount) {
		System.out.println("Player got hit by " + amount +" hp");
		obj.getHit(amount);
	}
}
