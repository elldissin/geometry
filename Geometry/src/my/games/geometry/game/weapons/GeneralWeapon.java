package my.games.geometry.game.weapons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public abstract class GeneralWeapon implements Weapon, Serializable {
	protected List<GameObject> projectileList;
	protected boolean shooting;
	protected GameObject ownerObject;
	protected int shootingDelay;
	protected double deltasCounter;

	public GeneralWeapon(GameObject ownerObject) {
		this.ownerObject = ownerObject;
		projectileList = new ArrayList<GameObject>();
		shooting = false;
		deltasCounter = 0;
	}

	@Override
	public void function(double delta) {
		shootIfShooting(delta);
	}

	protected void shootIfShooting(double delta) {
		deltasCounter += delta;
		if (shooting) {
			if (deltasCounter > shootingDelay) {
				deltasCounter = 0;
				GameObject projectile = createProjectile();
				// add each other to ignore list to avoid collisions
				ownerObject.addIgnoreObject(projectile);
				projectile.addIgnoreObject(ownerObject);
				synchronized (projectileList) {
					projectileList.add(projectile);
				}
			}
		}
	}

	protected abstract GameObject createProjectile();

	@Override
	public void setShooting(boolean value) {
		shooting = value;
	}

	@Override
	public List<GameObject> getProjectileList() {
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
