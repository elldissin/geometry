package nubiki.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import nubiki.behaviour.Behaviour;
import nubiki.behaviour.Effect;
import nubiki.behaviour.GeneralBehaviour;
import nubiki.game.movers.*;
import nubiki.game.renderers.*;
import nubiki.game.weapons.*;

public abstract class GameObject implements Updatable {
	private static final long serialVersionUID = 1L;
	protected int objectID;
	protected Point currentPos;
	protected Point prevPos;
	protected int objWidth, objHeight;
	protected int health;
	protected int level;
	protected double angle;
	protected boolean obsolete;
	protected int distTravelled;
	protected int liveDistance;
	protected Behaviour behaviour;
	protected Mover mover;
	protected Weapon weapon;
	protected Renderer renderer;
	protected ArrayList<Point> points;
	protected ArrayList<GameObject> ignoredObjects;
	protected ArrayList<Effect> onHitEffects;

	public GameObject(int x, int y) {
		super();
		objWidth = 20;
		objHeight = 20;
//		angle = 0;
//		distTravelled = 0;
		liveDistance = 400;
		obsolete = false;
		setPos(new Point(x, y));
		setPrevPos((Point) (getPos().clone()));
		points = new ArrayList<Point>();
		ignoredObjects = new ArrayList<GameObject>();
		onHitEffects = new ArrayList<Effect>();
		behaviour = null;
	}

	public int getHealth() {
		return health;
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

	public ArrayList<Effect> getOnHitEffects() {
		return onHitEffects;
	}

	public void addOnHitEffect(Effect e) {
		onHitEffects.add(e);
	}

	public Behaviour getBehaviour() {
		if (behaviour != null)
			return behaviour;
		else
			return new GeneralBehaviour();
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
		if (renderer != null)
			renderer.draw(g, this);
	}

	public void drawBoundingRect(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(boundingRect().x, boundingRect().y, boundingRect().width, boundingRect().height);
		g.setColor(Color.black);
	}

	public abstract ArrayList<Point> body();

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

	public int getLiveDistance() {
		return liveDistance;
	}

	public void setLiveDistance(int liveDistance) {
		this.liveDistance = liveDistance;
	}

	@Override
	public void update() {
//		move();
//		turn();
	}
}
