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
			} else if (vergleicher.compare(el.holeWert(), kopf.holeWert()) >= 1 || vergleicher.compare(el.holeWert(), kopf.holeWert()) == 0) {
				DoppeltVerketteterKnoten<WertTyp> end = kopf.holeVorgaenger();
				kopf.ersetzeVorgaenger(el);
				end.ersetzeNachfolger(el);
				el.ersetzeNachfolger(kopf);
				el.ersetzeVorgaenger(end);
				kopf = el;
				

			} else if (vergleicher.compare(el.holeWert(), kopf.holeVorgaenger().holeWert()) <= -1) {
				DoppeltVerketteterKnoten<WertTyp> end = kopf.holeVorgaenger();
				kopf.ersetzeVorgaenger(el);
				end.ersetzeNachfolger(el);
				el.ersetzeNachfolger(kopf);
				el.ersetzeVorgaenger(end);

			}
			
			else {
				DoppeltVerketteterKnoten<WertTyp> start = kopf;
				while(start.holeNachfolger() != null && start.holeNachfolger() != kopf) {
					if(vergleicher.compare(el.holeWert(), start.holeWert()) <= -1 && vergleicher.compare(el.holeWert(), start.holeNachfolger().holeWert()) >= 1) {
						DoppeltVerketteterKnoten<WertTyp> suc = start.holeNachfolger();
						el.ersetzeVorgaenger(start);
						el.ersetzeNachfolger(suc);
						start.ersetzeNachfolger(el);
						suc.ersetzeVorgaenger(el);
						el.ersetzeVorgaenger(start);
	
					} 
					start = start.holeNachfolger();
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
		
		if(kopf.holeNachfolger() != kopf && kopf.holeVorgaenger() != kopf ) {
			DoppeltVerketteterKnoten<WertTyp> last = kopf.holeVorgaenger();
			DoppeltVerketteterKnoten<WertTyp> second = kopf.holeNachfolger();
			last.ersetzeNachfolger(second);
			second.ersetzeVorgaenger(last);

			kopf = second;
		} else {
			kopf = null;
		}
		

		
		
		return result;
	}

	@Override
	public WertTyp unwichtigstenEntfernen() throws NoSuchElementException {
		if (kopf == null) {
			throw new NoSuchElementException();
		}

		WertTyp result = kopf.holeVorgaenger().holeWert();
		
		if(kopf.holeNachfolger() != kopf && kopf.holeVorgaenger() != kopf ) {
			
			DoppeltVerketteterKnoten<WertTyp> last = kopf.holeVorgaenger();
			DoppeltVerketteterKnoten<WertTyp> secondLast = last.holeVorgaenger();
			secondLast.ersetzeNachfolger(kopf);
			kopf.ersetzeVorgaenger(secondLast);
		} else {
			kopf = null;
		}
		
		return result;

	}

	@Override
	public WertTyp wichtigstenEntfernen(Comparator<WertTyp> andererVergleicher) throws NoSuchElementException {
		if (kopf == null) {
			throw new NoSuchElementException();
		}
		
		DoppeltVerketteterKnoten<WertTyp> el = kopf;
		DoppeltVerketteterKnoten<WertTyp> start = kopf;
		while(start.holeNachfolger() != null && start.holeNachfolger() != kopf) {
			if(andererVergleicher.compare(start.holeNachfolger().holeWert(), el.holeWert()) >= 0) {
				el = start.holeNachfolger();
			} else if (andererVergleicher.compare(el.holeWert(), start.holeNachfolger().holeWert()) <= -1){
				el = start.holeNachfolger();
			}
			
			start = start.holeNachfolger();
		}
		
		if(kopf.holeNachfolger() == kopf) {
			kopf = null;
		} else {
			DoppeltVerketteterKnoten<WertTyp> prec = el.holeVorgaenger();
			DoppeltVerketteterKnoten<WertTyp> suc = el.holeNachfolger();
			
			if(el == kopf) {
				kopf = suc;
			}
			
			prec.ersetzeNachfolger(suc);
			suc.ersetzeVorgaenger(prec);
		}
		
		
		
		return el == null ? null : el.holeWert();
	}

	@Override
	public WertTyp unwichtigstenEntfernen(Comparator<WertTyp> andererVergleicher) throws NoSuchElementException {
		if (kopf == null) {
			throw new NoSuchElementException();
		}
		DoppeltVerketteterKnoten<WertTyp> el = kopf;
		DoppeltVerketteterKnoten<WertTyp> start = kopf;
		
		while(start.holeNachfolger() != null && start.holeNachfolger() != kopf) {
			if(andererVergleicher.compare(start.holeNachfolger().holeWert(), el.holeWert()) <= 0) {
				el = start.holeNachfolger();
			}
			
			start = start.holeNachfolger();
		}
		
		if(kopf.holeNachfolger() == kopf) {
			kopf = null;
		} else {
			DoppeltVerketteterKnoten<WertTyp> prec = el.holeVorgaenger();
			DoppeltVerketteterKnoten<WertTyp> suc = el.holeNachfolger();
			if(el == kopf) {
				kopf = suc;
			}
			prec.ersetzeNachfolger(suc);
			suc.ersetzeVorgaenger(prec);
		}
		
		return el == null ? null : el.holeWert();
	}

}
