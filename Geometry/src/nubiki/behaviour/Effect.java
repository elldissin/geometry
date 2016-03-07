package nubiki.behaviour;

import nubiki.game.GameObject;

public interface Effect {
	public void applyTo(GameObject obj); //create interface Affectable instead?
	public boolean isApplicable(Behaviour b);
}
