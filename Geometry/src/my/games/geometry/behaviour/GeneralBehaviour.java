package my.games.geometry.behaviour;

import java.awt.Point;
import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public class GeneralBehaviour implements Behaviour, Serializable {
	protected boolean slowable;
	protected boolean vulnerable;
	protected boolean bumping;
	protected boolean destrictibleOnBump;

	public GeneralBehaviour() {
		slowable = false;
		vulnerable = false;
		bumping = false;
	}

	@Override
	public boolean isSlowable() {
		return slowable;
	}

	@Override
	public boolean isVulnerable() {
		return vulnerable;
	}

	@Override
	public boolean isBumping() {
		return bumping;
	}

	@Override
	public void slowDown(GameObject obj, int amount) {
		obj.getMover().setSpeed((int) (obj.getMover().getSpeed() * (100 - amount) / 100));
		slowable = false; // LATER remove after timer implementation
	}

	@Override
	public void doDamage(GameObject obj, int amount) {
		// System.out.println("Player got hit by " + amount +" hp");
		obj.getHit(amount);
	}

	@Override
	public void bump(GameObject obj, int amount) {
		obj.setPos((Point) (obj.getPrevPos().clone()));
		if (destrictibleOnBump)
			obj.setObsolete(true);
	}

	@Override
	public void setSlowable(boolean value) {
		slowable = value;
	}

	@Override
	public void setVulnerable(boolean value) {
		vulnerable = value;
	}

	@Override
	public void setBumping(boolean value) {
		bumping = value;
	}

	@Override
	public boolean destructibleOnBump() {
		return destrictibleOnBump;
	}

	@Override
	public void setDestructibleOnBump(boolean value) {
		destrictibleOnBump = value;
	}
}
