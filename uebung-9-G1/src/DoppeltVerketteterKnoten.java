public class DoppeltVerketteterKnoten<WertTyp> {
	private DoppeltVerketteterKnoten<WertTyp> vorgaenger;
	private final WertTyp wert;
	private DoppeltVerketteterKnoten<WertTyp> nachfolger;

	public DoppeltVerketteterKnoten(WertTyp wert) {
		this.vorgaenger = this;
		this.wert = wert;
		this.nachfolger = this;
	}

	public DoppeltVerketteterKnoten<WertTyp> holeVorgaenger() {
		return vorgaenger;
	}

	public DoppeltVerketteterKnoten<WertTyp> ersetzeVorgaenger(DoppeltVerketteterKnoten<WertTyp> vorgaenger) {
		return this.vorgaenger = vorgaenger;
	}

	WertTyp holeWert() {
		return wert;
	}

	DoppeltVerketteterKnoten<WertTyp> holeNachfolger() {
		return nachfolger;
	}

	DoppeltVerketteterKnoten<WertTyp> ersetzeNachfolger(DoppeltVerketteterKnoten<WertTyp> nachfolger) {
		return this.nachfolger = nachfolger;
	}
}