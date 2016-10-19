package my.games.geometry.behaviour;

import my.games.geometry.game.objects.GameObject;

public interface Effect {
	public void applyTo(GameObject obj); // create interface Affectable instead?

	public Effect copy();
}
