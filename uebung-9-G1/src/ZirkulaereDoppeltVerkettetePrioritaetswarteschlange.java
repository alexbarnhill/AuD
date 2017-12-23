import java.util.Comparator;
import java.util.NoSuchElementException;

public class ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<WertTyp>
		extends AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange<WertTyp> {

	protected ZirkulaereDoppeltVerkettetePrioritaetswarteschlange(Comparator<WertTyp> vergleicher) {
		super(vergleicher);
	}

	@Override
	public void einfuegen(WertTyp wert) {
		if (wert != null) {
			DoppeltVerketteterKnoten<WertTyp> el = new DoppeltVerketteterKnoten<WertTyp>(wert);
			if (kopf == null) {
				kopf = el;
			} else {
				if (vergleicher.compare(wert, kopf.holeWert()) == 1) {
					DoppeltVerketteterKnoten<WertTyp> last = kopf.holeVorgaenger();
					last.ersetzeNachfolger(el);
					kopf.ersetzeVorgaenger(el);
					el.ersetzeNachfolger(kopf);
					el.ersetzeVorgaenger(last);
					kopf = el;
				} else if (vergleicher.compare(wert, kopf.holeWert()) == 0) {
					DoppeltVerketteterKnoten<WertTyp> oldPred = kopf.holeNachfolger();
					kopf.ersetzeNachfolger(el);
					el.ersetzeVorgaenger(kopf);
					el.ersetzeNachfolger(oldPred);
					oldPred.ersetzeVorgaenger(el);
				} else {
					DoppeltVerketteterKnoten<WertTyp> start = kopf;
					while (start.holeNachfolger() != null && start != kopf) {

						if (vergleicher.compare(el.holeWert(), start.holeWert()) == -1
								&& (vergleicher.compare(el.holeWert(), start.holeNachfolger().holeWert()) == 1)) {
							el.ersetzeNachfolger(start.holeNachfolger());
							el.ersetzeVorgaenger(start);
							start.ersetzeNachfolger(el);
							start.holeNachfolger().ersetzeVorgaenger(el);
							break;
						} else if (vergleicher.compare(el.holeWert(), start.holeWert()) == -1
								&& (vergleicher.compare(el.holeWert(), start.holeNachfolger().holeWert()) == 0)) {
							start.holeNachfolger().holeNachfolger().ersetzeVorgaenger(el);
						}
						start = start.holeNachfolger();
					}
				}
			}
		}

	}

	@Override
	public WertTyp wichtigstenEntfernen() throws NoSuchElementException {
		if (kopf == null) {
			throw new NoSuchElementException();
		}

		WertTyp result = kopf.holeWert();
		DoppeltVerketteterKnoten<WertTyp> last = kopf.holeVorgaenger();
		DoppeltVerketteterKnoten<WertTyp> second = kopf.holeNachfolger();

		last.ersetzeNachfolger(second);
		second.ersetzeVorgaenger(last);

		kopf = second;
		return result;
	}

	@Override
	public WertTyp unwichtigstenEntfernen() throws NoSuchElementException {
		if (kopf == null || kopf.holeVorgaenger() == null) {
			throw new NoSuchElementException();
		}
		System.out.println(toString());

		WertTyp result = kopf.holeVorgaenger().holeWert();
		DoppeltVerketteterKnoten<WertTyp> secondLast = kopf.holeVorgaenger();

		secondLast.ersetzeNachfolger(kopf);
		kopf.ersetzeVorgaenger(secondLast);
		return result;

	}

	@Override
	public WertTyp wichtigstenEntfernen(Comparator andererVergleicher) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WertTyp unwichtigstenEntfernen(Comparator andererVergleicher) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

}
