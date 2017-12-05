import static org.junit.Assert.*;
import org.junit.*;

public class TurboliftPublicTest {
	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest__TurboliftSystem__Class() {
		try {
			Class<?> clazz = Class.forName("TurboliftSystem");
			assertNotNull("This should not happen!", clazz);
		} catch (ClassNotFoundException e) {
			fail("Klasse TurboliftSystem nicht gefunden.");
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