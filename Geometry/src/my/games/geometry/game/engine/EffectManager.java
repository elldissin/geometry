package my.games.geometry.game.engine;

import my.games.geometry.behaviour.Effect;
import my.games.geometry.game.objects.GameObject;

public class EffectManager {
	public void handle(GameObject obj, Effect e) { // create interface "EffectCarrier" or so
		if (obj != null && e != null)
			e.applyTo(obj);
	}
}
