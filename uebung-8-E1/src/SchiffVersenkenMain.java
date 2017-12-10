public class SchiffVersenkenMain {
	private static final int MIN = 2, MAX = 4;
	private static final java.util.Scanner INPUT = new java.util.Scanner(System.in);
	private static final java.util.Random RND = new java.util.Random();

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("<hoehe> <breite> <maxFehlversuche>");
			return;
		}
		final int hoehe = Integer.parseInt(args[0]), breite = Integer.parseInt(args[1]), maxFehlversuche = Integer.parseInt(args[2]);
		if (hoehe < MAX || breite < MAX) {
			System.out.println("Da passt kein Schlachtschiff drauf!");
			return;
		}
		final SchiffVersenken sv = new SchiffVersenken(hoehe, breite, maxFehlversuche);
		sv.neuesSpielfeld();
		boolean senkrecht = RND.nextBoolean();
		int xPos = 0, yPos = 0, schiffLaenge = MIN + RND.nextInt(MAX - MIN + 1);
		if (senkrecht) {
			xPos = RND.nextInt(breite);
			yPos = RND.nextInt(hoehe - schiffLaenge + 1);
		} else {
			xPos = RND.nextInt(breite - schiffLaenge + 1);
			yPos = RND.nextInt(hoehe);
		}
		if (sv.positioniereSchiff(xPos, yPos, schiffLaenge, senkrecht)) {
			int ergebnis;
			do {
				sv.feldAusgeben(false);
				System.out.print("Schuss-Koordinaten: ");
				int xKoord = INPUT.next().toUpperCase().charAt(0) - 'A';
				int yKoord = INPUT.nextInt() - 1;
				ergebnis = sv.spielzugAusfuehren(xKoord, yKoord);
				if (ergebnis == 0) {
					System.out.println("Fehlerhafte Koordinaten?");
				} else if (ergebnis == 1) {
					System.out.println("Wasser.");
				} else if (ergebnis == 2) {
					System.out.println("Treffer!");
				}
			} while (ergebnis >= 0);
			if (ergebnis == -1) {
				System.out.println("Leider verloren.");
			} else {
				System.out.println("Schiff versenkt!");
			}
			sv.feldAusgeben(true);
		} else {
			System.out.println("Ups: " + xPos + " / " + yPos + " / " + schiffLaenge + " / " + senkrecht);
		}
	}
}