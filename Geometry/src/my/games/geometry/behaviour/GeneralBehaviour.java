package my.games.geometry.behaviour;

import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public abstract class GeneralBehaviour implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean slowable;
	protected boolean vulnerable;
	protected boolean bumping;
	protected boolean destrictibleOnBump;
	protected GameObject ownerObject;

	public GeneralBehaviour(GameObject ownerObject) {
		this.ownerObject = ownerObject;
		slowable = false;
		vulnerable = false;
		bumping = false;
	}

	public void slowDown(int amount) {
		if (slowable) {
			ownerObject.getMover().setSpeed((int) (ownerObject.getMover().getSpeed() * (100 - amount) / 100));
		}
		slowable = false; // LATER remove after timer implementation
	}

	public void doDamage(int amount) {
		if (vulnerable) {
			ownerObject.getHit(amount);
		}
	}

	public void bump(int amount) {
		if (bumping) {
			ownerObject.setPos(ownerObject.getPrevPos().copy());
			if (destrictibleOnBump)
				ownerObject.setObsolete(true);
		}
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
