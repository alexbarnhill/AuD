import static org.junit.Assert.*;
import org.junit.*;

public class PhysicsPublicTest {
	// ========== PUBLIC MAIN TEST ==========
	@Test(timeout = 666)
	public void pubTest_computeP_normConditions() {
		double expected = PhysicsConstants.NORMPRESSURE;
		double actual = Physics.computeP(PhysicsConstants.NORMVOLUME, PhysicsConstants.NORMTEMPERATURE);
		assertEquals(expected, actual, 0.2);
	}

	@Test(timeout = 666)
	public void pubTest_computeV_normConditions() {
		double expected = PhysicsConstants.NORMVOLUME;
		double actual = Physics.computeV(PhysicsConstants.NORMPRESSURE, PhysicsConstants.NORMTEMPERATURE);
		assertEquals(expected, actual, 1E-7);
	}

	@Test(timeout = 666)
	public void pubTest_computeT_normConditions() {
		double expected = PhysicsConstants.NORMTEMPERATURE;
		double actual = Physics.computeT(PhysicsConstants.NORMPRESSURE, PhysicsConstants.NORMVOLUME);
		assertEquals(expected, actual, 0.002);
	}

	@Test(timeout = 666)
	public void pubTest_computeDeltaPisochore() {
		double expected = PhysicsConstants.NORMPRESSURE;
		double actual = Physics.computeDeltaPisochore(PhysicsConstants.NORMVOLUME, PhysicsConstants.NORMTEMPERATURE);
		assertEquals(expected, actual, 0.2);
	}

	@Test(timeout = 666)
	public void pubTest_computeDeltaPisotherm() {
		double expected = -PhysicsConstants.NORMPRESSURE / 2;
		double actual = Physics.computeDeltaPisotherm(PhysicsConstants.NORMVOLUME, PhysicsConstants.NORMTEMPERATURE, PhysicsConstants.NORMVOLUME);
		assertEquals(expected, actual, 0.2);
	}

	@Test(timeout = 666)
	public void pubTest_computeAverageSpeed_hotOxygen() {
		double molarMass_Oxygen = 0.0319988; // kg/mol
		double expected = 451.54529119983204; // m/s
		double actual = Physics.computeAverageSpeed(PhysicsConstants.NORMTEMPERATURE + 35, molarMass_Oxygen);
		double expectedSimple = expected * Math.sqrt(3 * Math.PI / 8);
		assertTrue(Math.abs(expected - actual) < 1E-4 || Math.abs(expectedSimple - actual) < 1E-4);
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
