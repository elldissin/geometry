package my.games.geometry.game.weapons;

import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.NoObject;

public class NoWeapon extends GeneralWeapon implements Weapon {

	public NoWeapon(GameObject ownerObject) {
		super(ownerObject);
	}

	@Override
	public Weapon copy() {
		return this;
	}

	@Override
	protected GameObject createProjectile() {
		return new NoObject(new ObjectPosition(0, 0), 0.0);
	}

}
