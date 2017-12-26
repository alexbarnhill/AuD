import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class WeinPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME = "Wein";

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666, expected = IllegalArgumentException.class)
	public void pubTest__cons__fuellstand_zu_hoch() {
		List<Integer> faesser = Collections.unmodifiableList(Arrays.asList(13, 11, 7, 5));
		List<Integer> staende = Collections.unmodifiableList(Arrays.asList(42, 2, 3, 4));
		new Wein(faesser, staende);
	}

	@Test(timeout = 666)
	public void pubTest__loese__ohne_Saufen__mit_Loesung() {
		List<Integer> faesser = Collections.unmodifiableList(Arrays.asList(13, 11, 7, 5));
		List<Integer> staende = Collections.unmodifiableList(Arrays.asList(13, 0, 0, 0));
		List<Integer> zieleOhneLoesung = Arrays.asList();
		WeinPublicTest.pruefeLoesung(faesser, staende, 0, 13, false, zieleOhneLoesung);
	}

	@Test(timeout = 666)
	public void pubTest__loese__ohne_Saufen__ohne_Loesung() {
		List<Integer> faesser = Collections.unmodifiableList(Arrays.asList(6, 6, 6));
		List<Integer> staende = Collections.unmodifiableList(Arrays.asList(4, 2, 2));
		List<Integer> zieleOhneLoesung = Arrays.asList(1, 3, 5);
		WeinPublicTest.pruefeLoesung(faesser, staende, 0, 6, true, zieleOhneLoesung);
	}

	// ========== HERLPERS HELPER ==========

	protected static final void pruefeLoesung(List<Integer> faesser, List<Integer> staende, int zielMin, int zielMax, boolean saufen, List<Integer> zieleOhneLoesung) {
		Wein wein = new Wein(faesser, staende);
		for (int ziel = zielMin; ziel <= zielMax; ziel++) {
			LinkedList<int[]> loesung = wein.loese(ziel, saufen);
			if (zieleOhneLoesung.contains(ziel)) {
				assertNull("Hicks! Eischendlisch scholde da geine Loeschung nisch schein nooot... Proscht @ " + "Faesser=" + faesser.toString() + ", Staende=" + staende.toString() + ", Ziel=" + ziel + ", Saufen=" + saufen + " ##", loesung);
			} else {
				assertNotNull("Hicks! Eischendlisch scholde da ne Loeschung schein... Proscht @ " + "Faesser=" + faesser.toString() + ", Staende=" + staende.toString() + ", Ziel=" + ziel + ", Saufen=" + saufen + " ##", loesung);
				if (staende.contains(ziel)) {
					assertEquals("Hicks! Isch bin soooo ferdisch! Isch gann nimmer schdehn, geschweige denn noch aufrechd gehn!", 0, loesung.size());
				} else {
					assertNotEquals("Hicks! Isch kann schwar nimmer schdehn, aber wenigschdens nen Schridd muess' ma gehn!", 0, loesung.size());
				}
				List<Integer> stc = new ArrayList<Integer>(staende);
				System.out.println("START:\t" + stc.toString() + " in " + faesser.toString() + " mit Ziel " + ziel + " und " + (saufen ? "MIT" : "ohne") + " Saufen:");
				for (int[] s : loesung) {
					assertNotNull("Fortschritt durch Stillstand? Loesung darf keine null-en enthalten!", s);
					if (s.length == 1) {
						assertTrue("Hey! Nicht schlucken! Nur Saufen, wenn Saufen auch wirklich erlaubt ist!", saufen);
						assertTrue("Ups: Das Fass Nr. " + s[0] + " gibt es nicht!", 0 <= s[0] && s[0] < faesser.size());
						int prost = stc.get(s[0]);
						assertNotEquals("Flasche leer: Aus Fass Nr. " + s[0] + " kann man nix mehr trinken!", 0, prost);
						System.out.println("\t" + stc.toString() + "\t(" + s[0] + " austrinken " + " : " + prost + ")");
						stc.set(s[0], 0);
					} else {
						assertEquals("Ein Schritt heisst: ZWEI Beine bewegen.", 2, s.length);
						assertTrue("Ups: " + s[0] + "->" + s[1] + " geht so nicht!", s[0] != s[1] && 0 <= s[0] && s[0] < faesser.size() && 0 <= s[1] && s[1] < faesser.size());
						int prost = Math.min(stc.get(s[0]), faesser.get(s[1]) - stc.get(s[1]));
						assertNotEquals("Flasche leer oder Vollflasche: " + s[0] + "->" + s[1] + " ergibt keinen Sinn, weil in " + s[0] + " nix mehr drin ist!", 0, prost);
						stc.set(s[0], stc.get(s[0]) - prost);
						stc.set(s[1], stc.get(s[1]) + prost);
						System.out.println("\t" + stc.toString() + "\t(" + s[0] + " -> " + s[1] + " : " + prost + ")");
					}
				}
				System.out.println("ENDE:\t^^^ hier ^^^ sollte iwo " + ziel + " stehen...");
				for (int stand : stc) {
					if (stand == ziel) {
						return;
					}
				}
				fail("Du solltest entweder mehr oder weniger saufen... ;)");
			}
		}
	}
}