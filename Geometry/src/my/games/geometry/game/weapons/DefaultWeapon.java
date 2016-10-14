package my.games.geometry.game.weapons;

import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public class DefaultWeapon extends GeneralWeapon implements Weapon, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultWeapon(GameObject ownerObject) {
		super(ownerObject);
	}

	public String toString() {
		return "DefaultWeapon";
	}

	@Override
	public Weapon copy() {
		DefaultWeapon copy = new DefaultWeapon(this.ownerObject.copy());
		finishCopy(copy);
		return copy;
	}

}
