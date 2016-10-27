package my.games.geometry.game.engine;

import my.games.geometry.game.objects.GameObject;

/**
 * @author LCrystal Represents system that each object may or may not have. Object is therefore
 *         working as a set of systems.
 *
 */
public interface ObjectSystem {
	void function(double delta);

	void setOwnerObject(GameObject ownerObject);
}
