package nubiki.game;

public interface Behaviour {
	public boolean isSlowable();
	public boolean isVulnerable();
	public void slowDown(int amount);
	public void doDamage(int amount);
}
