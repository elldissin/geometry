package nubiki.game;

public class EffectManager {
	public void handle(Behaviour b, Effect e) { //create interface "EffectCarrier" or so
		if(b!=null && e!=null)
			e.applyTo(b);
	}
}
