class TurboliftSystem extends BenannteEinrichtung {
	public int alarmStufe;
	private Steuerung steuerung;
	private TurboliftSchacht[] turboliftSchaechte;
	public TurboliftSystem(String name, TurboliftSchacht[] turboliftSchaechte, Steuerung steuerung) {
		super();
	}

}

abstract class BenannteEinrichtung {
	protected String name;

}

class Deck extends BenannteEinrichtung {
	public String sektion;
	public boolean fahrtWunsch;

	public Deck(String name) {
		super();

	}
}

class Kabine {
	public Deck position;
	public Deck[] fahrtziele;

	public boolean tuerZustand;

	public void veraendereTuerZustand(boolean tuerZustand) {
	}
}

class Antrieb {
	public String ANTRIEBSART = "Linearantrieb";

	public void start(int richtung, float geschwindigkeit) {
	}

	public void stop() {
	}
}

class Steuerung {
	protected TurboliftSchacht[] turoliftSchaechte;
	public Steuerung(TurboliftSchacht[] turboliftSchaechte) {
		super();
	}
}

class TurboliftSchacht extends BenannteEinrichtung {
	protected Deck[] decks;
	public boolean vertikal;
	private Antrieb antrieb = new Antrieb() ;
	private Kabine kabine = new Kabine();

	public TurboliftSchacht(String name, Deck[] decks) {
		super();
		this.decks = decks;
	}

	

	public void bediene(Deck ziel) {

	}

	public Deck gibPosition() {
		return this.kabine.position;

	}

	public Deck[] gibFahrtziele() {
		return this.kabine.fahrtziele;

	}

}





