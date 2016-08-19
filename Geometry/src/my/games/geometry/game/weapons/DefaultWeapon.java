package my.games.geometry.game.weapons;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.Client;
import my.games.geometry.game.GameObjectManager;
import my.games.geometry.game.objects.GameObject;

public class DefaultWeapon implements Weapon {

	@Override
	public void shoot(GameObject obj) {
		GameObject projectile = GameObjectManager.createGameObject("projectile", obj.getPos().x, obj.getPos().y);
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(1));
		projectile.setBehaviour(new ProjectileBehaviour());
		projectile.setAngle(obj.getAngle());
		Client.addProjectile(projectile); 
		//add each other to ignore list to avoid collisions
		obj.addIgnoreObject(projectile);
		projectile.addIgnoreObject(obj);
	}
}
