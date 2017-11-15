import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class RucksackPackenPublicTest {
	// ========== SYSTEM ==========
	public static final String CLASS_NAME = "RucksackPacken";
	public static final String METHOD_NAME = "packeSackHelfer";

	// ========== TEST DATA ==========
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);

	// ========== Big Brother ==========
	protected static final class BigBrother implements RucksackPackenProtokoll {
		protected LinkedList<Integer> stackTraceLog = new LinkedList<>();

		@Override
		public void packeSackHelferAufgerufen(RucksackPackenProtokoll rpp, int[] groessen, int[] werte, int platzFrei, int naechsterGegenstand) {
			int stackTiefe = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(CLASS_NAME)) {
					if (ste.getMethodName().equals(METHOD_NAME)) {
						stackTiefe++;
					}
				}
			}
			stackTraceLog.add(stackTiefe);
		}
	}

	// ========== PUBLIC TESTS ==========
	protected static final void pubTest_check(int[] groessen, int[] werte, int sackGroesse, int expected, int stackTraceRekTiefeMaxExpected) {
		BigBrother rpp = new BigBrother();
		int[] groessenKopie = Arrays.copyOf(groessen, groessen.length);
		int[] werteKopie = Arrays.copyOf(werte, werte.length);
		int actual = RucksackPacken.packeSack(rpp, groessenKopie, werteKopie, sackGroesse);
		assertArrayEquals("Ihre Implementierung darf die Eingabe NICHT veraendern!", groessen, groessenKopie);
		assertArrayEquals("Ihre Implementierung darf die Eingabe NICHT veraendern!", werte, werteKopie);
		if (expected >= 0) {
			assertEquals("Ergebnis von " + RucksackPackenPublicTest.CLASS_NAME + "." + RucksackPackenPublicTest.METHOD_NAME + "(*, " + Arrays.toString(groessen) + ", " + Arrays.toString(werte) + ", " + sackGroesse + ") fehlerhaft.", expected, actual);
		}
		if (stackTraceRekTiefeMaxExpected >= 0) {
			Integer[] stackTraceLogActual = rpp.stackTraceLog.toArray(new Integer[0]);
			Arrays.sort(stackTraceLogActual);
			int stackTraceRekTiefeMaxActual = stackTraceLogActual[stackTraceLogActual.length - 1];
			assertTrue("Rekursionstiefe bei " + RucksackPackenPublicTest.CLASS_NAME + "." + RucksackPackenPublicTest.METHOD_NAME + "(*, " + Arrays.toString(groessen) + ", " + Arrays.toString(werte) + ", " + sackGroesse + ") fehlerhaft: SOLL=[" + (stackTraceRekTiefeMaxExpected - 1) + "..." + (stackTraceRekTiefeMaxExpected - 1) + "] vs. IST=" + stackTraceRekTiefeMaxActual, stackTraceRekTiefeMaxExpected - 1 <= stackTraceRekTiefeMaxActual && stackTraceRekTiefeMaxActual <= stackTraceRekTiefeMaxExpected + 1);
		}
	}

	@Test(timeout = 666)
	public void pubTest_normal() {
		int[] groessen = { 2, 3, 2, 2 };
		int[] werte = { 1, 1, 2, 2 };
		int sackGroesse = 6;
		int expected = 5;
		int stackTraceRekTiefeMaxExpected = groessen.length + 1;
		RucksackPackenPublicTest.pubTest_check(groessen, werte, sackGroesse, expected, stackTraceRekTiefeMaxExpected);
	}
	
	@Test(timeout = 666)
	public void pubTest_normal_2() {
		int[] groessen = { 2, 1, 2, 2, 2 };
		int[] werte = { -1, -8, -4, -2, -2 };
		int sackGroesse = 6;
		int expected = 0;
		int stackTraceRekTiefeMaxExpected = groessen.length + 1;
		RucksackPackenPublicTest.pubTest_check(groessen, werte, sackGroesse, expected, stackTraceRekTiefeMaxExpected);
	}

	@Test(timeout = 16666)
	public void pubTest__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (int pass = 0; pass < 20; pass++) {
			int anzahlGegenstaende = 24 + RND.nextInt(42);
			int[] groessen = new int[anzahlGegenstaende];
			int[] werte = new int[anzahlGegenstaende];
			for (int i = 0; i < anzahlGegenstaende; i++) {
				groessen[i] = 666 + RND.nextInt(666);
				werte[i] = 666 + RND.nextInt(666);
			}
			int sackGroesse = 666 + RND.nextInt(666);
			int expected = -666;
			int stackTraceRekTiefeMaxExpected = groessen.length + 1;
			RucksackPackenPublicTest.pubTest_check(groessen, werte, sackGroesse, expected, stackTraceRekTiefeMaxExpected);
		}
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}