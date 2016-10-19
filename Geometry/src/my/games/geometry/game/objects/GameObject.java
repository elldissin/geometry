package my.games.geometry.game.objects;

import java.awt.Graphics;
import java.awt.Point;
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
import my.games.geometry.game.movers.Mover;
import my.games.geometry.game.renderers.Renderer;
import my.games.geometry.game.weapons.Weapon;

public abstract class GameObject implements Updatable, Serializable {
	private static final long serialVersionUID = 1L;
	protected int objectID;
	protected ObjectPosition currentPos;
	protected ObjectPosition prevPos;
	protected int objWidth, objHeight;
	protected int health;
	protected int level;
	protected int experienceForUp;
	protected int currentExperience;
	protected double angle;
	protected boolean obsolete;
	protected Behaviour behaviour;
	protected Mover mover;
	protected Weapon weapon;
	protected Renderer renderer;
	protected List<EventObserver> eventObserverList;

	protected List<Point> points;
	protected List<GameObject> ignoredObjects;
	protected List<Effect> onHitEffects;

	public GameObject(ObjectPosition position, double angle) {
		super();
		eventObserverList = new CopyOnWriteArrayList<EventObserver>();
		objWidth = 20;
		objHeight = 20;
		this.angle = angle;
		obsolete = false;
		setPos(position);
		setPrevPos(getPos().copy());
		points = new CopyOnWriteArrayList<Point>(); // LATER fix CopyOnWrite
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

	public ObjectPosition getPrevPos() {
		return prevPos;
	}

	public void setPrevPos(ObjectPosition prevPos) {
		this.prevPos = prevPos;
	}

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	public void setPos(ObjectPosition position) {
		currentPos = position;
		if (points != null) {
			points.clear();
			body();
		}
	}

	public ObjectPosition getPos() {
		return currentPos;
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

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		if (points != null) {
			points.clear();
			body();
		}
	}

	public void setObsolete(boolean value) {
		obsolete = value;
	}

	public void draw(Graphics g) {
		if (renderer != null)
			renderer.draw(g);
	}

	public abstract List<Point> body();

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
		rec = new Rectangle(currentPos.getIntX() - getObjWidth(), currentPos.getIntY() - getObjHeight(),
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
		if (weapon != null)
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

	@Override
	public void update(double delta) { // LATER use array of ObjectSystem ?
		if (mover != null)
			mover.function(delta);
		if (weapon != null)
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
		setObsolete(true);
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
		copyToWorkWith.prevPos = this.prevPos.copy();
		// LATER need to copy objects below or not?
		// if (this.behaviour != null)
		// copyToWorkWith.behaviour = this.behaviour.copy();
		// if (this.mover != null)
		// copyToWorkWith.mover = this.mover.copy();
		// if (this.weapon != null)
		// copyToWorkWith.weapon = this.weapon.copy();
		// no need to copy renderer copy.renderer = this.renderer.copy();
		// no need to copy EventObservers
		// for (int i = 0; i < this.ignoredObjects.size(); i++) {
		// copy.ignoredObjects.add(this.ignoredObjects.get(i).copy());
		// }
		for (int i = 0; i < this.onHitEffects.size(); i++) {
			copyToWorkWith.onHitEffects.add(this.onHitEffects.get(i).copy());
			;
		}
		for (int i = 0; i < this.points.size(); i++) {
			copyToWorkWith.points.add(new Point(this.points.get(i)));
		}

	}

}
