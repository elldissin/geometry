package nubiki.game;

public class EffectManager {
	public void handle(GameObject obj, Effect e) { //create interface "EffectCarrier" or so
		if(obj!=null && e!=null && e.isApplicable(obj.getBehaviour()))
			e.applyTo(obj);
	}
}
