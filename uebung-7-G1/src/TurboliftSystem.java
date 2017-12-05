public class TurboliftSystem extends BennanteEinrichtung{
	public int alarmStufe;
	private Steuerung steuerung;
	private TurboliftSchacht[] turboliftSchaechte;
	public TurboliftSystem(String name, TurboliftSchacht[] turboliftSchaechte, Steuerung steuerung) {
		super();
		this.steuerung = steuerung;
		this.turboliftSchaechte = turboliftSchaechte;
	}
	
}

abstract class BennanteEinrichtung {
	protected String name;
}

class Steuerung {
	protected TurboliftSchacht[] turboliftSchaechte;
	public Steuerung(TurboliftSchacht[] turboliftSchaechte) {
		this.turboliftSchaechte = turboliftSchaechte;
	}
}

class TurboliftSchacht extends BennanteEinrichtung{
	public boolean vertikal;
	private Kabine kabine = new Kabine();
	protected Deck[] decks;
	private Antrieb antrieb = new Antrieb();
	
	public TurboliftSchacht(String name, Deck[] decks) {
		super();
		this.decks = decks;
	}
	public void bediene (Deck ziel) {}
	
	public Deck gibPosition() {
		return this.kabine.position;
	}
	
	public Deck[] gibFahrtziele() {
		return this.kabine.fahrtZiele;
	}
	
}

class Deck extends BennanteEinrichtung{
	public String sektion;
	public boolean fahrtWunsch;
	public Deck(String name) {
		super();
	}
}

class Antrieb {
	public final String ANTRIEBSART = "Linearantrieb";
	
	public void start(int richtung, float geschwindigkeit) {}
	
	public void stop() {}
}

class Kabine {
	public boolean tuerZustand;
	public Deck position;
	public Deck[] fahrtZiele;
	public void veraenderteTuerZustand(boolean tuerZustand) {}
}