package my.games.geometry.behaviour;

import java.io.Serializable;

abstract public class GeneralEffect implements Effect, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int amount;

	public GeneralEffect(int amount) {
		this.amount = amount;
	}

	// @Override
	// public void applyTo(Behaviour b) {
	// b.doBehaviour(amount);
	// }
}
