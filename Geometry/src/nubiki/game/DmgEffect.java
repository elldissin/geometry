package nubiki.game;

public class DmgEffect extends GeneralEffect implements Effect {
	
	public DmgEffect(int amount) {
		super(amount);
	}

	@Override
	public boolean isApplicable(Behaviour b) {
		return b.isVulnerable();
	}
	
	@Override
	public void applyTo(GameObject obj) {
		obj.getBehaviour().doDamage(obj, amount);
	}
}