package my.games.geometry.game;

import my.games.geometry.behaviour.Effect;
import my.games.geometry.game.objects.GameObject;

public class EffectManager {
	public void handle(GameObject obj, Effect e) { //create interface "EffectCarrier" or so
		if(obj!=null && e!=null && e.isApplicable(obj.getBehaviour()))
			e.applyTo(obj);
	}
}