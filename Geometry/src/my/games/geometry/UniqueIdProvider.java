package my.games.geometry;

import java.util.HashSet;
import java.util.Set;

public class UniqueIdProvider {

	private static Set<Integer> clientIDList = new HashSet<Integer>();
	private static Set<Integer> objectDList = new HashSet<Integer>();

	public static int getObjectID() {
		return getUniqueNumber(objectDList);
	}

	public static int getClientID() {
		return getUniqueNumber(clientIDList);
	}

	private static int getUniqueNumber(Set<Integer> existingSet) {
		int candidateID = 0;
		while (existingSet.contains(candidateID)) {
			candidateID++;
		}
		return candidateID;
	}
}
