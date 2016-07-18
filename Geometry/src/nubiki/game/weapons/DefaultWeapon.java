package nubiki.game.weapons;

import java.awt.Point;

import nubiki.behaviour.DmgEffect;
import nubiki.behaviour.ProjectileBehaviour;
import nubiki.behaviour.SlowEffect;
import nubiki.game.GameManager;
import nubiki.game.GameObject;
import nubiki.game.GameObjectManager;

public class DefaultWeapon implements Weapon {

	@Override
	public void shoot(GameObject obj) {
		GameObject projectile = GameObjectManager.createGameObject("projectile", obj.getPos().x, obj.getPos().y);
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(1));
		projectile.setBehaviour(new ProjectileBehaviour());
		projectile.setAngle(obj.getAngle());
		GameManager.addProjectile(projectile); 
		//add each other to ignore list to avoid collisions
		obj.addIgnoreObject(projectile);
		projectile.addIgnoreObject(obj);
	}
}
