package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class SlowEffect extends GeneralEffect implements Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SlowEffect(int amount) {
		super(amount);
	}

	@Override
	public void applyTo(GameObject obj) {
		obj.getBehaviour().slowDown(amount);
	}

	@Override
	public Effect copy() {
		SlowEffect copy = new SlowEffect(this.amount);
		return copy;
	}
}
