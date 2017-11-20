import static org.junit.Assert.*;
import org.junit.*;

public class LotteriePublicTest {
	// ========== PUBLIC TESTS ==========
	protected static final void test_fakultaet(int k, double expected, double delta) {
		double actual = Lotterie.fakultaet(k);
		assertEquals("fakultaet(" + k + ")", expected, actual, delta);
	}

	@Test(timeout = 666)
	public void pubTest_fakultaet_0_to_3_and_169_170() {
		test_fakultaet(0, 1, 1e-12);
		test_fakultaet(1, 1, 1e-12);
		test_fakultaet(2, 2, 1e-12);
		test_fakultaet(3, 6, 1e-12);
		test_fakultaet(169, 4.269068009004705275e304, 1e+290);
		test_fakultaet(170, 7.257415615307998967e306, 1e+292);
	}

	protected static final void test_binomialkoeffizient(int m, int n, double expected, double delta) {
		double actual = Lotterie.binomialkoeffizient(m, n);
		assertEquals("binomialkoeffizient(" + m + "," + n + ")", expected, actual, delta);
	}

	@Test(timeout = 666)
	public void pubTest_binomialkoeffizient_small() {
		test_binomialkoeffizient(1, 1, 1, 1e-12);
		test_binomialkoeffizient(1, 2, 2, 1e-12);
		test_binomialkoeffizient(6, 49, 13983816, 1e-12);
	}

	protected static final void test_gewinnchance(int m, int n, double expected, double delta) {
		double actual = Lotterie.gewinnchance(m, n);
		assertEquals("gewinnchance(" + m + "," + n + ")", expected, actual, delta);
	}

	@Test(timeout = 666)
	public void pubTest_gewinnchance_small() {
		test_gewinnchance(1, 2, 0.5, 1e-7);
		test_gewinnchance(6, 49, 7.151123842018516262E-8, 1e-7);
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