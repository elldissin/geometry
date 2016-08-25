package my.games.geometry.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;
import my.games.geometry.game.objects.Projectile;
import my.games.geometry.game.objects.StaticObject;

public class World {
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
	}

	public GameObject createGameObject(String objType, int x, int y) {
		int id = gameObjectsMap.size(); // size()used to get unique ID
		GameObject obj = null;
		switch (objType) {
		case "player":
			obj = new Player(x, y);
			obj.setObjectID(id);
			shootersList.add(obj);
			break;
		case "projectile":
			obj = new Projectile(x, y);
			obj.setObjectID(id);
			break;
		case "static":
			obj = new StaticObject(x, y);
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

	public void addProjectile(Projectile obj) {
		createGameObject("projectile", obj.getPos().x, obj.getPos().y);
	}

	public GameObject getObjectByID(int id) {
		return gameObjectsMap.get(id);
	}

	public List<GameObject> getDrawableObjectList() {
		return drawableObjectList;
	}

	public void update() {
		// Handle shooters and their projectiles
		for (GameObject obj : shootersList) {
			for (Projectile prj : obj.getWeapon().getProjectileList()) {
				addProjectile(prj);
			}
		}

		for (GameObject obj : updatableObjectList)
			obj.update();
	}

	public void removeFromWorld(GameObject obj) {
		updatableObjectList.remove(obj);
		drawableObjectList.remove(obj);
		collidableObjectList.remove(obj);
	}
}
