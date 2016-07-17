package nubiki.game;

import java.util.HashMap;
import java.util.Map;

public class GameObjectManager {
	private Map<Integer, GameObject> gameObjectsMap;

	public GameObjectManager() {
		gameObjectsMap = new HashMap<Integer, GameObject>();
	}

	public GameObject createGameObject(String objType, int x, int y) {
		switch (objType) {
		case "player":
			gameObjectsMap.put(1, new Player(x,y));
			return gameObjectsMap.get(1);
		}
		return null;
	}
	
	public GameObject getObjectByID(int id) {
		return gameObjectsMap.get(id);
	}
}
