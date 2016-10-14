package my.games.geometry.game.weapons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public abstract class GeneralWeapon implements Weapon, Serializable {
	protected List<Projectile> projectileList; // LATER why not GameObject here?
	protected boolean shooting;
	protected GameObject ownerObject;

	public GeneralWeapon(GameObject ownerObject) {
		this.ownerObject = ownerObject;
		projectileList = new ArrayList<Projectile>();
		shooting = false;
	}

	@Override
	public void function(double delta) {
		shootIfShooting(delta);
	}

	protected void shootIfShooting(double delta) {
		if (shooting) {
			Projectile projectile = new Projectile(ownerObject.getPos().copy(), ownerObject.getAngle());
			projectile.addOnHitEffect(new SlowEffect(20));
			projectile.addOnHitEffect(new DmgEffect(1));
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

	@Override
	public void setShooting(boolean value) {
		shooting = value;
	}

	@Override
	public List<Projectile> getProjectileList() {
		return projectileList;
	}

	protected void finishCopy(GeneralWeapon copy) {
		synchronized (projectileList) {
			for (int i = 0; i < this.projectileList.size(); i++) {
				copy.projectileList.add((Projectile) projectileList.get(i).copy());
			}
		}
	}
}
