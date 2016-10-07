package my.games.geometry.game.weapons;

import java.util.ArrayList;
import java.util.List;

import my.games.geometry.game.objects.Projectile;

public abstract class GeneralWeapon {
	protected List<Projectile> projectileList; // LATER why not GameObject here?

	public GeneralWeapon() {
		projectileList = new ArrayList<Projectile>();
	}

	public List<Projectile> getProjectileList() {
		return projectileList;
	}

	public void finishCopy(Weapon copy) {
		synchronized (projectileList) {
			for (int i = 0; i < this.projectileList.size(); i++) {
				copy.getProjectileList().add((Projectile) projectileList.get(i).copy());
			}
		}
	}
}
