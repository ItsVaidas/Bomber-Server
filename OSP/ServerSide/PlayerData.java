package OSP.ServerSide;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerData {
	
	private static Map<Integer, Long> users = new HashMap<>();

	public static void addUser(int ID) {
		users.put(ID, System.currentTimeMillis()+1000);
	}

	public static void updateUser(int ID) {
		if (users.containsKey(ID))
			users.put(ID, System.currentTimeMillis()+1000);
	}

	public static Set<Integer> getUsers() {
		return new HashSet<>(users.keySet());
	}
	
	public static Long getUserTimeout(int ID) {
		return users.get(ID);
	}
	
	public static void removeUser(int ID) {
		users.remove(ID);
	}
}
