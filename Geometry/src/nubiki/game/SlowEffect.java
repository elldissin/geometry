package nubiki.game;

public class SlowEffect implements Effect {
	private int amount;
	
	public SlowEffect(int amount) {
		this.amount=amount;
	}
	
	@Override
	public void applyTo(Behaviour b) {
		if (b.isSlowable())
			b.doBehaviour(amount);
	}
}
