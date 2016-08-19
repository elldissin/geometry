package my.games.geometry.game;

public interface Controllable {
	public void setMoving();
	public void setTurningCW();
	public void setTurningCCW();
	public void setNotTurning();
	public void setStopped();
	public void shoot();
}
