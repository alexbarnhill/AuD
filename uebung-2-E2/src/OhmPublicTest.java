import static org.junit.Assert.*;
import org.junit.*;

public class OhmPublicTest {
	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest_Ohm_voltage() {
		double actual = Ohm.voltage(6_666.0815d, 10.0815d); // ca. 7 kOhm, ca. 10 A
		assertEquals(67204.10064225d, actual, 1e-10);
	}

	@Test(timeout = 666)
	public void pubTest_Ohm_current() {
		double actual = Ohm.current(67204.10064225d, 10.0815d); // ca. 67 kV, ca. 10 A
		assertEquals(6_666.0815d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_Ohm_resistance() {
		double actual = Ohm.resistance(67204.10064225d, 6_666.0815d); // ca. 67 kV, ca. 7 kOhm
		assertEquals(10.0815d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_Ohm_resistanceSerialConnection2() {
		double actual = Ohm.resistanceSerialConnection2(4711.0815d, 42.666d);
		assertEquals(4753.7475d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_Ohm_resistanceParallelConnection2() {
		double actual = Ohm.resistanceParallelConnection2(4711.0815d, 42.666d);
		assertEquals(42.283062631955104894d, actual, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_Ohm_resistanceSerialConnectionN() {
		double[] input1 = { 4711.0815d, 42.666d };
		double actual1 = Ohm.resistanceSerialConnectionN(input1);
		assertEquals(4753.7475d, actual1, 1e-12);
		double[] input2 = { 4711.0815d, 42.666d, 666.0815d, 2017.10d };
		double actual2 = Ohm.resistanceSerialConnectionN(input2);
		assertEquals(7436.929d, actual2, 1e-12);
	}

	@Test(timeout = 666)
	public void pubTest_Ohm_resistanceParallelConnectionN() {
		double[] input1 = { 4711.0815d, 42.666d };
		double actual1 = Ohm.resistanceParallelConnectionN(input1);
		assertEquals(42.283062631955104894d, actual1, 1e-12);
		double[] input2 = { 4711.0815d, 42.666d, 666.0815d, 2017.10d };
		double actual2 = Ohm.resistanceParallelConnectionN(input2);
		assertEquals(38.99059475623387422d, actual2, 1e-12);
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