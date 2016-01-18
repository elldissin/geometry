package nubiki.game;

public interface Collidable {
	public boolean isColliding(Collidable obj);
	public double getPosX();
	public double getPosY();
	public double getObjHeight();
	public double getObjWidth();
	public void destroy();
	public void setMaxSpeed(int i);
	public int getMaxSpeed();
}
