public class SchiffVersenken {
	private static final char SCHIFF = 'X';
	private final int hoehe;
	private final int breite;
	private final int maxFehlversuche;
	private final char[][] spielfeld;
	private int schiffLaenge;
	private int fehlversuche;
	private int treffer;

	public SchiffVersenken(int hoehe, int breite, int maxFehlversuche) {
		if (hoehe < 2) {
			hoehe = 2;
		}
		if (breite < 2) {
			breite = 2;
		}
		if (maxFehlversuche < 2) {
			maxFehlversuche = 2;
		}
		this.hoehe = hoehe;
		this.breite = breite;
		this.maxFehlversuche = maxFehlversuche;
		spielfeld = new char[hoehe][breite];
	}

	public void neuesSpielfeld() {
		Log.log("neuesSpielfeld");
		Log.log(1);
		for (int y = 0; y < hoehe; y++) {
			Log.log(2);
			for (int x = 0; x < breite; x++) {
				Log.log(3);
				spielfeld[y][x] = '~';
				Log.log(2);
			}
			Log.log(4);
			Log.log(1);
		}
		Log.log(5);
		fehlversuche = 0;
		treffer = 0;
		Log.log();
	}

	public boolean positioniereSchiff(int xPos, int yPos, int schiffLaenge, boolean senkrecht) {
		Log.log("positioniereSchiff");
		if (xPos < 0 || yPos < 0 || xPos >= breite || yPos >= hoehe || schiffLaenge <= 0) {
			Log.log();
			return false;
		}
		if (senkrecht && yPos + schiffLaenge > hoehe) {
			Log.log();
			return false;
		}
		if ((!senkrecht) && xPos + schiffLaenge > breite) {
			Log.log();
			return false;
		}
		this.schiffLaenge = schiffLaenge;
		for (int i = 0; i < schiffLaenge; i++) {
			spielfeld[yPos][xPos] = SCHIFF;
			if (senkrecht) {
				yPos++;
			} else {
				xPos++;
			}
		}
		Log.log();
		return true;
	}

	public void feldAusgeben(boolean schiffZeigen) {
		for (int y = hoehe - 1; y >= 0; y--) {
			if (y + 1 < 10) {
				System.out.print(" ");
			}
			System.out.print((y + 1) + " ");
			for (int x = 0; x < breite; x++) {
				char s = spielfeld[y][x];
				if ((s == SCHIFF) && (!schiffZeigen)) {
					s = '~';
				}
				System.out.print(s + " ");
			}
			System.out.println();
		}
		System.out.print("  ");
		for (char c = 'A'; c < 'A' + breite; c++) {
			System.out.print(" " + c);
		}
		System.out.println("");
	}

	public int spielzugAusfuehren(int xKoord, int yKoord) {
		if (xKoord < 0 || xKoord >= breite || yKoord < 0 || yKoord >= hoehe) {
			return 0;
		} else {
			if (spielfeld[yKoord][xKoord] != SCHIFF) {
				spielfeld[yKoord][xKoord] = 'o';
				fehlversuche++;
				if (fehlversuche < maxFehlversuche) {
					return 1;
				} else {
					return -1;
				}
			} else {
				spielfeld[yKoord][xKoord] = 'x';
				treffer++;
				if (treffer < schiffLaenge) {
					return 2;
				} else {
					return -2;
				}
			}
		}
	}
}