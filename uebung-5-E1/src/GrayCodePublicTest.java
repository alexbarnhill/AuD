import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class GrayCodePublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME_prevLength = "GrayCode.prevLength";
	public static final String EX_NAME_generate = "GrayCode.generate";
	public static final String CLASS_NAME = "GrayCode";
	public static final String METHOD_NAME_prevLength = "prevLength";
	public static final String METHOD_NAME_generate = "generate";

	// ========== TEST DATA ==========
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);

	// ========== Big Brother ==========
	private static final class BigBrother implements GrayCodeControl {
		protected LinkedList<Integer> lenLog = new LinkedList<>();
		protected LinkedList<Integer> stackTraceLog = new LinkedList<>();

		@Override
		public void logGenerate(GrayCodeControl gcc, int len) {
			lenLog.add(len);
			int stackTiefe = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(CLASS_NAME)) {
					if (ste.getMethodName().equals(METHOD_NAME_generate)) {
						stackTiefe++;
					}
				}
			}
			stackTraceLog.add(stackTiefe);
		}
	}

	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest__prevLength__even() throws Exception {
		for (int pass = 0; pass < 20; pass++) {
			int expected = 42 + RND.nextInt(42);
			int input = 2 * expected;
			GrayCodePublicTest.check__prevLength(expected, input);
		}
	}

	@Test(timeout = 666)
	public void pubTest__prevLength__odd() throws Exception {
		for (int pass = 0; pass < 20; pass++) {
			int expected = 1 << (0x8 + RND.nextInt(15));
			int input = expected + 3;
			GrayCodePublicTest.check__prevLength(expected, input);
		}
	}

	protected static final void check__prevLength(int expected, int input) throws Exception {
		int actual = GrayCode.prevLength(input);
		assertEquals(GrayCodePublicTest.CLASS_NAME + "." + GrayCodePublicTest.METHOD_NAME_prevLength + "(" + input + ") returns wrong result", expected, actual);
	}

	// =========================================================================
	@Test(timeout = 666)
	public void pubTest__generate__1_2_3_4_42__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		GrayCodePublicTest.check_generate(new Integer[] { 1 }, new Integer[] { 1 }, 1);
		GrayCodePublicTest.check_generate(new Integer[] { 2 }, new Integer[] { 1 }, 2);
		GrayCodePublicTest.check_generate(new Integer[] { 3, 2 }, new Integer[] { 1, 2 }, 3);
		GrayCodePublicTest.check_generate(new Integer[] { 4, 2 }, new Integer[] { 1, 2 }, 4);
		GrayCodePublicTest.check_generate(new Integer[] { 42, 21, 16, 8, 4, 2 }, new Integer[] { 1, 2, 3, 4, 5, 6 }, 42);
		GrayCodePublicTest.check_generate(new Integer[] { 666, 333, 256, 128, 64, 32, 16, 8, 4, 2 }, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 666);
		GrayCodePublicTest.check_generate(new Integer[] { 4711, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2 }, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 }, 4711);
	}

	protected static final void check_generate(Integer[] lenLogExpected, Integer[] stackTraceLogExpected, int length) {
		BigBrother gcc = new BigBrother();
		String[] actual = GrayCode.generate(gcc, length);
		assertNotNull("Your implementation must not return null", actual);
		assertEquals("Unexpected length of returned Gray code array", length, actual.length);
		if (lenLogExpected != null) {
			Integer[] lenLogActual = gcc.lenLog.toArray(new Integer[0]);
			assertArrayEquals("Recursion argments seem wrong for " + GrayCodePublicTest.CLASS_NAME + "." + GrayCodePublicTest.METHOD_NAME_generate + "(" + length + ")", lenLogExpected, lenLogActual);
		}
		if (stackTraceLogExpected != null) {
			Integer[] stackTraceLogActual = gcc.stackTraceLog.toArray(new Integer[0]);
			assertArrayEquals("Recursion depth/width in stack trace counter seems wrong for " + GrayCodePublicTest.CLASS_NAME + "." + GrayCodePublicTest.METHOD_NAME_generate + "(" + length + ")", stackTraceLogExpected, stackTraceLogActual);
		}
		HashSet<String> seenCodes = new HashSet<String>();
		String prev = null;
		for (int i = 0; i < actual.length; ++i) {
			String code = actual[i];
			assertNotNull("Your implementation must not return null in array at position " + i, code);
			if (prev != null) {
				if (seenCodes.contains(code)) {
					fail("Not a Gray code: Contains same code [" + code + "] more than once! (Your solution was: " + Arrays.toString(actual) + ")");
				} else if (code.length() != prev.length()) {
					fail("Not a Gray code: Code words of different length [len(" + code + ")=" + code.length() + " vs. len(" + prev + ")=" + prev.length() + "] found! (Your solution was: " + Arrays.toString(actual) + ")");
				}
				int delta = 0;
				for (int j = 0; j < code.length(); ++j) {
					if (prev.charAt(j) != code.charAt(j)) {
						++delta;
					}
				}
				if (delta != 1) {
					fail("Not a Gray code: Hammond distance between at least two adjacent codes differs from 1 for [" + code + "" + " <<" + delta + ">> " + prev + "]");
				}
			}
			seenCodes.add(code);
			prev = code;
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