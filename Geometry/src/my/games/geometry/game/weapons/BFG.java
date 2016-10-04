package my.games.geometry.game.weapons;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import my.games.geometry.behaviour.DmgEffect;
import my.games.geometry.behaviour.ProjectileBehaviour;
import my.games.geometry.behaviour.SlowEffect;
import my.games.geometry.game.objects.BFGProjectile;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public class BFG implements Weapon, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Projectile> projectileList; // lATER why not GameObject here?

	public BFG() {
		projectileList = new ArrayList<Projectile>();
	}

	@Override
	public void shoot(GameObject obj) {
		Projectile projectile = new BFGProjectile((Point) (obj.getPos().clone()), obj.getAngle());
		projectile.addOnHitEffect(new SlowEffect(20));
		projectile.addOnHitEffect(new DmgEffect(10));
		projectile.setBehaviour(new ProjectileBehaviour());
		projectile.setAngle(obj.getAngle());
		// add each other to ignore list to avoid collisions
		obj.addIgnoreObject(projectile);
		projectile.addIgnoreObject(obj);
		projectileList.add(projectile);
	}

	@Override
	public List<Projectile> getProjectileList() {
		// TODO Auto-generated method stub
		return projectileList;
	}

	public String toString() {
		return "BFG";
	}

	@Override
	public Weapon copy() {
		BFG copy = new BFG();
		for (int i = 0; i < this.projectileList.size(); i++) {
			copy.projectileList.add((Projectile) projectileList.get(i).copy());
		}
		return copy;
	}

}
