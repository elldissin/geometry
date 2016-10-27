package my.games.geometry.game.engine;

import java.util.ArrayList;
import java.util.List;

import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.CreateEvent;
import my.games.geometry.events.DestroyEvent;
import my.games.geometry.events.GameEvent;
import my.games.geometry.events.util.EventObserver;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;
import my.games.geometry.game.objects.StaticObject;
import my.games.geometry.game.weapons.DefaultWeapon;

public class World {
	private EffectManager effectManager;
	private List<GameObject> gameObjectList;
	private List<GameObject> drawableObjectList;
	private List<GameObject> updatableObjectList;
	private List<GameObject> collidableObjectList;

	private List<EventObserver> eventObserverList;
	private WorldChangeObserver logDisplayNotifier;

	public World() {
		gameObjectList = new ArrayList<GameObject>();
		drawableObjectList = new ArrayList<GameObject>();
		updatableObjectList = new ArrayList<GameObject>();
		collidableObjectList = new ArrayList<GameObject>();
		effectManager = new EffectManager();
		eventObserverList = new ArrayList<EventObserver>();
		logDisplayNotifier = new LogDisplayNotifier();
	}

	public void initializeWorld() {
		GameObject staticObject1 = new StaticObject(new ObjectPosition(250, 50), 0.0);
		registerGameObject(staticObject1);
		staticObject1.addOnHitEffect(new BumpEffect(0));
	}

	public List<GameObject> getGameObjectsList() {
		return gameObjectList;
	}

	public void registerGameObject(GameObject newObject) {
		if (newObject != null) {
			gameObjectList.add(newObject);
			drawableObjectList.add(newObject);
			collidableObjectList.add(newObject);
			updatableObjectList.add(newObject);
			registerObserversForObject(newObject);
			logDisplayNotifier.worldHasChanged();

		}
		GameEvent event = new CreateEvent(newObject);
		newObject.notifyObserversAbout(event);
	}

	public void destroyGameObject(GameObject obj) {
		if (obj != null) {
			for (int i = 0; i < gameObjectList.size(); i++) {
				GameObject localRemovedObject = gameObjectList.get(i);
				if (localRemovedObject.getObjectID() == obj.getObjectID()) {
					gameObjectList.remove(localRemovedObject);
					drawableObjectList.remove(localRemovedObject);
					collidableObjectList.remove(localRemovedObject);
					updatableObjectList.remove(localRemovedObject);
					unRegisterObserversForObject(localRemovedObject);
					logDisplayNotifier.worldHasChanged();
				}
			}
		}
		// FIXME controller.stopControlOf(obj);
	}

	public List<GameObject> getUpdatableObjectList() {
		return updatableObjectList;
	}

	public List<GameObject> getCollidableObjectList() {
		return collidableObjectList;
	}

	public GameObject addNewConnectedPlayer(int clientID) {
		GameObject newPlayer = new Player(new ObjectPosition(100, 100), 0.0);
		registerGameObject(newPlayer); // FIXME position?
		newPlayer.setWeapon(new DefaultWeapon(newPlayer));
		newPlayer.setBehaviour(new PlayerBehaviour(newPlayer));
		newPlayer.addOnHitEffect(new BumpEffect(0));
		return newPlayer;
	}

	public GameObject getObjectByID(int id) {
		for (int i = 0; i < gameObjectList.size(); i++) {
			GameObject candidateObject = gameObjectList.get(i);
			if (candidateObject.getObjectID() == id) {
				return candidateObject;
			}
		}
		return null;
	}

	public List<GameObject> getDrawableObjectList() {
		return drawableObjectList;
	}

	public void update(double delta) {
		// Handle shooters and their projectiles, add their projectiles to world
		for (int i = 0; i < updatableObjectList.size(); i++) {
			for (int j = 0; j < updatableObjectList.get(i).getWeapon().getProjectileList().size(); j++) {
				GameObject projectile = updatableObjectList.get(i).getWeapon().getProjectileList().get(j);
				registerGameObject(projectile);
				updatableObjectList.get(i).getWeapon().getProjectileList().remove(j);
			}
		}

		for (int i = 0; i < updatableObjectList.size(); i++) {
			updatableObjectList.get(i).update(delta); // first update, then remove
			if (updatableObjectList.get(i).isDestroyed()) {
				// Notify observers
				GameEvent event = new DestroyEvent(updatableObjectList.get(i));
				updatableObjectList.get(i).notifyObserversAbout(event);
				destroyGameObject(updatableObjectList.get(i));
			}
		}

		checkForCollisions();
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
