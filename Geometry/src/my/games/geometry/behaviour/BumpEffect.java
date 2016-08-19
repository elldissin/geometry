package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public class BumpEffect extends GeneralEffect implements Effect {

	public BumpEffect(int amount) {
		super(amount);
	}

	@Override
	public boolean isApplicable(Behaviour b) {
		return b.isBumping();
	}
	
	@Override
	public void applyTo(GameObject obj) {
		obj.getBehaviour().bump(obj, amount);
	}

}