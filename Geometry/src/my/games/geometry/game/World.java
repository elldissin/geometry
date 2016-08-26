package my.games.geometry.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.games.geometry.behaviour.BumpEffect;
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

	public World() {
		gameObjectsMap = new HashMap<Integer, GameObject>();
		drawableObjectList = new ArrayList<GameObject>();
		updatableObjectList = new ArrayList<GameObject>();
		collidableObjectList = new ArrayList<GameObject>();
		shootersList = new ArrayList<GameObject>();
		effectManager = new EffectManager();
		StaticObject obst = (StaticObject) createGameObject("static", 250, 50, 0.0);
		obst.addOnHitEffect(new BumpEffect(0));
	}

	public GameObject createGameObject(String objType, int x, int y, double angle) {
		int id = gameObjectsMap.size(); // size()used to get unique ID
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
		}
		if (obj != null) {
			gameObjectsMap.put(id, obj);
			drawableObjectList.add(obj);
			collidableObjectList.add(obj);
			updatableObjectList.add(obj);
		}
		return obj;
	}

	public List<GameObject> getUpdatableObjectList() {
		return updatableObjectList;
	}

	public List<GameObject> getCollidableObjectList() {
		return collidableObjectList;
	}

	/**
	 * Need this special method, do not add through createGameObject()
	 * *otherwise the projectile created will not be one generated in shoot()
	 * method
	 */
	public void addProjectile(Projectile obj) {
		int id = gameObjectsMap.size(); // size()used to get unique ID
		gameObjectsMap.put(id, obj);
		drawableObjectList.add(obj);
		collidableObjectList.add(obj);
		updatableObjectList.add(obj);
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

	public void removeFromWorld(GameObject obj) {
		updatableObjectList.remove(obj);
		drawableObjectList.remove(obj);
		collidableObjectList.remove(obj);
	}

	private void checkForCollisions() {
		for (int i = 0; i < collidableObjectList.size(); i++)
			for (int j = 0; j < collidableObjectList.size(); j++) {
				if (collidableObjectList.get(i).isColliding(collidableObjectList.get(j)) && i != j) {
					System.out.println("Collision happened");
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
}
