package my.games.geometry.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.events.EventObserver;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;
import my.games.geometry.game.objects.Projectile;
import my.games.geometry.game.objects.StaticObject;

public class World {
	private EffectManager effectManager;
	private Map<Integer, GameObject> gameObjectsMap;
	private List<GameObject> drawableObjectList;
	private List<GameObject> updatableObjectList;
	private List<GameObject> collidableObjectList;
	private List<GameObject> shootersList;

	private List<EventObserver> eventObserverList;
	private WorldChangeObserver logDisplayNotifier;
	// private Controller controller;

	public World() {
		gameObjectsMap = new HashMap<Integer, GameObject>();
		drawableObjectList = new ArrayList<GameObject>();
		updatableObjectList = new ArrayList<GameObject>();
		collidableObjectList = new ArrayList<GameObject>();
		shootersList = new ArrayList<GameObject>();
		effectManager = new EffectManager();
		eventObserverList = new ArrayList<EventObserver>();
		logDisplayNotifier = new LogDisplayNotifier();
		// LATER why static object created here?
		StaticObject obst = (StaticObject) createGameObject("static", 250, 50, 0.0);
		obst.addOnHitEffect(new BumpEffect(0));
		// this.controller = controller;
	}

	public Map<Integer, GameObject> getGameObjectsMap() {
		return gameObjectsMap;
	}

	public GameObject createGameObject(String objType, int x, int y, double angle) {
		int id = gameObjectsMap.size(); // LATER size()used to get unique ID
		GameObject obj = null;
		switch (objType) {
		case "player":
			obj = new Player(x, y, angle);
			obj.setObjectID(id);
			shootersList.add(obj);
			break;
		case "projectile":
			obj = new Projectile(x, y, angle);
			obj.setObjectID(id);
			break;
		case "static":
			obj = new StaticObject(x, y, angle);
			obj.setObjectID(id);
			break;
		// LATER add bfgprojectile type?
		}
		if (obj != null) {
			gameObjectsMap.put(id, obj);
			drawableObjectList.add(obj);
			collidableObjectList.add(obj);
			updatableObjectList.add(obj);
			registerObserversForObject(obj);

			logDisplayNotifier.worldHasChanged();
		}
		return obj;
	}

	public GameObject createGameObject(GameObject newObject) {
		if (newObject != null) {
			int id = newObject.getObjectID();
			gameObjectsMap.put(id, newObject);
			drawableObjectList.add(newObject);
			collidableObjectList.add(newObject);
			updatableObjectList.add(newObject);
			registerObserversForObject(newObject);

			logDisplayNotifier.worldHasChanged();
		}
		return newObject;
	}

	public List<GameObject> getUpdatableObjectList() {
		return updatableObjectList;
	}

	public List<GameObject> getCollidableObjectList() {
		return collidableObjectList;
	}

	/**
	 * Need this special method, do not add through createGameObject() otherwise
	 * the projectile created will not be one generated in shoot() method
	 */
	public void addProjectile(Projectile obj) { // FIXME add through
												// createGameObject
		createGameObject("projectile", obj.getPos().x, obj.getPos().y, obj.getAngle());
		// int id = gameObjectsMap.size(); // LATER size()used to get unique ID
		// gameObjectsMap.put(id, obj);
		// drawableObjectList.add(obj);
		// collidableObjectList.add(obj);
		// updatableObjectList.add(obj);
	}

	public GameObject getObjectByID(int id) {
		return gameObjectsMap.get(id);
	}

	public List<GameObject> getDrawableObjectList() {
		return drawableObjectList;
	}

	public void update() {
		// Handle shooters and their projectiles, add their projectiles to world
		for (int i = 0; i < shootersList.size(); i++) {
			for (int j = 0; j < shootersList.get(i).getWeapon().getProjectileList().size(); j++) {
				addProjectile(shootersList.get(i).getWeapon().getProjectileList().get(j));
				shootersList.get(i).getWeapon().getProjectileList().remove(j);
			}
		}

		for (int i = 0; i < updatableObjectList.size(); i++) {
			updatableObjectList.get(i).update(); // first update, then remove
			if (updatableObjectList.get(i).isDestroyed())
				removeFromWorld(updatableObjectList.get(i));
		}

		checkForCollisions();
	}

	public void removeFromWorld(GameObject obj) { // FIXME destroyGameObject is doing same?
		updatableObjectList.remove(obj);
		drawableObjectList.remove(obj);
		collidableObjectList.remove(obj);
		shootersList.remove(obj);
		// FIXME controller.stopControlOf(obj);
	}

	public void checkForCollisions() {
		for (int i = 0; i < collidableObjectList.size(); i++)
			for (int j = 0; j < collidableObjectList.size(); j++) {
				if (collidableObjectList.get(i).isColliding(collidableObjectList.get(j)) && i != j) {
					// System.out.println("Collision happened");
					GameObject actor1 = collidableObjectList.get(i);
					GameObject actor2 = collidableObjectList.get(j);
					for (int k = 0; k < actor2.getOnHitEffects().size(); k++) {
						effectManager.handle(actor1, actor2.getOnHitEffects().get(k));
					}
					for (int k = 0; k < actor1.getOnHitEffects().size(); k++) {
						effectManager.handle(actor2, actor1.getOnHitEffects().get(k));
					}
				}
			}
	}

	// LATER removeEventObserver required?
	public void registerWorldObserver(EventObserver observer) {
		eventObserverList.add(observer);
	}

	public void destroyGameObject(GameObject obj) {
		if (obj != null) {
			gameObjectsMap.remove(obj.getObjectID(), obj);
			drawableObjectList.remove(obj);
			collidableObjectList.remove(obj);
			updatableObjectList.remove(obj);
			unRegisterObserversForObject(obj);

			logDisplayNotifier.worldHasChanged();
		}
	}

	private void registerObserversForObject(GameObject obj) {
		for (int i = 0; i < eventObserverList.size(); i++) {
			obj.registerObserver(eventObserverList.get(i));
		}
	}

	private void unRegisterObserversForObject(GameObject obj) {
		for (int i = 0; i < eventObserverList.size(); i++) {
			obj.unRegisterObserver(eventObserverList.get(i));
			// System.out.println("unRegisterObserversForObject ");
		}
	}

	public void registerLogDisplayNotifyer(WorldChangeObserver logDisplayNotifier) {
		this.logDisplayNotifier = logDisplayNotifier;
	}
}
