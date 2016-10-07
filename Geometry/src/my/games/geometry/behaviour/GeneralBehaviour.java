package my.games.geometry.behaviour;

import java.awt.Point;
import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public abstract class GeneralBehaviour implements Serializable {
	protected boolean slowable;
	protected boolean vulnerable;
	protected boolean bumping;
	protected boolean destrictibleOnBump;

	public GeneralBehaviour() {
		slowable = false;
		vulnerable = false;
		bumping = false;
	}

	public boolean isSlowable() {
		return slowable;
	}

	public boolean isVulnerable() {
		return vulnerable;
	}

	public boolean isBumping() {
		return bumping;
	}

	public void slowDown(GameObject obj, int amount) {
		obj.getMover().setSpeed((int) (obj.getMover().getSpeed() * (100 - amount) / 100));
		slowable = false; // LATER remove after timer implementation
	}

	public void doDamage(GameObject obj, int amount) {
		// System.out.println("Player got hit by " + amount +" hp");
		obj.getHit(amount);
	}

	public void bump(GameObject obj, int amount) {
		obj.setPos((Point) (obj.getPrevPos().clone()));
		if (destrictibleOnBump)
			obj.setObsolete(true);
	}

	public void setSlowable(boolean value) {
		slowable = value;
	}

	public void setVulnerable(boolean value) {
		vulnerable = value;
	}

	public void setBumping(boolean value) {
		bumping = value;
	}

	public boolean destructibleOnBump() {
		return destrictibleOnBump;
	}

	public void setDestructibleOnBump(boolean value) {
		destrictibleOnBump = value;
	}

	protected void finishCopy(GeneralBehaviour copyToWorkWith) {
		copyToWorkWith.slowable = this.slowable;
		copyToWorkWith.vulnerable = this.vulnerable;
		copyToWorkWith.bumping = this.bumping;
		copyToWorkWith.destrictibleOnBump = this.destrictibleOnBump;
	}
}
