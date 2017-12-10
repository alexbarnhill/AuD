import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class LightsOutPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_BitOps = "BitOps";
	protected static final String EX_NAME_LightsOut_LightsOut = "LightsOut.LightsOut";
	protected static final String EX_NAME_LightsOut_toggle = "LightsOut.toggle";
	protected static final String EX_NAME_LightsOut_solve = "LightsOut.solve";

	// ========== TEST DATA ==========
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);

	// ========== PUBLIC TEST ==========
	// -------------------- BitOps --------------------
	@Test(timeout = 666)
	public void pubTest__BitOps__set() {
		long bitSet;
		long actual;
		for (int bitIndex = 0; bitIndex < 64; bitIndex++) {
			bitSet = 0;
			actual = BitOps.set(bitSet, bitIndex);
			assertEquals("You didn't set that bit in <" + bitSet + "> at <" + bitIndex + ">, did you?", (1L << bitIndex), actual);
			bitSet = (1L << bitIndex);
			actual = BitOps.set(bitSet, bitIndex);
			assertEquals("You didn't set that bit in <" + bitSet + "> at <" + bitIndex + ">, did you?", (1L << bitIndex), actual);
		}
	}

	@Test(timeout = 666)
	public void pubTest__BitOps__isSet() {
		long bitSet;
		for (int bitIndex = 1; bitIndex < 63; bitIndex++) {
			bitSet = 1L << bitIndex;
			assertFalse("There is no bit in <" + bitSet + "> at <" + (bitIndex - 1) + "> - what did you find here?", BitOps.isSet(bitSet, bitIndex - 1));
			assertTrue("Just set that bit in <" + bitSet + "> at <" + (bitIndex) + ">, but you didn't find it.", BitOps.isSet(bitSet, bitIndex));
			assertFalse("There is no bit in <" + bitSet + "> at <" + (bitIndex + 1) + "> - what did you find here?", BitOps.isSet(bitSet, bitIndex + 1));
		}
	}

	@Test(timeout = 666)
	public void pubTest__BitOps__clear() {
		long bitSet;
		long actual;
		for (int bitIndex = 0; bitIndex < 63; bitIndex++) {
			bitSet = 1L << bitIndex;
			actual = BitOps.clear(bitSet, bitIndex);
			assertEquals("You didn't clear that bit in <" + bitSet + "> at <" + bitIndex + ">, did you?", 0, actual);
			bitSet = 0;
			actual = BitOps.clear(bitSet, bitIndex);
			assertEquals("You didn't clear that bit in <" + bitSet + "> at <" + bitIndex + ">, did you?", 0, actual);
		}
	}

	@Test(timeout = 666)
	public void pubTest__BitOps__flip() {
		long bitSet;
		long actual;
		for (int bitIndex = 0; bitIndex < 63; bitIndex++) {
			bitSet = 0;
			actual = BitOps.flip(bitSet, bitIndex);
			assertEquals("You didn't flip that bit in <" + bitSet + "> at <" + bitIndex + ">, did you?", (1L << bitIndex), actual);
			bitSet = 1L << bitIndex;
			actual = BitOps.flip(bitSet, bitIndex);
			assertEquals("You didn't flip that bit in <" + bitSet + "> at <" + bitIndex + ">, did you?", 0, actual);
		}
	}

	// -------------------- LightsOut --------------------
	// legend: "+" == light is on / "-" == light is off / "#" == no bulb, no switch => no light, never

	@Test(timeout = 666)
	public void pubTest__cons__noException() {
		// state_|mask_|=>|after_cons
		// +++++_|#----|=>|-++++
		// +++++_|-##--|=>|+--++
		// -++-++|---##|=>|-++--
		long state = 0b0__________1_10110_11111_11111;
		long mask = 0b0_____________11000_00110_00001;
		long stateExpected = 0b0____00110_11001_11110;
		LightsOut lightsOut = new LightsOut(5, 3, state, mask);
		long stateActual = lightsOut.getState();
		assertEquals("Your cons failed.", stateExpected, stateActual);
	}

	@Test(timeout = 666, expected = IllegalArgumentException.class)
	public void pubTest__cons__withException() {
		long state = 0b0_111111111111_111;
		long mask = 0b0_______________010;
		new LightsOut(42, 0, state, mask);
	}

	@Test(timeout = 666)
	public void pubTest__toggle() {
		// state|mask|toggle|=>|after_toggle
		// -+-__|-#-_|---___|=>|---
		// -++__|#--_|-X-___|=>|---
		// ---__|---_|---___|=>|-+-
		// +++__|#-#_|---___|=>|-+-
		long state = 0b0__________111_000_110_010;
		long mask = 0b0___________101_000_001_010;
		long stateExpected = 0b0__010_010_000_000;
		LightsOut lightsOut = new LightsOut(3, 4, state, mask);
		lightsOut.toggle(1, 1);
		long stateActual = lightsOut.getState();
		assertEquals("You toggled wrong.", stateExpected, stateActual);
	}

	@Test(timeout = 666)
	public void pubTest__solve__baby() {
		// state|mask_|=>|after_solve
		// -+---|#---#|=>|-----
		// ++---|--#--|=>|-----
		// --++-|#---#|=>|-----
		// --+--|#---#|=>|-----
		long state = 0b0__00100_01100_00011_00010;
		long mask = 0b0___10001_10001_00100_10001;
		long stateExpected = state;
		LightsOut lightsOut = new LightsOut(5, 4, state, mask);
		ZahlenFolgenMerker movesMerker = lightsOut.solve();
		Integer[] solutionActual = movesMerker == null ? null : movesMerker.gibtMirAlle();
		long stateActual = lightsOut.getState();
		assertEquals("state must be cleaned by the cons but immutable during solve.", stateExpected, stateActual);
		assertNotNull("There is a solution, I know it!", solutionActual);
		assertEquals("Two toggles are enough here (6 and 12).", 2, solutionActual.length);
		assertTrue("Two toggles are enough here (6 and 12) - but you gave me: " + Arrays.toString(solutionActual), (solutionActual[0] == 6 && solutionActual[1] == 12) || (solutionActual[0] == 12 && solutionActual[1] == 6));
	}

	@Test(timeout = 6666)
	public void pubTest__solve__easy() {
		LightsOutPublicTest.check_solve_with_solution(4, 7, 0b0_0110_1110_0011_0101_0010_1000_0100, 0b0_1000_0000_1100_1010_1000_0010_0011, 0b0_0110_1110_0011_0101_0010_1000_0100, 9);
	}

	@Test(timeout = 2666)
	public void pubTest__solve__8xY_games__21_or_29_masks__0_1_2_x_toggle_req() {
		long[][] states = { //
				{ 3L << 0, 1L }, //
				{ 7L << 0, 1L }, { 7L << 1, 1L }, { 7L << 2, 1L }, { 7L << 4, 1L }, { 7L << 3, 1L }, { 7L << 5, 1L }, //
				{ 7L << 16, 1L }, { 7L << 17, 1L }, { 7L << 18, 1L }, { 7L << 19, 1L }, { 7L << 20, 1L }, { 7L << 21, 1L }, //
				{ 7L << 32, 1L }, { 7L << 33, 1L }, { 7L << 34, 1L }, { 7L << 35, 1L }, { 7L << 36, 1L }, { 7L << 37, 1L }, //
				{ 7L << 48, 1L }, { 7L << 49, 1L }, { 7L << 50, 1L }, { 7L << 51, 1L }, { 7L << 52, 1L }, { 7L << 53, 1L }, //
				{ 3L << 48, 1L }, //
		};
		long mask = 0b0__11111111_00000000_01111111_00000000_11111110_00000000_01111111_00000000L;
		for (int a = 0; a < states.length; a++) {
			LightsOutPublicTest.check_solve_with_solution(8, RND.nextBoolean() ? 7 : 8, states[a][0], mask, states[a][0], 1);
			for (int b = 0; b < states.length; b++) {
				LightsOutPublicTest.check_solve_with_solution(8, RND.nextBoolean() ? 7 : 8, states[a][0] ^ states[b][0], mask, states[a][0] ^ states[b][0], a == b ? 0 : 2);
			}
		}
	}

	@Test(timeout = 4666)
	public void pubTest__solve__8xY_games__21_or_29_masks__up_to_3_x_toggle_req() {
		long[][] states = { //
				{ 3L << 0, 1L }, //
				{ 7L << 0, 1L }, { 7L << 1, 1L }, { 7L << 2, 1L }, { 7L << 4, 1L }, { 7L << 3, 1L }, { 7L << 5, 1L }, //
				{ 7L << 16, 1L }, { 7L << 17, 1L }, { 7L << 18, 1L }, { 7L << 19, 1L }, { 7L << 20, 1L }, { 7L << 21, 1L }, //
				{ 7L << 32, 1L }, { 7L << 33, 1L }, { 7L << 34, 1L }, { 7L << 35, 1L }, { 7L << 36, 1L }, { 7L << 37, 1L }, //
				{ 7L << 48, 1L }, { 7L << 49, 1L }, { 7L << 50, 1L }, { 7L << 51, 1L }, { 7L << 52, 1L }, { 7L << 53, 1L }, //
				{ 3L << 48, 1L }, //
		};
		long mask = 0b0__11111111_00000000_01111111_00000000_11111110_00000000_01111111_00000000L;
		for (int pass = 0; pass < 42; pass++) {
			int a = RND.nextInt(states.length), b = RND.nextInt(states.length), c = RND.nextInt(states.length);
			LightsOutPublicTest.check_solve_with_solution(8, RND.nextBoolean() ? 7 : 8, states[a][0] ^ states[b][0] ^ states[c][0], mask, states[a][0] ^ states[b][0] ^ states[c][0], a == b && b == c ? 1 : a == b || a == c || b == c ? 1 : 3);
		}
	}

	private static final void check_solve_with_solution(int cols, int rows, long state, long mask, long stateExpected, long togglesExpected) {
		LightsOut lightsOut = new LightsOut(cols, rows, state, mask);
		ZahlenFolgenMerker movesMerker = lightsOut.solve();
		Integer[] solutionActual = movesMerker == null ? null : movesMerker.gibtMirAlle();
		long stateActual = lightsOut.getState();
		assertEquals("state must be cleaned by the cons but immutable during solve.", stateExpected, stateActual);
		assertNotNull("There is a solution, I know it!", solutionActual);
		assertEquals(togglesExpected + " toggles are enough here.", togglesExpected, solutionActual.length);
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar $(ls * | grep PublicTest.class | sed s/.class//)

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}