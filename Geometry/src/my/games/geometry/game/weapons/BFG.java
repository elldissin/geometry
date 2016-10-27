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
		shootingDelay = 20;
	}

	public String toString() {
		return "BFG";
	}

	@Override
	public Weapon copy() {
		BFG copy = new BFG(ownerObject);
		finishCopy(copy);
		return copy;
	}

	@Override
	protected Projectile createProjectile() {
		Projectile projectile = new BFGProjectile(ownerObject.getMover().getPos().copy(),
				ownerObject.getMover().getAngle());
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(10));
		projectile.setBehaviour(new ProjectileBehaviour(projectile));
		projectile.getMover().setAngle(ownerObject.getMover().getAngle());
		return projectile;
	}

}
