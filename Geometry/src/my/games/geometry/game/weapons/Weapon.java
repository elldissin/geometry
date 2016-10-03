package my.games.geometry.game.weapons;

import java.util.List;

import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Projectile;

public interface Weapon {
	public void shoot(GameObject obj);

	public List<Projectile> getProjectileList();

	public String toString();

	public Weapon copy();

}
