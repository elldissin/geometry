package nubiki.game;

public interface Behaviour {
	public boolean isSlowable();
	public boolean isVulnerable();
	public void slowDown(GameObject obj, int amount);
	public void doDamage(GameObject obj, int amount);
}
