package nubiki.game;

import java.util.HashMap;
import java.util.Map;

public class GameObjectManager {
	private Map<Integer, GameObject> gameObjectsMap;

	public GameObjectManager() {
		gameObjectsMap = new HashMap<Integer, GameObject>();
	}

	public GameObject createGameObject(String objType, int x, int y) {
		int id=gameObjectsMap.size(); // size()used to get unique ID
		switch (objType) {
		case "player":
			gameObjectsMap.put(id, new Player(x,y));
			return gameObjectsMap.get(id);
		}
		return null;
	}
	
	public GameObject getObjectByID(int id) {
		return gameObjectsMap.get(id);
	}
}
