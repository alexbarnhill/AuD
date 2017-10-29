import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class DeclarationsPublicTest {
	@Test(timeout = 666)
	public void pubTest_Declarations_TINY_SMOKE_TEST_ONLY() {
		System.out.println("STARTUP -----");
		System.out.println(Declarations.AUD_EXAM_GRADES);
		assertEquals("Smoke test failed.", -10_13_17_20_23_27_30_33_37_40.43_47e-50, Declarations.AUD_EXAM_GRADES, 11e-47);
		System.out.println("-----");
		System.out.println(Declarations.Tutors.Athanassios);
		System.out.println("-----");
		System.out.println(Arrays.toString(Declarations.lowerCaseLetters()));
		System.out.println("-----");
		System.out.println(">" + Declarations.aFewChars()[2][9] + "<");
		assertEquals("Smoke test failed.", '\t', Declarations.aFewChars()[2][9]);
		System.out.println("-----");
		System.out.println("[" + Declarations.DINGBATS + "]");
		assertEquals("Smoke test failed.", '\t', Declarations.DINGBATS.charAt(23));
		assertEquals("Smoke test failed.", '\n', Declarations.DINGBATS.charAt(Declarations.DINGBATS.length() - 1));
		System.out.println("-----");
		for (String[][] plane : Declarations.theHyperCube()) {
			for (String[] row : plane) {
				System.out.print(Arrays.toString(row));
			}
			System.out.println();
		}
		assertEquals("Smoke test failed.", "112", Declarations.theHyperCube()[1][1][2]);
		assertEquals("Smoke test failed.", "210", Declarations.theHyperCube()[2][1][0]);
		System.out.println("----- SHUTDOWN");
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