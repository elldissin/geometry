package nubiki.game;

public class SlowEffect extends GeneralEffect implements Effect {
	
	public SlowEffect(int amount) {
		super(amount);
	}

	@Override
	public boolean isApplicable(Behaviour b) {
		return b.isSlowable();
	}
	
	@Override
	public void applyTo(Behaviour b) {
		b.slowDown(amount);
	}
}
