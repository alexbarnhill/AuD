import static org.junit.Assert.*;
import org.junit.*;

public class FooBarBazQuxPublicTest {
	// ========== simulate an entire program execution (because damn junit does not always reset static attributes...) ==========
	// don't try to understand!
	private static FooBarBazQux fbbq1, fbbq2;
	private static long numberOfInst;
	private static int resultOfFoo;
	private static char resultOfBar;
	private static double resultOfBaz;
	private static String resultOfQux;
	static {
		fbbq1 = new FooBarBazQux();
		fbbq2 = new FooBarBazQux("I love AuD!");
		numberOfInst = FooBarBazQux.getInstantiations();
		resultOfFoo = fbbq2.foo();
		resultOfFoo = fbbq2.foo();
		resultOfFoo = fbbq1.foo();
		resultOfBar = FooBarBazQux.bar();
		resultOfBaz = FooBarBazQux.baz(4711.0815d);
		resultOfQux = fbbq1.qux("I hate ", "!");
	}

	// ========== UML2Java ==========
	@Test(timeout = 666)
	public void pubTest_UML2Java() {
		assertNotEquals('\uCAFE', resultOfFoo);
		assertNotEquals('\uCAFE', resultOfBar);
		assertNotEquals(Double.NaN, resultOfBaz);
		assertNotNull(resultOfQux);
	}

	// ========== getInstantiations ==========
	@Test(timeout = 666)
	public void pubTest_getInstantiations() {
		assertEquals(2, numberOfInst);
	}

	// ========== getCallsTo ==========
	@Test(timeout = 666)
	public void pubTest_getCallsTo() {
		long actual = fbbq2.getCallsTo("foo");
		assertEquals(2, actual);
		assertEquals(1, fbbq1.getCallsTo("foo"));
		assertEquals(0, fbbq2.getCallsTo("qux"));
		assertEquals(-1, fbbq1.getCallsTo("AuD"));
	}

	// ========== getTotalCallsTo ==========
	@Test(timeout = 666)
	public void pubTest_getTotalCallsTo() {
		long actual = FooBarBazQux.getTotalCallsTo("foo");
		assertEquals(3, actual);
	}

	// ========== main ==========
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}