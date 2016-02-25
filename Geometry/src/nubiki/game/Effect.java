package nubiki.game;

public interface Effect {
	public void applyTo(GameObject obj); //create interface Affectable instead?
	public boolean isApplicable(Behaviour b);
}
