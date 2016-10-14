package my.games.geometry.game.objects;

public interface Updatable {
	public void update(double delta);

	public void destroy();

	public boolean isDestroyed(); // LATER destroyed or obsolete?

}
