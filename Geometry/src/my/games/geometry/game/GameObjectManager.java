package my.games.geometry.game;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class GameObjectManager {
	private static Map<Integer, GameObject> gameObjectsMap = new HashMap<Integer, GameObject>(); ;

	public static GameObject createGameObject(String objType, int x, int y) {
		int id=gameObjectsMap.size(); // size()used to get unique ID
		GameObject obj;
		switch (objType) {
		case "player":
			obj = new Player(x,y);
			obj.setObjectID(id);
			gameObjectsMap.put(id, obj);
			return obj;
		case "projectile":
			obj = new Projectile(x,y);
			obj.setObjectID(id);
			gameObjectsMap.put(id, obj);
			return obj;
		}
		return null;
	}
	
	public static GameObject getObjectByID(int id) {
		return gameObjectsMap.get(id);
	}
}
