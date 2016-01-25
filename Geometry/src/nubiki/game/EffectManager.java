package nubiki.game;

public class EffectManager {
	public void handle(Behaviour b, Effect e) { //create interface "EffectCarrier" or so
		e.applyTo(b);
	}
}
