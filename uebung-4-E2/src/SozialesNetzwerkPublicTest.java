import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class SozialesNetzwerkPublicTest {
	// ========== SYSTEM ==========
	public static final String CLASS_NAME = "SozialesNetzwerk";
	public static final String METHOD_NAME = "istErreichbar";
	public static final String FIELD_NAME_freundschaft = "freundschaft";
	public static final String METHOD_PATH_fuegeFreundschaftHinzu = CLASS_NAME + ".fuegeFreundschaftHinzu";
	public static final String METHOD_PATH_testeFreundschaft = CLASS_NAME + ".testeFreundschaft";
	public static final String METHOD_PATH_istErreichbar = CLASS_NAME + ".istErreichbar";

	// ========== TEST DATA ==========
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);

	// =========================================================================
	@Test(timeout = 666)
	public void pubTest_initialisiere() {
		zuruecksetzen();
		initialisiere(10);
		pruefeGroesse(10);
	}

	@Test(timeout = 1000)
	public void pubTest_fuegeNutzerHinzu() {
		zuruecksetzen();
		initialisiere(10);
		pruefe_fuegeNutzerHinzu("Hans");
		pruefe_fuegeNutzerHinzu("Klaus");
		pruefe_fuegeNutzerHinzu("Peter");
		pruefe_fuegeNutzerHinzu("Silke");
		pruefe_fuegeNutzerHinzu("Maria");
		pruefe_fuegeNutzerHinzu("Julia");
	}

	@Test(timeout = 666)
	public void pubTest_verwaltung() {
		int[] ids = initialisiere_beispiel();
		pruefe_testeFreundschaft(ids[5], ids[4], true);
		pruefe_testeFreundschaft(ids[4], ids[5], true);
		pruefe_testeFreundschaft(ids[0], ids[0], true);
		SozialesNetzwerk.entferneFreundschaft(ids[0], ids[1]);
		SozialesNetzwerk.entferneFreundschaft(ids[5], ids[4]);
		pruefe_testeFreundschaft(ids[4], ids[5], false);
		pruefe_testeFreundschaft(ids[1], ids[0], false);
		pruefe_testeFreundschaft(ids[0], ids[1], false);
	}

	@Test(timeout = 666)
	public void pubTest_istErreichbar() {
		int[] ids = initialisiere_beispiel();
		SozialesNetzwerk.entferneFreundschaft(ids[0], ids[1]);
		pruefe_istErreichbar(ids[0], ids[1], 1, false);
		pruefe_istErreichbar(ids[0], ids[2], 1, false);
		pruefe_istErreichbar(ids[0], ids[2], 3, false);
		pruefe_istErreichbar(ids[0], ids[5], 2, true);
		pruefe_istErreichbar(ids[3], ids[0], 2, true);
		pruefe_istErreichbar(ids[3], ids[3], 1, true);
	}

	@Test(timeout = 46666)
	public void pubTest__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (int pass = 0; pass < 20; pass++) {
			int n = 42 + RND.nextInt(666);
			n = 42 + 666;
			zuruecksetzen();
			initialisiere(n);
			pruefeGroesse(n);
			for (int i = 0; i < n - 7; i++) {
				pruefe_fuegeNutzerHinzu("Eichhorn" + i);
			}
			LinkedList<Integer> usedIDsList = new LinkedList<>();
			for (int id0 = 0; id0 < n; id0++) {
				if (usedIDs[id0]) {
					usedIDsList.add(id0);
				}
				for (int id1 = 0; id1 < n; id1++) {
					pruefe_testeFreundschaft(id0, id1, usedIDs[id0] && usedIDs[id1] && id0 == id1);
				}
			}
			assertEquals("You have used the wrong number of distinct user IDs in your network.", n - 7, usedIDsList.size());
			BigBrother snmp;
			Integer[] stackTraceLogActual;
			int idStart, idLast, idEnd, aRandom = 3 + RND.nextInt(13), stackDepthActual;
			idStart = idLast = usedIDsList.remove(RND.nextInt(usedIDsList.size() - 1));
			for (int i = 1; i <= 15; i++) {
				idEnd = usedIDsList.remove(RND.nextInt(usedIDsList.size() - 1));
				SozialesNetzwerk.fuegeFreundschaftHinzu(idLast, idEnd);
				if (i == 1 || i == 7 || i == 15 || i == aRandom) {
					// ----- zu kurz -----
					snmp = new BigBrother();
					assertFalse("1) checking value returned by istErreichbar( " + idStart + ", " + idEnd + ", d = " + (i - 1) + " ): ", SozialesNetzwerk.istErreichbar(snmp, idStart, idEnd, (i - 1)));
					stackTraceLogActual = snmp.stackTraceLog.toArray(new Integer[0]);
					Arrays.sort(stackTraceLogActual);
					stackDepthActual = stackTraceLogActual[stackTraceLogActual.length - 1];
					assertTrue("1) checking recursion depth of istErreichbar( " + idStart + ", " + idEnd + ", d = " + (i - 1) + " ): [" + (i - 1) + "<=" + stackDepthActual + "<=" + (i + 1) + "]", i - 1 <= stackDepthActual && stackDepthActual <= i + 1);
					// ----- exakt -----
					snmp = new BigBrother();
					assertTrue("2) checking value returned by istErreichbar( " + idStart + ", " + idEnd + ", d = " + (i) + " ): ", SozialesNetzwerk.istErreichbar(snmp, idStart, idEnd, (i)));
					stackTraceLogActual = snmp.stackTraceLog.toArray(new Integer[0]);
					Arrays.sort(stackTraceLogActual);
					stackDepthActual = stackTraceLogActual[stackTraceLogActual.length - 1];
					assertTrue("2) checking recursion depth of istErreichbar( " + idStart + ", " + idEnd + ", d = " + (i) + " ): [" + (i) + "<=" + stackDepthActual + "<=" + (i + 2) + "]", i <= stackDepthActual && stackDepthActual <= i + 2);
					// ----- zu lang -----
					snmp = new BigBrother();
					assertTrue("3) checking value returned by istErreichbar( " + idStart + ", " + idEnd + ", d = " + (i + 2) + " ): ", SozialesNetzwerk.istErreichbar(snmp, idStart, idEnd, (i + 2)));
					stackTraceLogActual = snmp.stackTraceLog.toArray(new Integer[0]);
					Arrays.sort(stackTraceLogActual);
					stackDepthActual = stackTraceLogActual[stackTraceLogActual.length - 1];
					assertTrue("3) checking recursion depth of istErreichbar( " + idStart + ", " + idEnd + ", d = " + (i + 2) + " ): [" + (i - 1) + "<=" + stackDepthActual + "<=" + (i + 3) + "]", i - 1 <= stackDepthActual && stackDepthActual <= i + 3);
				}
				idLast = idEnd;
			}
		}
	}

	// =========================================================================

	protected static boolean[] usedIDs;

	protected static void pruefeGroesse(int n) {
		assertNotNull("The field " + FIELD_NAME_freundschaft + " must not be null - but it is", SozialesNetzwerk.freundschaft);
		assertEquals("The field " + FIELD_NAME_freundschaft + " has wrong size", n, SozialesNetzwerk.freundschaft.length);
		for (boolean[] sub : SozialesNetzwerk.freundschaft) {
			assertNotNull("The field " + FIELD_NAME_freundschaft + " must not have null sub-entries - but it has", sub);
			assertEquals("At least one sub-entry of the field " + FIELD_NAME_freundschaft + " has wrong size", n, sub.length);
		}
	}

	protected static void pruefe_fuegeNutzerHinzu(String name) {
		int testId = SozialesNetzwerk.fuegeNutzerHinzu(name);
		assertTrue("checking id " + testId + " returned by " + SozialesNetzwerkPublicTest.CLASS_NAME + SozialesNetzwerkPublicTest.METHOD_PATH_fuegeFreundschaftHinzu + " is within bounds", 0 <= testId && testId < usedIDs.length);
		assertFalse("checking id " + testId + " returned by " + SozialesNetzwerkPublicTest.CLASS_NAME + SozialesNetzwerkPublicTest.METHOD_PATH_fuegeFreundschaftHinzu + " was not used before", usedIDs[testId]);
		usedIDs[testId] = true;
	}

	protected static void pruefe_testeFreundschaft(int id1, int id2, boolean friends) {
		assertEquals("checking value returned by " + SozialesNetzwerkPublicTest.CLASS_NAME + SozialesNetzwerkPublicTest.METHOD_PATH_testeFreundschaft + "(" + id1 + ", " + id2 + "): ", friends, SozialesNetzwerk.testeFreundschaft(id1, id2));
	}

	protected static void pruefe_istErreichbar(int id1, int id2, int d, boolean reachable) {
		BigBrother snmp = new BigBrother();
		assertEquals("checking value returned by " + SozialesNetzwerkPublicTest.CLASS_NAME + SozialesNetzwerkPublicTest.METHOD_PATH_istErreichbar + "(*, " + id1 + ", " + id2 + ", " + d + " ): ", reachable, SozialesNetzwerk.istErreichbar(snmp, id1, id2, d));
	}

	protected static void zuruecksetzen() {
		SozialesNetzwerk.freundschaft = null;
		SozialesNetzwerk.anzahlNutzer = 0;
	}

	protected static void initialisiere(int n) {
		SozialesNetzwerk.initialisiere(n);
		int actual = SozialesNetzwerk.anzahlNutzer;
		assertEquals("checking initials: ", 0, actual);
		usedIDs = new boolean[n];
	}

	protected static int[] initialisiere_beispiel() {
		zuruecksetzen();
		initialisiere(10);
		int id0 = SozialesNetzwerk.fuegeNutzerHinzu("Hans");
		int id1 = SozialesNetzwerk.fuegeNutzerHinzu("Klaus");
		int id2 = SozialesNetzwerk.fuegeNutzerHinzu("Peter");
		int id3 = SozialesNetzwerk.fuegeNutzerHinzu("Silke");
		int id4 = SozialesNetzwerk.fuegeNutzerHinzu("Maria");
		int id5 = SozialesNetzwerk.fuegeNutzerHinzu("Julia");
		SozialesNetzwerk.fuegeFreundschaftHinzu(id0, id1);
		SozialesNetzwerk.fuegeFreundschaftHinzu(id1, id2);
		SozialesNetzwerk.fuegeFreundschaftHinzu(id5, id0);
		SozialesNetzwerk.fuegeFreundschaftHinzu(id4, id1);
		SozialesNetzwerk.fuegeFreundschaftHinzu(id3, id5);
		SozialesNetzwerk.fuegeFreundschaftHinzu(id5, id0);
		SozialesNetzwerk.fuegeFreundschaftHinzu(id4, id5);
		return new int[] { id0, id1, id2, id3, id4, id5 };
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements SozialesNetzwerkMethodenProtokoll {
		protected LinkedList<Integer> id0Log = new LinkedList<>();
		protected LinkedList<Integer> id1Log = new LinkedList<>();
		protected LinkedList<Integer> eLog = new LinkedList<>();
		protected LinkedList<Integer> stackTraceLog = new LinkedList<>();

		@Override
		public void istErreichbar(int id0, int id1, int e) {
			id0Log.add(id0);
			id1Log.add(id1);
			eLog.add(e);
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