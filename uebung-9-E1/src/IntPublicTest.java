import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.*;

public class IntPublicTest {

	// ========== define some Nats... ==========
	private static final Nat zero = Nat.zero();
	private static final Nat one = Nat.succ(zero);
	private static final Nat two = Nat.succ(one);
	private static final Nat three = Nat.succ(two);
	private static final Nat four = Nat.succ(three);
	private static final Nat five = Nat.succ(four);
	private static final Nat six = Nat.succ(five);
	private static final Nat seven = Nat.succ(six);
	private static final Nat eight = Nat.succ(seven);
	private static final Nat nine = Nat.succ(eight);
	private static final Nat ten = Nat.succ(nine);
	private static final Nat n11 = Nat.succ(ten);
	private static final Nat n15 = Nat.mul(three, five);
	private static final Nat n42 = Nat.add(Nat.mul(four, ten), two);
	private static final Nat n47 = Nat.add(Nat.mul(four, ten), seven);
	private static final Nat n58 = Nat.add(Nat.mul(five, ten), eight);
	private static final Nat n517 = Nat.add(Nat.mul(Nat.add(Nat.mul(five, ten), one), ten), seven);
	private static final Nat n666 = Nat.add(Nat.mul(Nat.add(Nat.mul(six, ten), six), ten), six);

	// ========== check intestines of Int... ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	@Test(timeout = 666)
	public void pubTest_intestines() {
		Class<Nat> natClass = Nat.class;
		Class<Sign> signClass = Sign.class;
		Class<Int> intClass = Int.class;
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal super class in Int.", Object.class, intClass.getSuperclass());
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal interfaces in Int.", 0, intClass.getInterfaces().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner annotations in Int.", 0, intClass.getDeclaredAnnotations().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner classes in Int.", 0, intClass.getDeclaredClasses().length);
		Field[] intFields = intClass.getDeclaredFields();
		for (Field field : intFields) {
			if (!(field.getName().equals("$assertionsDisabled") && field.getType() == boolean.class)) { // damn java hack
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute visibility in Int (field: " + field + ").", Modifier.isPrivate(field.getModifiers()));
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute modifier in Int (field: " + field + ").", Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()));
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute type in Int (field: " + field + ").", field.getType() == signClass || field.getType() == natClass);
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute name in Int (field: " + field + ").", field.getName().equals("sign") || field.getName().equals("nat"));
			}
		}
		Constructor<?>[] intCons = intClass.getDeclaredConstructors();
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong number of constructors in Int.", 1, intCons.length);
		assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal constructor visibility in Int (constructor: " + intCons[0] + ").", Modifier.isPrivate(intCons[0].getModifiers()));
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong number of arguments for constructor in Int.", 2, intCons[0].getParameterTypes().length);
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for constructor in Int.", signClass, intCons[0].getParameterTypes()[0]);
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for constructor in Int.", natClass, intCons[0].getParameterTypes()[1]);
		Method[] intMethods = intClass.getDeclaredMethods();
		assertEquals("Found wrong number of methods in Int.", 9, intMethods.length);
		for (Method method : intMethods) {
			assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method visibility in Int (method: " + method + ").", Modifier.isPublic(method.getModifiers()));
			switch (method.getName()) {
			case "nat2int":
			case "sign":
			case "nat":
			case "uminus":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Int (method: " + method + ").", Modifier.isStatic(method.getModifiers()));
				break;
			case "add":
			case "sub":
			case "mul":
			case "div":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Int (method: " + method + ").", Modifier.isStatic(method.getModifiers()));
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal return type for method in Int (method: " + method + ").", intClass, method.getReturnType());
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of arguments for method in Int (method: " + method + ").", 2, method.getParameterTypes().length);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for method in Int (method: " + method + ").", intClass, method.getParameterTypes()[0]);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for method in Int (method: " + method + ").", intClass, method.getParameterTypes()[1]);
				break;
			case "toString":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Int (method: " + method + ").", !Modifier.isStatic(method.getModifiers()));
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal return type for method in Int (method: " + method + ").", String.class, method.getReturnType());
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of arguments for method in Int (method: " + method + ").", 0, method.getParameterTypes().length);
				break;
			default:
				fail("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method in Int: " + method);
			}
		}
	}

	// ========== PUBLIC MAIN TEST ==========
	@Test(timeout = 666)
	public void pubTest_nat2int() {
		Int actual = Int.nat2int(Sign.plus(), n42);
		assertSame("You failed: +42 == (*+*42)", Sign.plus(), Int.sign(actual));
		IntPublicTest.assertSameNat("You failed: +42 == (+*42*)", n42, Int.nat(actual));
	}

	@Test(timeout = 666)
	public void pubTest_uminus() {
		Int actual = Int.uminus(Int.nat2int(Sign.plus(), n42));
		assertSame("You failed: -(42) == (*-*42)", Sign.minus(), Int.sign(actual));
		IntPublicTest.assertSameNat("You failed: -(42) == (-*42*)", n42, Int.nat(actual));
	}

	@Test(timeout = 666)
	public void pubTest_add() {
		Int minus47 = Int.nat2int(Sign.minus(), n47);
		Int minus11 = Int.nat2int(Sign.minus(), n11);
		Int actual = Int.add(minus47, minus11);
		assertSame("You failed: (-47)+(-11) == (*-*58)", Sign.minus(), Int.sign(actual));
		IntPublicTest.assertSameNat("You failed: (-47)+(-11) == (-*58*)", n58, Int.nat(actual));
	}

	@Test(timeout = 666)
	public void pubTest_sub() {
		Int minus47 = Int.nat2int(Sign.minus(), n47);
		Int plus11 = Int.nat2int(Sign.plus(), n11);
		Int actual = Int.sub(minus47, plus11);
		assertSame("You failed: (-47)-(+11) == (*-*58)", Sign.minus(), Int.sign(actual));
		IntPublicTest.assertSameNat("You failed: (-47)-(+11) == (-*58*)", n58, Int.nat(actual));
	}

	@Test(timeout = 666)
	public void pubTest_mul() {
		Int minus47 = Int.nat2int(Sign.minus(), n47);
		Int minus11 = Int.nat2int(Sign.minus(), n11);
		Int actual = Int.mul(minus47, minus11);
		assertSame("You failed: (-47)*(-11) == (*+*58)", Sign.plus(), Int.sign(actual));
		IntPublicTest.assertSameNat("You failed: (-47)*(-11) == (+*58*)", n517, Int.nat(actual));
	}

	@Test(timeout = 666)
	public void pubTest_div() {
		Int plus666 = Int.nat2int(Sign.plus(), n666);
		Int minus42 = Int.nat2int(Sign.minus(), n42);
		Int actual = Int.div(plus666, minus42);
		assertSame("You failed: (+666)/(-42) == (*-*15)", Sign.minus(), Int.sign(actual));
		IntPublicTest.assertSameNat("You failed: (+666)/(-42) == (-*15*)", n15, Int.nat(actual));
	}

	protected static final void assertSameNat(String message, Nat expected, Nat actual) {
		assertTrue(message, Nat.zero() == Nat.sub(actual, expected));
		assertTrue(message, Nat.zero() == Nat.sub(expected, actual));
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar $(ls * | grep PublicTest.class | sed s/.class//)

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}