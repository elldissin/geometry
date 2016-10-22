package my.games.geometry.game.weapons;

import java.io.Serializable;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public class DefaultWeapon extends GeneralWeapon implements Weapon, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultWeapon(GameObject ownerObject) {
		super(ownerObject);
		shootingDelay = 5;
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

	@Override
	protected Projectile createProjectile() {
		Projectile projectile = new Projectile(ownerObject.getMover().getPos().copy(), ownerObject.getAngle());
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(1));
		projectile.setBehaviour(new ProjectileBehaviour(projectile));
		projectile.setAngle(ownerObject.getAngle());
		return projectile;
	}

}
