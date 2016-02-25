package nubiki.game;

public interface Effect {
	public void applyTo(Behaviour b); //create interface Affectable instead?
	public boolean isApplicable(Behaviour b);
}
