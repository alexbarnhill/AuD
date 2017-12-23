import java.util.Comparator;
import java.util.NoSuchElementException;

/** Inhaltliches: **/
// Prioritaetswarteschlangen in Anlehnung an Aufgabe 7 (insbesondere c und d) der AuD-Klausur vom 30.03.2015
// https://www2.cs.fau.de/aud/organisation/oldexams/secure/15-03-30_klausur.pdf
// Das Attribut kopf verweist auf den Knoten mit dem gemaess "Comparator<WertTyp> vergleicher" wichtigsten/"hoechsten"/"groessten" Wert,
// bzw. auf null, wenn die Schlange leer ist.
// Der Nachfolger von kopf verweist entsprechend auf den zweit-wichtigsten usw., bis der Ring wieder bei kopf schliesst.
// Der Vorgaenger von kopf verweist analog in Gegenrichtung auf den unwichtigsten, danach auf den zweit-unwichtigsten Wert usw.,
// bis auch hier der Ring wieder bei kopf schliesst.

/** Organisatorisches: **/
// Ihre Unterklasse darf KEINE weiteren Attribute, Klassen oder Annotationen deklarieren (sie soll nur die von hier geerbten nutzen)!
// Ihre Unterklasse darf KEINE anderen Klassen oder Methoden aus der Java-API verwenden, ausser die hier oben importierten!
// Sie duerfen und sollten eine oder mehrere private Hilfsmethode(n) deklarieren...

/** Plagiatorisches: **/
// Um Plagiate zu verhindern, wurden alle Klassen und Methoden umbenannt,
// die Hauptklasse zerlegt, Comparatoren statt Comparables verwendet und einiges mehr unternommen...
// Schreibt den Code allein oder hoechstens mit dem Gruppenpartner von Grund auf selbst!
// PS: die Seite https://fsi.cs.fau.de/dw/pruefungen/bachelor/aud/loesungws14#aufgabe_7_-_doppelverkettung_29
// ist dem AuD-Team durchaus bekannt... aber zum Zeitpunkt der Erstellung dieser Aufgabe war die vermeintliche Loesung
// fehlerhaft (und ist es ziemlich sicher noch immer) - ausserdem passt sie hier hinten und vorne nicht ;)...

public abstract class AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange<WertTyp> {
	protected DoppeltVerketteterKnoten<WertTyp> kopf; // Knoten mit hoechster Prioritaet (null falls Schlange leer)
	protected final Comparator<WertTyp> vergleicher; // stellt die Prioritaetsreihenfolge dar

	protected AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange(Comparator<WertTyp> vergleicher) {
		this.vergleicher = vergleicher;
	}

	// Fuegt den Wert w gemaess "Sortierung" nach "Comparator<WertTyp> vergleicher" in den Ring ein.
	// Bei gleicher Prioritaet wird das neue Element "hinter" dem vorhandenen eingefuegt.
	public abstract void einfuegen(WertTyp wert);

	// Entfernt den gemaess "Comparator<WertTyp> vergleicher" wichtigsten Wert aus dem Ring und gibt ihn zurueck.
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation muss in O(1) erfolgen!
	public abstract WertTyp wichtigstenEntfernen() throws NoSuchElementException;

	// Entfernt den gemaess "Comparator<WertTyp> vergleicher" *UN*wichtigsten Wert aus dem Ring und gibt ihn zurueck
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation muss in O(1) erfolgen!
	public abstract WertTyp unwichtigstenEntfernen() throws NoSuchElementException;

	// Entfernt den gemaess "Comparator<WertTyp> andererVergleicher" wichtigsten Wert aus dem Ring und gibt ihn zurueck.
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation darf in O(Schlangenlaenge) erfolgen!
	public abstract WertTyp wichtigstenEntfernen(Comparator<WertTyp> andererVergleicher) throws NoSuchElementException;

	// Entfernt den gemaess "Comparator<WertTyp> andererVergleicher" *UN*wichtigsten Wert aus dem Ring und gibt ihn zurueck.
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation darf in O(Schlangenlaenge) erfolgen!
	public abstract WertTyp unwichtigstenEntfernen(Comparator<WertTyp> andererVergleicher) throws NoSuchElementException;

	@Override
	public final String toString() {
		String zeichenkette = "#>";
		if (kopf != null) {
			DoppeltVerketteterKnoten<WertTyp> laufzeiger = kopf;
			do {
				zeichenkette += " " + laufzeiger.holeWert() + " >";
				laufzeiger = laufzeiger.holeNachfolger();
			} while (laufzeiger != kopf);
		}
		return zeichenkette + "|";
	}
}