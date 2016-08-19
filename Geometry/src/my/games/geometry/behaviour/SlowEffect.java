package my.games.geometry.behaviour;

import my.games.geometry.game.GameObject;

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
}
