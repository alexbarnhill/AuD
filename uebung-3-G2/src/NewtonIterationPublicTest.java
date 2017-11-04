import static org.junit.Assert.*;
import org.junit.*;

public class NewtonIterationPublicTest {
	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest_power() {
		double actual = NewtonIteration.power(4711.0815d, 42);
		assertEquals("(4711.0815d, 42)", 1.866663057070941562e154, actual, 1e140);
	}
	
	@Test(timeout = 666)
	public void pubTest_power2() {
		double actual = NewtonIteration.power(2.0, 2);
		assertEquals("(2, 4)", 4, actual, 1e140);
	}

	@Test(timeout = 666)
	public void pubTest_fun() {
		double actual = NewtonIteration.fun(0.815d, 42, -0.665814382223034268d);
		assertEquals("(0.815d, 42, -0.665814382223034268d)", 0.666d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_funDeriv() {
		double actual = NewtonIteration.funDeriv(0.815d, 42, -0.665814382223034268d);
		assertEquals("(0.815d, 42, -0.665814382223034268d)", 0.009565578690258567d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_newtonStep() {
		double actual = NewtonIteration.newtonStep(47.11d, 42, 815d);
		assertEquals("(47.11d, 42, 815d)", 795.5952380952381d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_approxRoot() {
		final double EPSILON = 0.000001;
		double actual;
		actual = NewtonIteration.approxRoot(47.11, 1, EPSILON);
		assertEquals("(47.11, 1, EPSILON)", 47.11, actual, 1e-12);
		actual = NewtonIteration.approxRoot(47.11, 42, EPSILON);
		assertEquals("(47.11, 42, EPSILON)", 1.0960642842334902, actual, 1e-12);
		actual = NewtonIteration.approxRoot(47.11, 815, EPSILON);
		assertEquals("(47.11, 815, EPSILON)", 1.0047381655938923, actual, 1e-12);
		actual = NewtonIteration.approxRoot(4095, 4, EPSILON);
		assertEquals("(4095, 4, EPSILON)", 7.999511674040149, actual, 1e-12);
		actual = NewtonIteration.approxRoot(4096, 4, EPSILON);
		assertEquals("(4096, 4, EPSILON)", 8.0, actual, 1e-12);
		actual = NewtonIteration.approxRoot(4097, 4, EPSILON);
		assertEquals("(4097, 4, EPSILON)", 8.000488236552881, actual, 1e-12);
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