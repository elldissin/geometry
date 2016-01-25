package nubiki.game;

public class SlowEffect implements Effect {

	@Override
	public void applyTo(Behaviour b) {
		if (b.isSlowable())
			b.doBehaviour();
	}
}
