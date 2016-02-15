package nubiki.game;

public interface Behaviour {
	public void doBehaviour(int amount);
	public boolean isSlowable();
	public boolean isVulnerable();
}
