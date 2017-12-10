/**
 * Merkt sich beliebig viele Kombinationen aus eindeutigem LightsOut-Zustand und Schaltfolge!<br>
 * Beispiel: In der Kombination (z, s) koennte s diejenige Schaltfolge sein, die zum zugehoerigen Zustand z gefuehrt hat.
 *
 * @author John Doe
 * @version 1.0, 12/01/17
 */
public class MerkerFuerLightsOutLoesungsVersuche {
	private final java.util.HashMap<Long, ZahlenFolgenMerker> merker = new java.util.HashMap<>();

	/**
	 * Merkt sich die Kombination aus eindeutigem LightsOut-Zustand und Schaltfolge!<br>
	 * ACHTUNG: Falls der Zustand schon mal vorgekommen wird, dann wird die neue Schaltfolge stattdessen gemerkt!
	 * 
	 * @param zustand
	 *            - ein Zustand des LightsOut-Spiels
	 * @param schaltfolge
	 *            - eine Schaltfolge im LightsOut-Spiel
	 */
	public void merkeDir(long zustand, ZahlenFolgenMerker schaltfolge) {
		merker.put(zustand, schaltfolge);
	}

	/**
	 * Gibt die zum Zustand {@code zustand} gemerkte Schaltfolge zurueck, sofern eine bekannt ist - andernfalls {@code null}.
	 * 
	 * @param zustand
	 *            - ein Zustand des LightsOut-Spiels
	 * @return die zum Zustand gemerkte Schaltfolge - oder {@code null}, falls der {@code zustand} bislang unbekannt ist
	 */
	public ZahlenFolgenMerker verrateMirDieSchaltfolgeZum(long zustand) {
		return merker.get(zustand);
	}

	/**
	 * Gibt eine Liste aller gemerkten Zustaende zurueck.
	 * 
	 * @return die Liste aller germerkten Zustaende
	 */
	public long[] gibMirAlleZustaende() {
		long[] zustaende = new long[merker.keySet().size()];
		int index = 0;
		for (Long l : merker.keySet()) {
			zustaende[index++] = l;
		}
		return zustaende;
	}

	/**
	 * Vergisst ALLE bislang gemerkten Kombinationen!
	 */
	public void vergissAlles() {
		merker.clear();
	}
}