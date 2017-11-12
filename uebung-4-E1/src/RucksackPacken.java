public class RucksackPacken {
	
	private static int worth = 0;
	
	private static boolean worthIt(double size, double worth) {
		boolean worthIt = false;
		if((worth / size) >= 1) {
			System.out.println("Item was worth it with a ratio of " + worth/size);
			worthIt = true;
		} else {
			System.out.println("Item was not worth it with a ratio of " + worth/size);
		}
			
		return worthIt;
	}
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
		int packWorth = packeSackHelfer(rpp, groessen, werte, sackGroesse, start);
		
		return packWorth;
		
//		if(sackGroesse == 0 || marker < 0) {
//			System.out.println( "Worth: " + worth);
//			return worth;
//		} else {
//			System.out.println("Item: " + marker + " Size: " + groessen[marker] + " Worth: " + werte[marker]);
//			sackGroesse = packeSackHelfer(rpp, groessen, werte, sackGroesse, marker);
//			packeSack(rpp, groessen, werte, sackGroesse);
//			
//		}
		
		
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
			System.out.println("Next Item: " + naechsterGegenstand + " Free Space: " + platzFrei);
			if((groessen[naechsterGegenstand] <= platzFrei)) {
				worth += werte[naechsterGegenstand];
				platzFrei -= groessen[naechsterGegenstand];
				System.out.println("Adding item: " + naechsterGegenstand + " Space left: " + platzFrei + " Total Worth: " + worth);

			} else {
				System.out.println("Item " + naechsterGegenstand + " was too big with a size of " + groessen[naechsterGegenstand] + ". Skipping");
			}
			
			return packeSackHelfer(rpp, groessen, werte, platzFrei, naechsterGegenstand - 1);
		}
		
		return worth;
		
	}
	
}