import static org.junit.Assert.*;
import org.junit.*;

public class StatistikPublicTest {
	// ========== PUBLIC TEST: min/max ==========
	@Test(timeout = 666)
	public void pubTest_min2() {
		double actual1 = Statistik.min2(42.0815d, 666.0815d);
		assertEquals(42.0815, actual1, 1e-42);
		double actual2 = Statistik.min2(666.0815, .815);
		assertEquals(.815, actual2, 1e-42);
	}
	
	@Test
	public void test2_min2() {
		double actual1 = Statistik.min2(-3.0d, -5.0d);
		assertEquals(-5.0d, actual1, 1e-42);
		double actual2 = Statistik.min2(666.0815, .815);
		assertEquals(.815, actual2, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest_min3() {
		double actual1 = Statistik.min3(42.0815d, 4711.0815d, 666.0815d);
		assertEquals(42.0815, actual1, 1e-42);
		double actual3 = Statistik.min3(666.0815, 4711.0815, .815);
		assertEquals(.815, actual3, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest_min7() {
		double actual1 = Statistik.min7(42.0815d, 4711.0815d, 666.0815d, 4711d, 666d, 0x815d, 2017.10d);
		assertEquals(42.0815, actual1, 1e-42);
		double actual3 = Statistik.min7(4711.0815, 666.0815, 4711, 666, 0x815, 2017.10, .815);
		assertEquals(.815, actual3, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest_max2() {
		double actual1 = Statistik.max2(42.0815d, 666.0815d);
		assertEquals(666.0815, actual1, 1e-42);
		double actual2 = Statistik.max2(4711.0815, .815);
		assertEquals(4711.0815, actual2, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest_max3() {
		double actual1 = Statistik.max3(4711.0815d, 42.0815d, 666.0815d);
		assertEquals(4711.0815, actual1, 1e-42);
		double actual3 = Statistik.max3(0.815, 42.0815, 666.0815);
		assertEquals(666.0815, actual3, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest_max7() {
		double actual1 = Statistik.max7(4711.0815d, 42.0815d, 666.0815d, 4711d, 666d, 0x815d, 2017.10d);
		assertEquals(33117.0, actual1, 1e-42);
		double actual3 = Statistik.max7(42.0815, 666.0815, 2017.0815, 666, 0b11111111, .815, 2017.10);
		assertEquals(2017.10, actual3, 1e-42);
		double actual4 = Statistik.max7(7, 6, 5, 4, 3, 2, 1);
		assertEquals(7, actual4, 1e-42);
		double actual5 = Statistik.max7(14, -3, 12, -8, -9, 7, 5);
		assertEquals(14, actual5, 1e-42);
	}

	// ========== PUBLIC TEST: Mittelwert ==========
	@Test(timeout = 666)
	public void pubTest_mittelwert3() {
		double expected1 = 1806.41483333333333;
		double actual1 = Statistik.mittelwert3(4711.0815d, 42.0815d, 666.0815d);
		assertEquals(expected1, actual1, 1e-12);
		double expected2 = 908.421;
		double actual2 = Statistik.mittelwert3(2017.10, 42.0815, 666.0815);
		assertEquals(expected2, actual2, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_mittelwert7() {
		double expected1 = -4137.651514285714;
		double actual1 = Statistik.mittelwert7(4711.0815d, 42.0815d, 666.0815d, -0x815d, -999.4711d, -333d, 66.666d);
		assertEquals(expected1, actual1, 1e-12);
		double expected2 = -4093.508657142857;
		double actual2 = Statistik.mittelwert7(4711.0815d, 42.0815d, 999.0815d, -0x815d, -999.4711d, -333d, 42.666d);
		assertEquals(expected2, actual2, 1e-12);
	}

	// ========== PUBLIC TEST: Median ==========
	@Test(timeout = 666)
	public void pubTest_median3() {
		double actual1 = Statistik.median3(4711.0815d, 42.0815d, -666.0815d);
		assertEquals(42.0815, actual1, 1e-12);
		double actual2 = Statistik.median3(4711.0815d, 42.0815d, 666.0815);
		assertEquals(666.0815, actual2, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_median7() {
		double actual1 = Statistik.median7(4711.0815d, 42.0815d, 666.0815d, -0x815d, -999.4711d, -333d, 66.666d);
		assertEquals(42.0815, actual1, 1e-12);
		double actual2 = Statistik.median7(-4711.0815d, -42.0815d, 999.0815d, -0x815d, -999.4711d, -123.4567890d, 42.666d);
		assertEquals(-123.4567890, actual2, 1e-12);
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