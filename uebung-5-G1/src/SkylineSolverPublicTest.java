import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class SkylineSolverPublicTest {
	// ========== SYSTEM ==========
	public static final String CLASS_NAME = "SkylineSolver";
	public static final String METHOD_NAME = "conquer";
	public static final String METHOD_NAME_area = "SkylineSolver.area";
	public static final String METHOD_NAME_divide = "SkylineSolver.divide";
	public static final String METHOD_NAME_conquer = "SkylineSolver.conquer";

	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest__area__oneBuildingInSkyline() {
		int[] skyline = { 5, 9, 26, 0 };
		int[] skylineClone = Arrays.copyOf(skyline, skyline.length);
		int expected = 9 * (26 - 5); // height x width
		int actual = SkylineSolver.area(skylineClone);
		assertEquals(SkylineSolverPublicTest.METHOD_NAME_area + "(" + Arrays.toString(skyline) + ") failed", expected, actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_area + " - DO NOT MODIFY THE INPUT!", skyline, skylineClone);
	}

	@Test(timeout = 666)
	public void pubTest__area__lectureSkyline() {
		int[] skyline = { 1, 11, 3, 13, 9, 0, 12, 7, 16, 3, 19, 18, 22, 3, 23, 13, 29, 0 };
		int[] skylineClone = Arrays.copyOf(skyline, skyline.length);
		int expected = 272;
		int actual = SkylineSolver.area(skylineClone);
		assertEquals(SkylineSolverPublicTest.METHOD_NAME_area + "(" + Arrays.toString(skyline) + ") failed", expected, actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_area + " - DO NOT MODIFY THE INPUT!", skyline, skylineClone);
	}

	// ----------------------------------------
	@Test
	public void pubTest__divide__1_of_many__true() {
		int[][] buildings = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 19 } };
		int[][] buildingsClone = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 19 } };
		int[][] actual = SkylineSolver.divide(buildingsClone, 1, true);
		assertNotNull(SkylineSolverPublicTest.METHOD_NAME_divide + "(" + Arrays.deepToString(buildings) + ", 1, true) must not return null", actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_divide + "(" + Arrays.deepToString(buildings) + ", 1, true) failed", new int[][] { { 1, 11, 5 } }, actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_divide + " - DO NOT MODIFY THE INPUT!", buildings, buildingsClone);
	}
	
	@Test
	public void pubTest__divide__2_of_many__true() {
		int[][] buildings = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 19 } };
		int[][] buildingsClone = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 19 } };
		int[][] actual = SkylineSolver.divide(buildingsClone, 2, true);
		assertNotNull(SkylineSolverPublicTest.METHOD_NAME_divide + "(" + Arrays.deepToString(buildings) + ", 2, true) must not return null", actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_divide + "(" + Arrays.deepToString(buildings) + ", 2, true) failed", new int[][] { { 1, 11, 5 }, {2, 6, 7 }}, actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_divide + " - DO NOT MODIFY THE INPUT!", buildings, buildingsClone);
	}
	
	@Test
	public void pubTest__divide__2_of_many__false() {
		int[][] buildings = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 19 } };
		int[][] buildingsClone = { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 19 } };
		int[][] actual = SkylineSolver.divide(buildingsClone, 2, false);
		assertNotNull(SkylineSolverPublicTest.METHOD_NAME_divide + "(" + Arrays.deepToString(buildings) + ", 2, true) must not return null", actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_divide + "(" + Arrays.deepToString(buildings) + ", 2, true) failed", new int[][] { {3, 13, 19}}, actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_divide + " - DO NOT MODIFY THE INPUT!", buildings, buildingsClone);
	}

	// ----------------------------------------
	@Test
	public void pubTest__conquer__lectureSkyline__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		int[][] buildings = new int[][] { { 1, 11, 5 }, { 2, 6, 7 }, { 3, 13, 9 }, { 12, 7, 16 }, { 14, 3, 25 }, { 19, 18, 22 }, { 23, 13, 29 }, { 24, 4, 28 } };
		int[] expected = { 1, 11, 3, 13, 9, 0, 12, 7, 16, 3, 19, 18, 22, 3, 23, 13, 29, 0 };
		Integer[] stackTraceLogExpected = { 3, 3, 2, 3, 3, 2, 1 };
		check_conquer(buildings, expected, stackTraceLogExpected);
	}

	protected static final void check_conquer(int[][] buildings, int[] expected, Integer[] stackTraceLogExpected) {
		SkylineSolverPublicTest.BigBrother ssh = new SkylineSolverPublicTest.BigBrother();
		int[][] buildingsClone = new int[buildings.length][];
		for (int i = 0; i < buildings.length; i++) {
			buildingsClone[i] = Arrays.copyOf(buildings[i], buildings[i].length);
		}
		int[] actual = SkylineSolver.conquer(ssh, buildingsClone);
		assertArrayEquals(expected, actual);
		assertNotNull(SkylineSolverPublicTest.METHOD_NAME_conquer + "(" + Arrays.deepToString(buildings) + ") must not return null", actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_conquer + "(" + Arrays.deepToString(buildings) + ") failed", expected, actual);
		assertArrayEquals(SkylineSolverPublicTest.METHOD_NAME_conquer + " - DO NOT MODIFY THE INPUT!", buildings, buildingsClone);
		if (stackTraceLogExpected != null) {
			Integer[] stackTraceLogActual = ssh.stackTraceLog.toArray(new Integer[0]);
			assertArrayEquals("Recursion depth/width in stack trace counter seems wrong for " + SkylineSolverPublicTest.METHOD_NAME_conquer + "(" + Arrays.deepToString(buildings) + ")", stackTraceLogExpected, stackTraceLogActual);
		}
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements SkylineSolverHelper {
		protected LinkedList<Integer> stackTraceLog = new LinkedList<>();

		@Override
		public int[] merge(int[] one, int[] two) {
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
			// and now do what they all really expect you to do ;)...:
			ArrayList<Integer> internalRep = new ArrayList<>();
			int l = 0;
			int r = 0;
			int lHeight = 0;
			int rHeight = 0;
			while (l < one.length && r < two.length) {
				if (one[l] < two[r]) {
					int h = Math.max(one[l + 1], rHeight);
					if (internalRep.size() > 0 && internalRep.get(internalRep.size() - 1) == h) {
						// still same height, do nothing
					} else {
						internalRep.add(one[l]);
						internalRep.add(h);
					}
					lHeight = one[l + 1];
					l += 2;
				} else {
					int h = Math.max(two[r + 1], lHeight);
					if (internalRep.size() > 0 && internalRep.get(internalRep.size() - 1) == h) {
						// still same height, do nothing
					} else {
						internalRep.add(two[r]);
						internalRep.add(h);
					}
					rHeight = two[r + 1];
					r += 2;
				}
			}
			while (l < one.length) {
				int h = one[l + 1];
				if (internalRep.size() > 0 && internalRep.get(internalRep.size() - 1) == h) {
					// still same height, do nothing
				} else {
					internalRep.add(one[l]);
					internalRep.add(h);
				}
				l += 2;
			}
			while (r < two.length) {
				int h = two[r + 1];
				if (internalRep.size() > 0 && internalRep.get(internalRep.size() - 1) == h) {
					// still same height, do nothing
				} else {
					internalRep.add(two[r]);
					internalRep.add(h);
				}
				r += 2;
			}
			int[] ret = new int[internalRep.size()];
			for (int i = 0; i < ret.length; i++) {
				ret[i] = internalRep.get(i);
			}
			return ret;
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