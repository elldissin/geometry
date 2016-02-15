package nubiki.game;

public class DmgEffect implements Effect {
	private int amount;
	
	public DmgEffect(int amount) {
		this.amount=amount;
	}
	
	@Override
	public void applyTo(Behaviour b) {
		if (b.isVulnerable()) {
			b.doBehaviour(amount);
		}
	}
}