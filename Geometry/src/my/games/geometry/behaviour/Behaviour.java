package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public interface Behaviour {
	public boolean isSlowable();

	public boolean isVulnerable();

	public boolean isBumping();

	public boolean destructibleOnBump();

	public void slowDown(GameObject obj, int amount);

	public void doDamage(GameObject obj, int amount);

	public void bump(GameObject obj, int amount);

	public void setSlowable(boolean value);

	public void setVulnerable(boolean value);

	public void setBumping(boolean value);

	public void setDestructibleOnBump(boolean value);
	// test
}
