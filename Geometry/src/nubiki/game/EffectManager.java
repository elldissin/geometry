package nubiki.game;

public class EffectManager {
	public void handle(Collidable o, Effect e) { //create interface "EffectCarrier" or so
		e.applyTo(o);
	}
}
