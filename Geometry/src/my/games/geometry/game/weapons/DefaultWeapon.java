package my.games.geometry.game.weapons;

import java.util.ArrayList;
import java.util.List;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public class DefaultWeapon implements Weapon {

	private List<Projectile> projectileList;

	public DefaultWeapon() {
		projectileList = new ArrayList<Projectile>();
	}

	@Override
	public void shoot(GameObject obj) {
		Projectile projectile = new Projectile(obj.getPos().x, obj.getPos().y, obj.getAngle());
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(1));
		projectile.setBehaviour(new ProjectileBehaviour());
		projectile.setAngle(obj.getAngle());
		// add each other to ignore list to avoid collisions
		obj.addIgnoreObject(projectile);
		projectile.addIgnoreObject(obj);
		projectileList.add(projectile);
	}

	@Override
	public List<Projectile> getProjectileList() {
		return projectileList;
	}
}
