public class SozialesNetzwerk {
	public static boolean[][] freundschaft;
	
	private static boolean[][] visited;

	public static int anzahlNutzer;
	
	private static String[] users;
	
	private static int getNextSpot() {
		int spot = 0;
		for(int i = 0; i < users.length; i++) {
			if(users[i] == null) {
				spot = i;
				break;
			}
		}
		return spot;
		
	}
	
	private static int nextFriend(int id) {
		int friend = id;
		for(int i = 0; i < anzahlNutzer; i++) {
			if(testeFreundschaft(i, id) && id != i && !alreadyVisited(i, id)) {
				visited[i][id] = true;
				visited[id][i] = true;
				friend = i;
				break;
			}
		}
		return friend;
	}
	
	private static boolean alreadyVisited(int id0, int id1) {
		boolean alreadyVisited = false;
		if(visited[id0][id1] || visited[id1][id0]) {
			alreadyVisited = true;
		}
		return alreadyVisited;
	}
	
	private static void resetVisited() {
		visited = new boolean[anzahlNutzer][anzahlNutzer];
	}
	

	// initialize the network for max. n users
	public static void initialisiere(int n) {
		freundschaft = new boolean[n][n];
		users = new String[n];
		resetVisited();
		
	}

	// adds a user with the given name
	public static int fuegeNutzerHinzu(String name) {
		int location = getNextSpot();
		users[location] = name;
		freundschaft[location][location] = true;
		anzahlNutzer++;
		resetVisited();
		return location;
	}

	// adds a friendship relationship between the users with the given IDs
	// (regardless of the current state)
	public static void fuegeFreundschaftHinzu(int id0, int id1) {
		freundschaft[id0][id1] = true;
		freundschaft[id1][id0] = true;
		resetVisited();
	}

	// removes a friendship relationship between the users with the given IDs
	// (regardless of the current state)
	public static void entferneFreundschaft(int id0, int id1) {
		freundschaft[id0][id1] = false;
		freundschaft[id1][id0] = false;
		resetVisited();
	}

	// returns true if the users with the given IDs are friends and false if not
	public static boolean testeFreundschaft(int id0, int id1) {
		return freundschaft[id0][id1];
		
	}

	// returns true if the users with the given IDs are reachable within the given distance e, false otherwise
	public static boolean istErreichbar(SozialesNetzwerkMethodenProtokoll snmp, int id0, int id1, int e) {
		snmp.istErreichbar(id0, id1, e); // DO NOT REMOVE OR CHANGE THIS LINE!
		System.out.println("Starting with: id0 = " + id0 + " id1 = " + id1 + " E: " + e);
		if((testeFreundschaft(id0, id1)) && e > 0) {
			resetVisited();
			System.out.println(id0 + " and " + id1 + " are friends. Exiting now with e = " + e);
			return true;
		} else if( e <= 0){
			resetVisited();
			System.out.println("Too far away");
			return false;
		} else {
			for(int i = 0; i < anzahlNutzer; i++) {
				if(testeFreundschaft(id0, i)) {
					return istErreichbar(snmp, nextFriend(id0), id1, e - 1);
				}
			}
			return false;
		}
	}
}