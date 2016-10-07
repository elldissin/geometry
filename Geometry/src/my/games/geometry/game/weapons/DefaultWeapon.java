package my.games.geometry.game.weapons;

import java.awt.Point;
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

	public DefaultWeapon() {
		super();
	}

	@Override
	public void shoot(GameObject obj) {
		Projectile projectile = new Projectile((Point) (obj.getPos().clone()), obj.getAngle());
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(1));
		projectile.setBehaviour(new ProjectileBehaviour());
		projectile.setAngle(obj.getAngle());
		// add each other to ignore list to avoid collisions
		obj.addIgnoreObject(projectile);
		projectile.addIgnoreObject(obj);
		synchronized (projectileList) {
			projectileList.add(projectile);
		}
	}

	public String toString() {
		return "DefaultWeapon";
	}

	@Override
	public Weapon copy() {
		DefaultWeapon copy = new DefaultWeapon();
		finishCopy(copy);
		return copy;
	}

}
