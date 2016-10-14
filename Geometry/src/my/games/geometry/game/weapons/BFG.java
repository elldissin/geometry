package my.games.geometry.game.weapons;

import java.io.Serializable;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.objects.BFGProjectile;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public class BFG extends GeneralWeapon implements Weapon, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BFG(GameObject ownerObject) {
		super(ownerObject);
	}

	@Override
	public void shootIfShooting(double delta) {
		if (shooting) {
			Projectile projectile = new BFGProjectile(ownerObject.getPos().copy(), ownerObject.getAngle());
			projectile.addOnHitEffect(new SlowEffect(20));
			projectile.addOnHitEffect(new DmgEffect(10));
			projectile.setBehaviour(new ProjectileBehaviour());
			projectile.setAngle(ownerObject.getAngle());
			// add each other to ignore list to avoid collisions
			ownerObject.addIgnoreObject(projectile);
			projectile.addIgnoreObject(ownerObject);
			synchronized (projectileList) {
				projectileList.add(projectile);
			}
		}
	}

	public String toString() {
		return "BFG";
	}

	@Override
	public Weapon copy() {
		BFG copy = new BFG(this.ownerObject.copy());
		finishCopy(copy);
		return copy;
	}

}
