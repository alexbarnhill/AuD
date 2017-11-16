public class RucksackPacken {
	
	private static int worth = 0;
	/**
	 * @param groessen
	 *            Die Groessen der einzupackenden Elemente.
	 * @param werte
	 *            Die Werte der einzupackenden Elemente.
	 * @param sackGroesse
	 *            Die Groesse des leeren Rucksacks.
	 * @return Der Gesamtwert des optimal gepackten Rucksacks.
	 */
	public static int packeSack(RucksackPackenProtokoll rpp, int groessen[], int werte[], int sackGroesse) {
		int start = groessen.length - 1;
		worth = 0;
		int packWorth = packeSackHelfer(rpp, groessen, werte, sackGroesse, start);
		return packWorth;
	}

	/**
	 * @param groessen
	 *            Die Groessen der einzupackenden Elemente.
	 * @param werte
	 *            Die Werte der einzupackenden Elemente.
	 * @param platzFrei
	 *            Der noch verfuegbare (freie) Platz im Rucksack.
	 * @param naechsterGegenstand
	 *            Index des als naechstes zu betrachtenden Gegenstand.
	 * @return Der Gesamtwert des optimal gepackten Rucksacks.
	 */
	public static int packeSackHelfer(RucksackPackenProtokoll rpp, int groessen[], int werte[], int platzFrei, int naechsterGegenstand) {
		rpp.packeSackHelferAufgerufen(rpp, groessen, werte, platzFrei, naechsterGegenstand); // DO NOT REMOVE OR CHANGE THIS LINE!
		if(platzFrei >= 0 && naechsterGegenstand >= 0) {
			if((groessen[naechsterGegenstand] <= platzFrei) && (worth + werte[naechsterGegenstand] > worth)) {
				worth += werte[naechsterGegenstand];
				platzFrei -= groessen[naechsterGegenstand];
			}
			
			return packeSackHelfer(rpp, groessen, werte, platzFrei, naechsterGegenstand - 1);
		}
		
		return worth;
		
	}
	
}