package my.games.geometry.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.Effect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.EventObserver;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.movers.Mover;
import my.games.geometry.game.renderers.Renderer;
import my.games.geometry.game.weapons.Weapon;

public abstract class GameObject implements Updatable, Serializable {
	private static final long serialVersionUID = 1L;
	protected int objectID;
	protected Point currentPos;
	protected Point prevPos;
	protected int objWidth, objHeight;
	protected int health;
	protected int level;
	protected int experienceForUp;
	protected int currentExperience;
	protected double angle;
	protected boolean obsolete;
	protected int distTravelled;
	protected int liveDistance;
	protected Behaviour behaviour;
	protected Mover mover;
	protected Weapon weapon;
	protected Renderer renderer;
	protected List<EventObserver> eventObserverList;

	protected List<Point> points;
	protected List<GameObject> ignoredObjects;
	protected List<Effect> onHitEffects;

	public GameObject(Point position, double angle) {
		super();
		eventObserverList = new CopyOnWriteArrayList<EventObserver>();
		objWidth = 20;
		objHeight = 20;
		this.angle = angle;
		liveDistance = 400;
		obsolete = false;
		setPos(position);
		setPrevPos((Point) (getPos().clone()));
		points = new CopyOnWriteArrayList<Point>(); // LATER fix CopyOnWrite
													// later
		ignoredObjects = new CopyOnWriteArrayList<GameObject>();
		onHitEffects = new CopyOnWriteArrayList<Effect>();
		behaviour = null;
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

	public Point getPrevPos() {
		return prevPos;
	}

	public void setPrevPos(Point prevPos) {
		this.prevPos = prevPos;
	}

	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

	public void setPos(Point p) {
		currentPos = p;
		if (points != null) {
			points.clear();
			body();
		}
	}

	public Point getPos() {
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
			return new PlayerBehaviour(); // LATER create NoBehaviour?
	}

	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setObsolete(boolean value) {
		obsolete = value;
	}

	public void draw(Graphics g) {
		drawBoundingRect(g);
		Point p = (Point) (getPos().clone());
		p.y -= getObjHeight() / 2 + 10;
		p.x -= getObjWidth() / 2 + 20;
		g.drawString("ID: " + getObjectID(), p.x, p.y);
		if (renderer != null)
			renderer.draw(g, this);
	}

	public void drawBoundingRect(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(boundingRect().x, boundingRect().y, boundingRect().width, boundingRect().height);
		g.setColor(Color.black);
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
		rec = new Rectangle((int) (currentPos.x - getObjWidth()), (int) (currentPos.y - getObjHeight()),
				(int) (getObjWidth()) * 2, (int) (getObjHeight()) * 2);
		return rec;
	}

	public void move() {
		if (mover != null)
			mover.move(this);
	}

	public void turn(int dir) {
		if (mover != null)
			mover.turn(this, dir);
	}

	public void shoot() {
		if (weapon != null)
			weapon.shoot(this);
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

	public int getLiveDistance() {
		return liveDistance;
	}

	public void setLiveDistance(int liveDistance) {
		this.liveDistance = liveDistance;
	}

	@Override
	public void update() {
		// move();
		// turn();
	}

	public void notifyObserversAbout(GameEvent event) {
		for (int i = 0; i < eventObserverList.size(); i++)
			eventObserverList.get(i).notifyAboutEvent(this, event);

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
}
