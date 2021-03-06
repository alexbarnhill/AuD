import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.*;

public class PolyPublicTest {

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
	private static final Nat n42 = Nat.add(Nat.mul(four, ten), two);

	// ========== define some Ints... ==========
	private static final Int minusOne = Int.nat2int(Sign.minus(), one);
	private static final Int minusTwo = Int.nat2int(Sign.minus(), two);
	private static final Int plusOne = Int.nat2int(Sign.plus(), one);
	private static final Int plusTwo = Int.add(plusOne, plusOne);
	private static final Int plusZero = Int.nat2int(Sign.plus(), zero);
	private static final Int plusSix = Int.nat2int(Sign.plus(), six);
	private static final Int minusSix = Int.nat2int(Sign.minus(), six);
	private static final Int minusTwelve = Int.nat2int(Sign.minus(), Nat.add(six, six));
	private static final Int plusTwelve = Int.uminus(minusTwelve);
	private static final Int plusEighteen = Int.add(plusSix, plusTwelve);
	private static final Int plusSixteen = Int.sub(plusEighteen, plusTwo);
	
	private static final Int plusTwenty = Int.add(plusEighteen, plusTwo);
	private static final Int minusTen = Int.add(minusTwelve, plusTwo);
	private static final Int plusTen = Int.uminus(minusTen);
	private static final Int minusFour = Int.add(minusTwo, minusTwo);
	private static final Int plusFour = Int.uminus(minusFour);
	private static final Int minus42 = Int.nat2int(Sign.minus(), n42);
	private static final Int minusSixty = Int.nat2int(Sign.minus(), Nat.mul(six, ten));
	private static final Int plusForty = Int.add(plusTwenty, plusTwenty);
	private static final Int plusFortyTwo = Int.add(plusForty, plusTwo);
	private static final Int plusEighty = Int.mul(plusForty, plusTwo);
	private static final Int plusNinety = Int.add(plusEighty, plusTen);
	private static final Int plusThirtySix = Int.add(plusTwenty, plusSixteen);
	private static final Int plusThirtyFour = Int.sub(plusThirtySix, plusTwo);
	private static final Int minusFourteen = Int.uminus(Int.add(plusTen, plusFour));
	private static final Int minusTwentyEight = Int.mul(minusFourteen, plusTwo);
	private static final Int minusThirtyTwo = Int.add(Int.uminus(plusThirtyFour), plusTwo);
	private static final Int minusSixtyFour = Int.mul(minusThirtyTwo, plusTwo);
	private static final Int minusSixteen = Int.add(minusFourteen, minusTwo);

	// ========== check intestines of Poly... ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	@Test(timeout = 666)
	public void pubTest_intestines() {
		Class<Nat> natClass = Nat.class;
		Class<Int> intClass = Int.class;
		Class<Poly> polyClass = Poly.class;
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal super class in Poly.", Object.class, polyClass.getSuperclass());
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal interfaces in Poly.", 0, polyClass.getInterfaces().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner annotations in Poly.", 0, polyClass.getDeclaredAnnotations().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner classes in Poly.", 0, polyClass.getDeclaredClasses().length);
		Field[] polyFields = polyClass.getDeclaredFields();
		for (Field field : polyFields) {
			if (!(field.getName().equals("$assertionsDisabled") && field.getType() == boolean.class)) { // damn java hack
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute visibility in Poly (field: " + field + ").", Modifier.isPrivate(field.getModifiers()));
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute modifier in Poly (field: " + field + ").", Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()));
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute type in Poly (field: " + field + ").", field.getType() == intClass || field.getType() == polyClass);
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute name in Poly (field: " + field + ").", field.getName().equals("a") || field.getName().equals("p"));
			}
		}
		Constructor<?>[] polyCons = polyClass.getDeclaredConstructors();
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong number of constructors in Poly.", 2, polyCons.length);
		for (Constructor<?> cons : polyCons) {
			if (Modifier.isPrivate(cons.getModifiers())) {
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal constructor visibility in Poly (constructor: " + cons + ").", Modifier.isPrivate(cons.getModifiers()));
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong number of arguments for constructor in Poly.", 2, cons.getParameterTypes().length);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for constructor in Poly.", polyClass, cons.getParameterTypes()[0]);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for constructor in Poly.", intClass, cons.getParameterTypes()[1]);
			} else {
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal constructor visibility in Poly (constructor: " + cons + ").", Modifier.isPublic(cons.getModifiers()));
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong number of arguments for constructor in Poly.", 1, cons.getParameterTypes().length);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for constructor in Poly.", intClass, cons.getParameterTypes()[0]);
			}
		}
		Method[] polyMethods = polyClass.getDeclaredMethods();
		assertEquals("Found wrong number of methods in Poly.", 4, polyMethods.length);
		for (Method method : polyMethods) {
			assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method visibility in Poly (method: " + method + ").", Modifier.isPublic(method.getModifiers()));
			switch (method.getName()) {
			case "horny":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Poly (method: " + method + ").", !Modifier.isStatic(method.getModifiers()));
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal return type for method in Poly (method: " + method + ").", polyClass, method.getReturnType());
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of arguments for method in Poly (method: " + method + ").", 1, method.getParameterTypes().length);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for method in Poly (method: " + method + ").", intClass, method.getParameterTypes()[0]);
				break;
			case "degree":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Poly (method: " + method + ").", !Modifier.isStatic(method.getModifiers()));
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal return type for method in Poly (method: " + method + ").", natClass, method.getReturnType());
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of arguments for method in Poly (method: " + method + ").", 0, method.getParameterTypes().length);
				break;
			case "eval":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Poly (method: " + method + ").", !Modifier.isStatic(method.getModifiers()));
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal return type for method in Poly (method: " + method + ").", intClass, method.getReturnType());
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of arguments for method in Poly (method: " + method + ").", 1, method.getParameterTypes().length);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for method in Poly (method: " + method + ").", intClass, method.getParameterTypes()[0]);
				break;
			case "add":
				assertTrue("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method modifier in Poly (method: " + method + ").", Modifier.isStatic(method.getModifiers()));
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal return type for method in Poly (method: " + method + ").", polyClass, method.getReturnType());
				assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of arguments for method in Poly (method: " + method + ").", 2, method.getParameterTypes().length);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for method in Poly (method: " + method + ").", polyClass, method.getParameterTypes()[0]);
				assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found wrong type of arguments for method in Poly (method: " + method + ").", polyClass, method.getParameterTypes()[1]);
				break;
			default:
				fail("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal method in Poly: " + method);
			}
		}
	}

	// ========== PUBLIC MAIN TEST ==========
	@Test(timeout = 666)
	public void pubTest_degree() {
		Poly poly = new Poly(plusSix).horny(minusSix).horny(plusSix).horny(minus42); // actual = 6x^3 - 6x^2 + 6x - 42
		Nat actual = poly.degree();
		PolyPublicTest.assertSameNat("You failed: degree(6x^3 - 6x^2 + 6x - 42) == 3", three, actual);
	}

	@Test(timeout = 666)
	public void pubTest_eval() {
		Poly poly = new Poly(plusSix).horny(minusSix).horny(plusSix).horny(minus42); // actual = 6x^3 - 6x^2 + 6x - 42
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^3 - 6x^2 + 6x - 42)(-1) == -*60*; Your result: " + actual, minusSixty, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_zero() {
		Poly poly = new Poly(plusSix); // actual = 6
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6)(-1) == +*6*", plusSix, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_one() {
		Poly poly = new Poly(plusSix).horny(plusSix); // actual = 6x + 6;
		Int actual = poly.eval(plusOne);
		PolyPublicTest.assertSameInt("You failed: (6x + 6)(1) == *12*; Your result: " + actual, plusTwelve, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_one_2() {
		Poly poly = new Poly(plusSix).horny(plusSix); // actual = 6x + 6;
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x + 6)(-1) == *0*; Your result: " + actual, plusZero, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_one_3() {
		Poly poly = new Poly(plusSix).horny(plusSix); // actual = 6x + 6;
		Int actual = poly.eval(minusTwo);
		PolyPublicTest.assertSameInt("You failed: (6x + 6)(-2) == *-6*; Your result: " + actual, minusSix, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_one_4() {
		Poly poly = new Poly(plusSix).horny(minusFour); // actual = 6x - 4;
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x - 4)(-1) == *-10*; Your result: " + actual, minusTen, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_two() {
		Poly poly = new Poly(plusSix).horny(plusSix).horny(plusSix); // actual = 6x^2 + 6x + 6;
		Int actual = poly.eval(plusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^2 + 6x + 6)(1) == *18*; Your result: " + actual, plusEighteen, actual);
	}
	
	@Test
	public void pubTest_eval_four() {
		Poly poly = new Poly(plusSix).horny(minusOne).horny(minusTen).horny(minusFour).horny(plusEighteen); // actual = 6x^4 - x^3 -10x^2 - 4x + 18;
		Int actual = poly.eval(minusTwo);
		PolyPublicTest.assertSameInt("You failed: (6x^4 - x^3 - 4x^2 + 18x - 12)(-2) == *40*; Your result: " + actual, plusNinety, actual);
	}
	
	@Test(timeout = 666)
	public void pubTest_eval_two_2() {
		Poly poly = new Poly(plusSix).horny(plusSix).horny(plusSix); // actual = 6x^2 + 6x + 6;
		Int actual = poly.eval(plusTwo);
		PolyPublicTest.assertSameInt("You failed: (6x^2 + 6x + 6)(1) == *40*; Your result: " + actual, plusFortyTwo, actual);
	}

	@Test
	public void pubTest_add() {
		Poly p1 = new Poly(plusSix).horny(minusSix).horny(plusSix).horny(minus42); // p1 = 6x^3 - 6x^2 + 6x - 42
		Poly p2 = new Poly(plusSix).horny(plusZero).horny(Int.uminus(minus42)); // p2 = 6x^2 + 42
		Poly poly = Poly.add(p1, p2); // actual = 6x^3 + 6x
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^3 + 6x)(-1) == -12", minusTwelve, actual);
		Int actualImmutableCheck = p1.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^3 - 6x^2 + 6x - 42)(-1) == -60", minusSixty, actualImmutableCheck);
	}
	
	@Test
	public void pubTest_add_easy() {
		Poly p1 = new Poly(plusSix).horny(minusSix).horny(plusSix); // p1 = 6x^2 - 6x + 6x
		Poly poly = Poly.add(p1, p1); // actual = 12x^2 - 12x + 12
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (12x^2 - 12x + 12)(-1) == 36", plusThirtySix, actual);
		Int actualImmutableCheck = p1.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^2 - 6x + 6)(-1) == +18", plusEighteen, actualImmutableCheck);
	}
	
	@Test
	public void pubTest_add_harder() {
		Poly p1 = new Poly(plusSix).horny(minusSix).horny(plusTwelve).horny(minusTwo).horny(minusFour).horny(minus42); // p1 = 6x^5 - 6x^4 + 12x^3 - 2x^2 - 4x - 42
		Poly p2 = new Poly(plusSix).horny(plusZero).horny(Int.uminus(minus42)); // p2 = 6x^2 + 0x + 42
		Poly poly = Poly.add(p1, p2); // actual = 6x^5 - 6x^4 + 12x^3 + 4x^2 - 4x + 0;
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^5 - 6x^4 + 12x^3 + 4x^2 -4x + 0)(-1) == -16; Your result = " + actual + " Expected: " + minusSixteen, minusSixteen, actual);
		Int actualImmutableCheck = p1.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^5 - 6x^4 + 12x^3 - 2x^2 - 4x - 42)(-1) == -64", minusSixtyFour, actualImmutableCheck);
	}
	
	@Test(timeout = 666)
	public void pubTest_add_rev() {
		Poly p1 = new Poly(plusSix).horny(minusSix).horny(plusSix).horny(minus42); // p1 = 6x^3 - 6x^2 + 6x - 42
		Poly p2 = new Poly(plusSix).horny(plusZero).horny(Int.uminus(minus42)); // p2 = 6x^2 + 42
		Poly poly = Poly.add(p2, p1); // actual = 6x^3 + 6x
		Int actual = poly.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^3 + 6x)(-1) == -12", minusTwelve, actual);
		Int actualImmutableCheck = p1.eval(minusOne);
		PolyPublicTest.assertSameInt("You failed: (6x^3 - 6x^2 + 6x - 42)(-1) == -60", minusSixty, actualImmutableCheck);
	}

	// ========== HELPER ==========
	protected static final void assertSameNat(String message, Nat expected, Nat actual) {
		assertNotNull(message, expected);
		assertNotNull(message, actual);
		assertTrue(message, Nat.zero() == Nat.sub(actual, expected));
		assertTrue(message, Nat.zero() == Nat.sub(expected, actual));
	}

	protected static final void assertSameInt(String message, Int expected, Int actual) {
		assertNotNull(message, expected);
		assertNotNull(message, actual);
		assertSame(message, Int.sign(expected), Int.sign(actual));
		assertSameNat(message, Int.nat(expected), Int.nat(actual));
	}
}