package nubiki.behaviour;

import nubiki.game.GameObject;

public interface Behaviour {
	public boolean isSlowable();
	public boolean isVulnerable();
	public boolean isBumping();
	public void slowDown(GameObject obj, int amount);
	public void doDamage(GameObject obj, int amount);
	public void bump(GameObject obj, int amount);

}
