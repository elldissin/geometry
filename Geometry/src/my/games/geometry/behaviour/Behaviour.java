package my.games.geometry.behaviour;

public interface Behaviour {

	public void slowDown(int amount);

	public void doDamage(int amount);

	public void bump(int amount);

	public void setSlowable(boolean value);

	public void setVulnerable(boolean value);

	public void setBumping(boolean value);

	public void setDestructibleOnBump(boolean value);

	public Behaviour copy();
}
