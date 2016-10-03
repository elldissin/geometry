package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class SlowEffect extends GeneralEffect implements Effect {

	public SlowEffect(int amount) {
		super(amount);
	}

	@Override
	public boolean isApplicable(Behaviour b) {
		return b.isSlowable();
	}

	@Override
	public void applyTo(GameObject obj) {
		obj.getBehaviour().slowDown(obj, amount);
	}

	@Override
	public Effect copy() {
		SlowEffect copy = new SlowEffect(this.amount);
		return copy;
	}
}
