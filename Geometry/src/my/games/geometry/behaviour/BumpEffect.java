package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class BumpEffect extends GeneralEffect implements Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BumpEffect(int amount) {
		super(amount);
	}

	@Override
	public void applyTo(GameObject obj) {
		obj.getBehaviour().bump(amount);
	}

	@Override
	public Effect copy() {
		BumpEffect copy = new BumpEffect(this.amount);
		return copy;
	}

}