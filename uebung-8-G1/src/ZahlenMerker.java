/**
 * Merkt sich sehr schnell beliebig viele Zahlen wie auf einem Schmierblatt - daher auch in beliebiger Reihenfolge (also "kreuz und quer")!
 *
 * @author John Doe
 * @version 1.0, 12/01/17
 */
public final class ZahlenMerker {
	private final java.util.HashSet<Long> schmierblatt = new java.util.HashSet<>();

	/**
	 * 
	 * @param zahl
	 *            - die Zahl, die auf dem Schmierblatt vermerkt werden soll
	 * @return {@code true} wenn die Zahl noch nicht auf dem Schmierblatt stand und deshalb erstmals hinzugefuegt wurde
	 */
	public boolean merkeDir(long zahl) {
		return schmierblatt.add(zahl);
	}

	/**
	 * 
	 * @param zahl
	 *            - die Zahl, die auf dem Schmierblatt nachgeschlagen werden soll
	 * @return {@code true} wenn die Zahl schon auf dem Schmierblatt steht
	 */
	public boolean sagMirKennstDuDieSchon(long zahl) {
		return schmierblatt.contains(zahl);
	}
}