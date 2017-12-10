/**
 * Merkt sich beliebig viele Zahlen wie auf einem Notizblock - aber in geordneter Reihenfolge, daher etwas langsamer!
 *
 * @author John Doe
 * @version 1.0, 12/01/17
 */
public final class ZahlenFolgenMerker {
	private java.util.LinkedList<Integer> notizblock = new java.util.LinkedList<>();

	/**
	 * 
	 * @param zahl
	 *            - die Zahl, die am Ende der bisherigen Zahlen auf dem Notizblock vermerkt werden soll
	 */
	public void ergaenze(int zahl) {
		notizblock.add(zahl);
	}

	/**
	 * 
	 * @return eine waschechte Kopie des Notizblocks
	 */
	@SuppressWarnings("unchecked")
	public ZahlenFolgenMerker machMirEineKopieDavon() {
		ZahlenFolgenMerker kopie = new ZahlenFolgenMerker();
		kopie.notizblock = (java.util.LinkedList<Integer>) notizblock.clone();
		return kopie;
	}

	/**
	 * 
	 * @return alle Zahlen auf dem Notizblock in der Reihenfolge, in der sie hinzugefuegt wurden
	 */
	public Integer[] gibtMirAlle() {
		return notizblock.toArray(new Integer[0]);
	}
}