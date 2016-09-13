package my.games.geometry.game;

import java.util.ArrayList;
import java.util.List;

import my.games.geometry.events.CreateEvent;
import my.games.geometry.events.EventObserver;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;
import my.games.geometry.game.objects.Projectile;
import my.games.geometry.game.objects.StaticObject;

public class World {
	private EffectManager effectManager;
	private List<GameObject> gameObjectList;
	private List<GameObject> drawableObjectList;
	private List<GameObject> updatableObjectList;
	private List<GameObject> collidableObjectList;
	private List<GameObject> shootersList;

	private List<EventObserver> eventObserverList;
	private WorldChangeObserver logDisplayNotifier;
	// private Controller controller;

	public World() {
		gameObjectList = new ArrayList<GameObject>();
		drawableObjectList = new ArrayList<GameObject>();
		updatableObjectList = new ArrayList<GameObject>();
		collidableObjectList = new ArrayList<GameObject>();
		shootersList = new ArrayList<GameObject>();
		effectManager = new EffectManager();
		eventObserverList = new ArrayList<EventObserver>();
		logDisplayNotifier = new LogDisplayNotifier();
		// this.controller = controller;
	}

	public List<GameObject> getGameObjectsList() {
		return gameObjectList;
	}

	public GameObject createGameObject(String objType, int x, int y, double angle) {
		int id = gameObjectList.size(); // LATER size()used to get unique ID
		GameObject obj = null;
		switch (objType) {
		case "player":
			obj = new Player(x, y, angle);
			shootersList.add(obj);
			break;
		case "projectile":
			obj = new Projectile(x, y, angle);
			break;
		case "static":
			obj = new StaticObject(x, y, angle);
			break;
		// LATER add bfgprojectile type?
		}
		if (obj != null) {
			obj.setObjectID(id);
			System.out.println("add obj on server: " + obj.getObjectID());
			gameObjectList.add(obj);
			drawableObjectList.add(obj);
			collidableObjectList.add(obj);
			updatableObjectList.add(obj);
			registerObserversForObject(obj);

			GameEvent event = new CreateEvent(obj.getObjectID());
			event.setCarriedObject(obj);
			obj.notifyObserversAbout(event);

			logDisplayNotifier.worldHasChanged();
		}
		return obj;
	}

	public GameObject createGameObject(GameObject newObject) {
		if (newObject != null) {
			System.out.println("add obj on client: " + newObject.getObjectID());
			gameObjectList.add(newObject);
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
	 * Need this special method, do not add through createGameObject() otherwise the projectile
	 * created will not be one generated in shoot() method
	 */
	public void addProjectile(GameObject obj) { // FIXME add through
												// createGameObject
		createGameObject("projectile", obj.getPos().x, obj.getPos().y, obj.getAngle());
	}

	public GameObject getObjectByID(int id) {
		return gameObjectList.get(id);
	}

	public List<GameObject> getDrawableObjectList() {
		return drawableObjectList;
	}

	public void update() {
		// Handle shooters and their projectiles, add their projectiles to world
		for (int i = 0; i < shootersList.size(); i++) { // FIXME where projectile gets ID?
			for (int j = 0; j < shootersList.get(i).getWeapon().getProjectileList().size(); j++) {
				GameObject projectile = shootersList.get(i).getWeapon().getProjectileList().get(j);
				addProjectile(projectile);
				shootersList.get(i).getWeapon().getProjectileList().remove(j);
			}
		}

		for (int i = 0; i < updatableObjectList.size(); i++) {
			updatableObjectList.get(i).update(); // first update, then remove
			if (updatableObjectList.get(i).isDestroyed())
				destroyGameObject(updatableObjectList.get(i));
		}

		checkForCollisions();
	}

	// public void removeFromWorld(GameObject obj) { // FIXME destroyGameObject is doing same?
	// updatableObjectList.remove(obj);
	// drawableObjectList.remove(obj);
	// collidableObjectList.remove(obj);
	// shootersList.remove(obj);
	// // FIXME controller.stopControlOf(obj);
	// }

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
			gameObjectList.remove(obj);
			drawableObjectList.remove(obj);
			collidableObjectList.remove(obj);
			updatableObjectList.remove(obj);
			unRegisterObserversForObject(obj);

			logDisplayNotifier.worldHasChanged();
		}
		// FIXME controller.stopControlOf(obj);
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
