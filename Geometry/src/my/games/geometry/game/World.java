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
	private List<GameObject> shootersList;

	public World() {
		gameObjectsMap = new HashMap<Integer, GameObject>();
		drawableObjectList = new ArrayList<GameObject>();
		shootersList = new ArrayList<GameObject>();
	}

	public GameObject createGameObject(String objType, int x, int y) {
		int id = gameObjectsMap.size(); // size()used to get unique ID
		GameObject obj = null;
		switch (objType) {
		case "player":
			obj = new Player(x, y);
			obj.setObjectID(id);
			gameObjectsMap.put(id, obj);
			shootersList.add(obj);
			break;
		case "projectile":
			obj = new Projectile(x, y);
			obj.setObjectID(id);
			gameObjectsMap.put(id, obj);
			break;
		case "static":
			obj = new StaticObject(x, y);
			obj.setObjectID(id);
			gameObjectsMap.put(id, obj);
			break;
		}
		if (obj != null)
			drawableObjectList.add(obj);
		return null;
	}

	public void addProjectile(Projectile obj) {
		int id = gameObjectsMap.size(); // size()used to get unique ID
		gameObjectsMap.put(id, obj);
		drawableObjectList.add(obj);
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
	}
}
