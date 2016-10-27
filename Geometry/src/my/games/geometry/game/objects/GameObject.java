package my.games.geometry.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.Effect;
import my.games.geometry.behaviour.NoBehaviour;
import my.games.geometry.events.GameEvent;
import my.games.geometry.events.util.EventObserver;
import my.games.geometry.game.engine.ObjectPosition;
import my.games.geometry.game.engine.ObjectShape;
import my.games.geometry.game.movers.Mover;
import my.games.geometry.game.renderers.Renderer;
import my.games.geometry.game.weapons.Weapon;
import my.games.geometry.util.UniqueIdProvider;

public abstract class GameObject implements Updatable, Serializable {
	private static final long serialVersionUID = 1L;
	protected int objectID;
	protected int objWidth, objHeight;
	protected int health;
	protected int level;
	protected int experienceForUp;
	protected int currentExperience;
	protected boolean obsolete;
	protected Behaviour behaviour;
	protected Mover mover;
	protected Weapon weapon;
	protected Renderer renderer;
	protected List<EventObserver> eventObserverList;

	protected ObjectShape shape;
	protected List<GameObject> ignoredObjects;
	protected List<Effect> onHitEffects;

	public GameObject(ObjectPosition position, double angle) {
		super();
		objectID = UniqueIdProvider.getObjectID();
		eventObserverList = new CopyOnWriteArrayList<EventObserver>();
		objWidth = 20;
		objHeight = 20;
		obsolete = false;
		shape = new ObjectShape();
		ignoredObjects = new CopyOnWriteArrayList<GameObject>();
		onHitEffects = new CopyOnWriteArrayList<Effect>();
	}

	public int getHealth() {
		return health;
	}

	public int getLevel() {
		return level;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	public int getObjWidth() {
		return objWidth;
	}

	public void setObjWidth(int objWidth) {
		this.objWidth = objWidth;
	}

	public int getObjHeight() {
		return objHeight;
	}

	public void setObjHeight(int objHeight) {
		this.objHeight = objHeight;
	}

	public List<Effect> getOnHitEffects() {
		return onHitEffects;
	}

	public void addOnHitEffect(Effect e) {
		onHitEffects.add(e);
	}

	public Behaviour getBehaviour() {
		if (behaviour != null)
			return behaviour;
		else
			return new NoBehaviour(this);
	}

	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	public void draw(Graphics g) {
		renderer.draw(g);
	}

	public abstract ObjectShape rebuildShape();

	public void addIgnoreObject(GameObject o) {
		if (o != null)
			ignoredObjects.add(o);
	}

	public boolean isColliding(GameObject o) {
		if (!ignoredObjects.contains(o)) {
			GameObject temp = (GameObject) o;
			if (boundingRect().intersects(temp.boundingRect()))
				return true;
			return false;
		}
		return false;
	}

	public Rectangle boundingRect() {
		Rectangle rec;
		rec = new Rectangle(mover.getPos().getIntX() - getObjWidth(), mover.getPos().getIntY() - getObjHeight(),
				(int) (getObjWidth()) * 2, (int) (getObjHeight()) * 2);
		return rec;
	}

	public void setMoving(boolean value) {
		mover.setMoving(value);
	}

	public void setTurning(Mover.TurnDirection dir, boolean value) {
		mover.setTurning(dir, value);
	}

	public void setShooting(boolean value) {
		weapon.setShooting(value);
	}

	abstract public void getHit(int amount);

	public Mover getMover() {
		return mover;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setMover(Mover mover) {
		this.mover = mover;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public ObjectShape getShape() {
		return shape;
	}

	@Override
	public void update(double delta) { // LATER use array of ObjectSystem ?
		mover.function(delta);
		weapon.function(delta);
	}

	public void notifyObserversAbout(GameEvent event) {
		for (int i = 0; i < eventObserverList.size(); i++)
			eventObserverList.get(i).notifyAboutEvent(this, event);

	}

	@Override
	public boolean isDestroyed() {
		return obsolete;
	}

	@Override
	public void destroy() {
		obsolete = true;
	}

	// LATER removeEventObserver required?
	public void registerObserver(EventObserver observer) {
		eventObserverList.add(observer);
	}

	public int getExperienceForUp() {
		return experienceForUp;
	}

	public void setExperienceForUp(int experienceForUp) {
		this.experienceForUp = experienceForUp;
	}

	public int getCurrentExperience() {
		return currentExperience;
	}

	public void setCurrentExperience(int currentExperience) {
		this.currentExperience = currentExperience;
	}

	public void unRegisterObserver(EventObserver eventObserver) {
		eventObserverList.remove(eventObserver);

	}

	public abstract GameObject copy();

	protected void finishCopy(GameObject copyToWorkWith) {
		copyToWorkWith.objectID = this.objectID;
		copyToWorkWith.objWidth = this.objWidth;
		copyToWorkWith.objHeight = this.objHeight;
		copyToWorkWith.health = this.health;
		copyToWorkWith.level = this.level;
		copyToWorkWith.experienceForUp = this.experienceForUp;
		copyToWorkWith.currentExperience = this.currentExperience;
		copyToWorkWith.obsolete = this.obsolete;
		if (this.behaviour != null) {
			copyToWorkWith.behaviour = this.behaviour.copy();
			copyToWorkWith.behaviour.setOwnerObject(copyToWorkWith);
		}
		if (this.mover != null) {
			copyToWorkWith.mover = this.mover.copy();
			copyToWorkWith.mover.setOwnerObject(copyToWorkWith);
		}
		if (this.weapon != null) {
			copyToWorkWith.weapon = this.weapon.copy();
			copyToWorkWith.weapon.setOwnerObject(copyToWorkWith);
		}
		// All below is no required on client side.
		// LATER use "ghost" copy on client side?
		// no need to copy renderer copy.renderer = this.renderer.copy();
		// no need to copy EventObservers
		// no need to copy ignored objects
		// for (int i = 0; i < this.ignoredObjects.size(); i++) {
		// copy.ignoredObjects.add(this.ignoredObjects.get(i).copy());
		// }
		for (int i = 0; i < this.onHitEffects.size(); i++) {
			copyToWorkWith.onHitEffects.add(this.onHitEffects.get(i).copy());
			;
		}
		// for (int i = 0; i < this.points.size(); i++) {
		// copyToWorkWith.points.add(new Point(this.points.get(i)));
		// }

	}

}
