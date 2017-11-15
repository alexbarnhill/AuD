import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class WrittenRecursiveMultiplicationPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_countUsedBits = "countUsedBits";
	protected static final String EX_extractLowerBits = "extractLowerBits";
	protected static final String EX_extractHigherBits = "extractHigherBits";
	protected static final String EX_combine = "combine";
	protected static final String EX_writtenMulRec4 = "writtenMulRec4";
	protected static final String EX_writtenMulRec3 = "writtenMulRec3";
	protected static final String CLASS_NAME = "WrittenRecursiveMultiplication";
	protected static final String METHOD_NAME_countUsedBits = "countUsedBits";
	protected static final String METHOD_NAME_extractLowerBits = "extractLowerBits";
	protected static final String METHOD_NAME_extractHigherBits = "extractHigherBits";
	protected static final String METHOD_NAME_writtenMulRec4 = "writtenMulRec4";
	protected static final String METHOD_NAME_writtenMulRec3 = "writtenMulRec3";

	// ========== TEST DATA ==========
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);

	// ========== Big Brother ==========
	protected static final class BigBrother implements WRM_Logger {
		protected LinkedList<Long> countUsedBits_num = new LinkedList<>();
		protected LinkedList<Long> countUsedBits_stack = new LinkedList<>();
		protected LinkedList<Long> writtenMulRec4_x = new LinkedList<>();
		protected LinkedList<Long> writtenMulRec4_y = new LinkedList<>();
		protected LinkedList<Long> writtenMulRec4_stack = new LinkedList<>();
		protected LinkedList<Long> writtenMulRec3_x = new LinkedList<>();
		protected LinkedList<Long> writtenMulRec3_y = new LinkedList<>();
		protected LinkedList<Long> writtenMulRec3_stack = new LinkedList<>();

		private long getStackCount(String className, String methodName) {
			long stackCount = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(className)) {
					if (ste.getMethodName().equals(methodName)) {
						stackCount++;
					}
				}
			}
			return stackCount;
		}

		@Override
		public void log_countUsedBits(long num) {
			countUsedBits_num.add(num);
			countUsedBits_stack.add(getStackCount(CLASS_NAME, METHOD_NAME_countUsedBits));
		}

		@Override
		public void log_writtenMulRec4(long x, long y) {
			writtenMulRec4_x.add(x);
			writtenMulRec4_y.add(y);
			writtenMulRec4_stack.add(getStackCount(CLASS_NAME, METHOD_NAME_writtenMulRec4));
		}

		@Override
		public void log_writtenMulRec3(long x, long y) {
			writtenMulRec3_x.add(x);
			writtenMulRec3_y.add(y);
			writtenMulRec3_stack.add(getStackCount(CLASS_NAME, METHOD_NAME_writtenMulRec3));
		}
	}

	// ========== PUBLIC TEST ==========
	protected static final void check_countUsedBits(long num, int expected, int countUsedBits_stack_max, LinkedList<Long> countUsedBits_num_expected) {
		BigBrother LOGGER = new BigBrother();
		WrittenRecursiveMultiplication.LOGGER = LOGGER;
		int actual = WrittenRecursiveMultiplication.countUsedBits(num);
		assertEquals("Result of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_countUsedBits + "(" + num + ") is wrong.", expected, actual);
		Long[] countUsedBits_stack_array = LOGGER.countUsedBits_stack.toArray(new Long[0]);
		Arrays.sort(countUsedBits_stack_array);
		assertEquals("Recursion depth of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_countUsedBits + "(" + num + ") is wrong.", Long.valueOf(countUsedBits_stack_max), countUsedBits_stack_array[countUsedBits_stack_array.length - 1]);
		if (countUsedBits_num_expected != null) {
			assertEquals("Recursive call arguments of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_countUsedBits + "(" + num + ") are wrong.", countUsedBits_num_expected, LOGGER.countUsedBits_num);
		}
	}

	@Test(timeout = 666)
	public void pubTest_countUsedBits__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (int pass = 0; pass < 42; pass++) {
			LinkedList<Long> countUsedBits_num_expected = new LinkedList<>();
			int expected = RND.nextInt(63);
			long num = 1;
			countUsedBits_num_expected.add(0, num);
			for (int i = 1; i < expected; i++) {
				num = (num << 1) + RND.nextInt(2);
				countUsedBits_num_expected.add(0, num);
			}
			WrittenRecursiveMultiplicationPublicTest.check_countUsedBits(num, num == 1 ? 1 : expected, num == 1 ? 1 : expected, countUsedBits_num_expected);
		}
	}

	// ----------------------------------------
	@Test(timeout = 666)
	public void pubTest_extractLowerBits__THIS_TEST_IS_QUITE_IMPORTANT__if_it_fails_you_may_get_almost_no_points_at_all() {
		for (int pass = 0; pass < 42; pass++) {
			int lowerBits = 1 + RND.nextInt(42);
			int totalBits = lowerBits + RND.nextInt(63 - lowerBits);
			long num = 1, rnd, expected = 0;
			for (int i = 0; i < totalBits; i++) {
				rnd = RND.nextInt(2);
				num = (num << 1) + rnd;
				expected = i >= totalBits - lowerBits ? (expected << 1) + rnd : expected;
			}
			long actual = WrittenRecursiveMultiplication.extractLowerBits(lowerBits, num);
			assertEquals("Result of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_extractLowerBits + "(" + lowerBits + ", " + num + ") is wrong.", expected, actual);
		}
	}

	// ----------------------------------------
	@Test(timeout = 666)
	public void pubTest_extractHigherBits__THIS_TEST_IS_QUITE_IMPORTANT__if_it_fails_you_may_get_almost_no_points_at_all() {
		for (int pass = 0; pass < 42; pass++) {
			int lowerBits = 1 + RND.nextInt(24);
			int totalBits = lowerBits + RND.nextInt(63 - lowerBits);
			long num = 1, rnd, expected = 1;
			for (int i = 0; i < totalBits; i++) {
				rnd = RND.nextInt(2);
				num = (num << 1) + rnd;
				expected = i < totalBits - lowerBits ? (expected << 1) + rnd : expected;
			}
			long actual = WrittenRecursiveMultiplication.extractHigherBits(lowerBits, num);
			assertEquals("Result of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_extractHigherBits + "(" + lowerBits + ", " + num + ") is wrong.", expected, actual);
		}
	}

	// ----------------------------------------
	@Test(timeout = 666)
	public void pubTest_combine__THIS_TEST_IS_QUITE_IMPORTANT__if_it_fails_you_may_get_almost_no_points_at_all() {
		for (int pass = 0; pass < 42; pass++) {
			int bits = 2 + RND.nextInt(20);
			long expected = 0, up = 0, mid = 0, low = 0, rnd;
			for (int i = 0; i < 3 * bits; i++) {
				rnd = RND.nextInt(2);
				expected = (expected << 1) + rnd;
				up = i < bits ? (up << 1) + rnd : up;
				mid = bits <= i && i < 2 * bits ? (mid << 1) + rnd : mid;
				low = 2 * bits <= i ? (low << 1) + rnd : low;
			}
			long actual = WrittenRecursiveMultiplication.combine(up, mid, low, bits);
			assertEquals("Result of " + WrittenRecursiveMultiplicationPublicTest.EX_combine + "(" + up + ", " + mid + ", " + low + ", " + bits + ") is wrong.", expected, actual);
		}
	}

	// ----------------------------------------
	protected static final void check_writtenMulRec4(long x, long y, long expected, long[] log_x, long[] log_y, long[] log_stack) {
		BigBrother LOGGER = new BigBrother();
		WrittenRecursiveMultiplication.LOGGER = LOGGER;
		long actual = WrittenRecursiveMultiplication.writtenMulRec4(x, y);
		assertEquals("Result of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec4 + "(" + x + ", " + y + ") is wrong.", expected, actual);
		LinkedList<Long> log_x_L = new LinkedList<>();
		for (long v : log_x) {
			log_x_L.add(v);
		}
		LinkedList<Long> log_y_L = new LinkedList<>();
		for (long v : log_y) {
			log_y_L.add(v);
		}
		LinkedList<Long> log_stack_L = new LinkedList<>();
		for (long v : log_stack) {
			log_stack_L.add(v);
		}
		assertArrayEquals("Recursive call arguments for x of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec4 + "(" + x + ", " + y + ") are wrong. Found: " + Arrays.toString(LOGGER.writtenMulRec4_x.toArray(new Long[0])), log_x_L.toArray(new Long[0]), LOGGER.writtenMulRec4_x.toArray(new Long[0]));
		assertArrayEquals("Recursive call arguments for y of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec4 + "(" + x + ", " + y + ") are wrong. Found: " + Arrays.toString(LOGGER.writtenMulRec4_y.toArray(new Long[0])), log_y_L.toArray(new Long[0]), LOGGER.writtenMulRec4_y.toArray(new Long[0]));
		assertArrayEquals("Recursion depth of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec4 + "(" + x + ", " + y + ") is wrong. Found: " + Arrays.toString(LOGGER.writtenMulRec4_stack.toArray(new Long[0])), log_stack_L.toArray(new Long[0]), LOGGER.writtenMulRec4_stack.toArray(new Long[0]));
	}

	@Test(timeout = 666)
	public void pubTest_writtenMulRec4__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		long[] xs = { 42, 3, 10, 42 };
		long[] ys = { 0, 4, 10, 23 };
		long[] expecteds = { 42 * 0, 3 * 4, 10 * 10, 42 * 23 };
		long[][] log_xs = { { 42 }, { 3, 0, 0, 3, 3 }, //
				{ 10, 2, 1, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 0, 2, 1, 1, 0, 0 }, //
				{ 42, 5, 1, 1, 1, 1, 5, 1, 1, 1, 1, 2, 1, 1, 0, 0, 2, 0, 0, 2, 2, 1, 1, 0, 0 } //
		};
		long[][] log_ys = { { 0 }, { 4, 1, 0, 1, 0 }, //
				{ 10, 2, 1, 0, 1, 0, 2, 1, 0, 1, 0, 2, 1, 0, 1, 0, 2, 1, 0, 1, 0 }, //
				{ 23, 2, 0, 2, 0, 2, 7, 1, 3, 1, 3, 2, 1, 0, 1, 0, 7, 1, 3, 1, 3, 1, 1, 1, 1 } //
		};
		long[][] log_stacks = { { 1 }, { 1, 2, 2, 2, 2 }, //
				{ 1, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3 }, //
				{ 1, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3, 4, 4, 4, 4 } //
		};
		for (int i = 0; i < xs.length; i++) {
			check_writtenMulRec4(xs[i], ys[i], expecteds[i], log_xs[i], log_ys[i], log_stacks[i]);
		}
	}

	// ----------------------------------------
	protected static final void check_writtenMulRec3(long x, long y, long expected, long[] log_x, long[] log_y, long[] log_stack) {
		BigBrother LOGGER = new BigBrother();
		WrittenRecursiveMultiplication.LOGGER = LOGGER;
		long actual = WrittenRecursiveMultiplication.writtenMulRec3(x, y);
		assertEquals("Result of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec3 + "(" + x + ", " + y + ") is wrong.", expected, actual);
		LinkedList<Long> log_x_L = new LinkedList<>();
		for (long v : log_x) {
			log_x_L.add(v);
		}
		LinkedList<Long> log_y_L = new LinkedList<>();
		for (long v : log_y) {
			log_y_L.add(v);
		}
		LinkedList<Long> log_stack_L = new LinkedList<>();
		for (long v : log_stack) {
			log_stack_L.add(v);
		}
		assertArrayEquals("Recursive call arguments for x of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec3 + "(" + x + ", " + y + ") are wrong. Found: " + Arrays.toString(LOGGER.writtenMulRec3_x.toArray(new Long[0])), log_x_L.toArray(new Long[0]), LOGGER.writtenMulRec3_x.toArray(new Long[0]));
		assertArrayEquals("Recursive call arguments for y of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec3 + "(" + x + ", " + y + ") are wrong. Found: " + Arrays.toString(LOGGER.writtenMulRec3_y.toArray(new Long[0])), log_y_L.toArray(new Long[0]), LOGGER.writtenMulRec3_y.toArray(new Long[0]));
		assertArrayEquals("Recursion depth of " + WrittenRecursiveMultiplicationPublicTest.METHOD_NAME_writtenMulRec3 + "(" + x + ", " + y + ") is wrong. Found: " + Arrays.toString(LOGGER.writtenMulRec3_stack.toArray(new Long[0])), log_stack_L.toArray(new Long[0]), LOGGER.writtenMulRec3_stack.toArray(new Long[0]));
	}

	@Test(timeout = 666)
	public void pubTest_writtenMulRec3__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		long[] xs = { 42, 3, 10, 42 };
		long[] ys = { 0, 4, 10, 23 };
		long[] expecteds = { 42 * 0, 3 * 4, 10 * 10, 42 * 23 };
		long[][] log_xs = { { 42 }, { 3, 0, 3, 3 }, //
				{ 10, 2, 1, 1, 0, 4, 1, 1, 0, 2, 1, 1, 0 }, //
				{ 42, 5, 1, 2, 1, 1, 0, 1, 7, 1, 4, 1, 1, 0, 3, 2, 0, 2, 0, 2, 2, 2, 1, 1, 0 } //
		};
		long[][] log_ys = { { 0 }, { 4, 1, 1, 0 }, //
				{ 10, 2, 1, 1, 0, 4, 1, 1, 0, 2, 1, 1, 0 }, //
				{ 23, 2, 0, 2, 1, 1, 0, 2, 9, 2, 3, 0, 3, 3, 1, 7, 1, 4, 1, 1, 0, 3, 1, 2, 1 } //
		};
		long[][] log_stacks = { { 1 }, { 1, 2, 2, 2 }, //
				{ 1, 2, 3, 3, 3, 2, 3, 3, 3, 2, 3, 3, 3 }, //
				{ 1, 2, 3, 3, 4, 4, 4, 3, 2, 3, 3, 4, 4, 4, 3, 2, 3, 3, 4, 4, 4, 3, 4, 4, 4 } //
		};
		for (int i = 0; i < xs.length; i++) {
			check_writtenMulRec3(xs[i], ys[i], expecteds[i], log_xs[i], log_ys[i], log_stacks[i]);
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