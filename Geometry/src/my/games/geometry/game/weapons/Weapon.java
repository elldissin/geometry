package my.games.geometry.game.weapons;

import java.util.List;

import my.games.geometry.game.engine.ObjectSystem;
import my.games.geometry.game.objects.Projectile;

public interface Weapon extends ObjectSystem {

	void setShooting(boolean value);

	public List<Projectile> getProjectileList();

	public String toString();

	public Weapon copy();

}
