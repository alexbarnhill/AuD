import static org.junit.Assert.*;
import org.junit.*;

public class UnknownPublicTest {
	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest_unknown_133() {
		int result = Unknown.unknown(133);
		assertEquals("The value returned by the method is wrong.", 3, result);
	}
	
	@Test
	public void pubTest_unknown_10000() {
		int result = Unknown.unknown(10000);
		assertEquals("The value returned by the method is wrong.", 5, result);
	}
	
	@Test
	public void pubTest_unknown_5() {
		int result = Unknown.unknown(5);
		assertEquals("The value returned by the method is wrong.", 1, result);
	}

	@Test
	public void pubTest_unknown_negative5() {
		int result = Unknown.unknown(-5);
		assertEquals("The value returned by the method is wrong.", 1, result);
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