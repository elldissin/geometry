package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class DmgEffect extends GeneralEffect implements Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DmgEffect(int amount) {
		super(amount);
	}

	@Override
	public void applyTo(GameObject obj) {
		obj.getBehaviour().doDamage(amount);
	}

	@Override
	public Effect copy() {
		DmgEffect copy = new DmgEffect(this.amount);
		return copy;
	}
}