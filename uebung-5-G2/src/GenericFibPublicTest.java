import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class GenericFibPublicTest {
	// ========== SYSTEM ==========
	public static final String CLASS_NAME = "GenericFib";
	public static final String METHOD_NAME_fibNaiveRec = "fibNaiveRec";
	public static final String METHOD_NAME_fibDP = "fibDP";
	public static final String METHOD_NAME_fibDvE = "fibDvE";
	public static final String METHOD_NAME_fibDvEHelper = "fibDvEHelper";

	// ========== Big Brother ==========
	protected static final class BigBrother implements GenericFibKontrolle {
		protected LinkedList<Integer> fibNaiveRecLog_stack = new LinkedList<>();
		protected LinkedList<Integer> fibNaiveRecLog_n = new LinkedList<>();
		protected LinkedList<Integer> fibDPLog_stack = new LinkedList<>();
		protected LinkedList<Integer> fibDPLog_n = new LinkedList<>();
		protected LinkedList<Integer> fibDvELog_stack = new LinkedList<>();
		protected LinkedList<Integer> fibDvELog_n = new LinkedList<>();
		protected LinkedList<Integer> fibDvELog_i = new LinkedList<>();
		protected LinkedList<Double> fibDvELog_mems = new LinkedList<>();

		@Override
		public void fibNaiveRecLog(GenericFibKontrolle gfk, double a, double b, int c, int n) {
			fibNaiveRecLog_n.add(n);
			scanStackTrace();
		}

		@Override
		public void fibDPLog(GenericFibKontrolle gfk, double a, double b, int c, int n) {
			fibDPLog_n.add(n);
			scanStackTrace();
		}

		@Override
		public void fibDvELog(GenericFibKontrolle gfk, double a, double b, int c, int n, int i, double mem1, double mem2, double mem3) {
			fibDvELog_n.add(n);
			fibDvELog_i.add(i);
			fibDvELog_mems.add(mem1);
			fibDvELog_mems.add(mem2);
			fibDvELog_mems.add(mem3);
			scanStackTrace();
		}

		private void scanStackTrace() {
			int fibNaiveRecStackCount = 0, fibDPStackCount = 0, fibDvEStackCount = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(CLASS_NAME)) {
					if (ste.getMethodName().equals(METHOD_NAME_fibNaiveRec)) {
						fibNaiveRecStackCount++;
					} else if (ste.getMethodName().equals(METHOD_NAME_fibDP)) {
						fibDPStackCount++;
					} else if (ste.getMethodName().equals(METHOD_NAME_fibDvEHelper)) {
						fibDvEStackCount++;
					}
				}
			}
			if (fibNaiveRecStackCount > 0) {
				fibNaiveRecLog_stack.add(fibNaiveRecStackCount);
			}
			if (fibDPStackCount > 0) {
				fibDPLog_stack.add(fibDPStackCount);
			}
			if (fibDvEStackCount > 0) {
				fibDvELog_stack.add(fibDvEStackCount);
			}
		}
	}

	// ========== Intestines ==========
	private static final Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static final Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static final Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}

	private static final void check_GenericFib_intestines() {
		Class<?> clazz = GenericFib.class;
		assertFalse(clazz.getName() + " ist faelschlicherweise eine Annotation.", clazz.isAnnotation());
		assertFalse(clazz.getName() + " ist faelschlicherweise ein Enum.", clazz.isEnum());
		assertFalse(clazz.getName() + " ist faelschlicherweise ein Interface.", clazz.isInterface());
		assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz.getName() + " ist faelschlicherweise nicht >public<", Modifier.isPublic(clazz.getModifiers()));
		assertEquals(clazz.getName() + " hat faelschlicherweise innere Annotationen.", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz.getName() + " hat faelschlicherweise innere Klassen.", 0, clazz.getDeclaredClasses().length);
		assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
		assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", Object.class, clazz.getSuperclass());
		Field[] fields = getDeclaredFields(clazz);
		assertEquals("Hat falsche Anzahl an Attributen.", 1, fields.length);
		assertEquals("Hat unerwartete Attribute.", "dp", fields[0].getName());
		assertEquals(clazz.getName() + " hat falsche Anzahl an Konstruktoren.", 1, getDeclaredConstructors(clazz).length); // default cons only!
		assertEquals(clazz.getName() + " hat falsche Anzahl an Methoden.", 5, getDeclaredMethods(clazz).length);
	}

	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest_GenericFib__NAIVE__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_GenericFib_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_GenericFib__DP__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_GenericFib_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_GenericFib__DvE__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_GenericFib_intestines();
	}

	// --------------------------------------------------------------------------------
	protected static final void check_fibNaiveRec(double a, double b, int c, int n, double expected, Integer[] fibNaiveRecLog_stack_expected, Integer[] fibNaiveRecLog_n_expected) {
		GenericFibPublicTest.BigBrother gfk = new GenericFibPublicTest.BigBrother();
		double actual = GenericFib.fibNaiveRec(gfk, a, b, c, n);
		assertEquals(GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibNaiveRec + "(" + a + ", " + b + ", " + c + ", " + n + ")", expected, actual, expected * 1e-7);
		if (fibNaiveRecLog_stack_expected != null) {
			Integer[] fibNaiveRecLog_stack_actual = gfk.fibNaiveRecLog_stack.toArray(new Integer[0]);
			assertArrayEquals("Recursion depth/width in stack trace counter seems wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibNaiveRec + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibNaiveRecLog_stack_expected, fibNaiveRecLog_stack_actual);
		}
		if (fibNaiveRecLog_n_expected != null) {
			Integer[] fibNaiveRecLog_n_actual = gfk.fibNaiveRecLog_n.toArray(new Integer[0]);
			assertArrayEquals("Recursion arguments seem wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibNaiveRec + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibNaiveRecLog_n_expected, fibNaiveRecLog_n_actual);
		}
		assertEquals("YOU CALLED THE WRONG METHOD from " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibNaiveRec + "(" + a + ", " + b + ", " + c + ", " + n + ") - found calls to " + GenericFibPublicTest.METHOD_NAME_fibDP, 0, gfk.fibDPLog_stack.size());
		assertEquals("YOU CALLED THE WRONG METHOD from " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibNaiveRec + "(" + a + ", " + b + ", " + c + ", " + n + ") - found calls to " + GenericFibPublicTest.METHOD_NAME_fibDvE, 0, gfk.fibDvELog_stack.size());
	}

	@Test(timeout = 3666)
	public void pubTest_fibNaiveRec_classicFib_and_exerciseSheetExample() {
		double fn = 0, fnp1 = 1;
		for (int n = 0; n < 16; n++) {
			check_fibNaiveRec(1, 1, 2, n, fn, null, null);
			fnp1 = fn + (fn = fnp1);
		}
		check_fibNaiveRec(1.5, -0.5, 3, 9, 2.515625, null, null);
		check_fibNaiveRec(1.5, -0.5, 3, 10, 2.0859375, null, null);
		check_fibNaiveRec(1.5, -0.5, 3, 11, -1.32421875, null, null);
	}

	@Test(timeout = 3666)
	public void pubTest_fibNaiveRec_classicFib_and_exerciseSheetExample_10__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT_IF_YOU_WANT_POINTS() {
		double expected = 55;
		Integer[] fibNaiveRecLog_stack_expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 9, 9, 7, 8, 9, 9, 8, 6, 7, 8, 9, 9, 8, 7, 8, 8, 5, 6, 7, 8, 9, 9, 8, 7, 8, 8, 6, 7, 8, 8, 7, 4, 5, 6, 7, 8, 9, 9, 8, 7, 8, 8, 6, 7, 8, 8, 7, 5, 6, 7, 8, 8, 7, 6, 7, 7, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 8, 8, 6, 7, 8, 8, 7, 5, 6, 7, 8, 8, 7, 6, 7, 7, 4, 5, 6, 7, 8, 8, 7, 6, 7, 7, 5, 6, 7, 7, 6, 2, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 8, 8, 6, 7, 8, 8, 7, 5, 6, 7, 8, 8, 7, 6, 7, 7, 4, 5, 6, 7, 8, 8, 7, 6, 7, 7, 5, 6, 7, 7, 6, 3, 4, 5, 6, 7, 8, 8, 7, 6, 7, 7, 5, 6, 7, 7, 6, 4, 5, 6, 7, 7, 6, 5, 6, 6 };
		Integer[] fibNaiveRecLog_n_expected = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 4, 3, 2, 1, 0, 1, 2, 1, 0, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 4, 3, 2, 1, 0, 1, 2, 1, 0, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 4, 3, 2, 1, 0, 1, 2, 1, 0, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 8, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 4, 3, 2, 1, 0, 1, 2, 1, 0, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 6, 5, 4, 3, 2, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 1, 4, 3, 2, 1, 0, 1, 2, 1, 0 };
		check_fibNaiveRec(1, 1, 2, 10, expected, fibNaiveRecLog_stack_expected, fibNaiveRecLog_n_expected);
		double expected_ExSheet = -1.869140625;
		check_fibNaiveRec(1.5, -0.5, 2, 10, expected_ExSheet, fibNaiveRecLog_stack_expected, fibNaiveRecLog_n_expected);
	}

	// --------------------------------------------------------------------------------
	protected static final void check_fibDP(double a, double b, int c, int n, double expected, Integer[] fibDPLog_stack_expected, Integer[] fibDPLog_n_expected) {
		GenericFibPublicTest.BigBrother gfk = new GenericFibPublicTest.BigBrother();
		GenericFib.initDP(n);
		double actual = GenericFib.fibDP(gfk, a, b, c, n);
		assertEquals(GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDP + "(" + a + ", " + b + ", " + c + ", " + n + ")", expected, actual, 1e-7);
		if (fibDPLog_stack_expected != null) {
			Integer[] fibDPLog_stack_actual = gfk.fibDPLog_stack.toArray(new Integer[0]);
			assertArrayEquals("Recursion depth/width in stack trace counter seems wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDP + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibDPLog_stack_expected, fibDPLog_stack_actual);
		}
		if (fibDPLog_n_expected != null) {
			Integer[] fibDPLog_n_actual = gfk.fibDPLog_n.toArray(new Integer[0]);
			assertArrayEquals("Recursion arguments seem wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDP + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibDPLog_n_expected, fibDPLog_n_actual);
		}
		assertEquals("YOU CALLED THE WRONG METHOD from " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDP + "(" + a + ", " + b + ", " + c + ", " + n + ") - found calls to " + GenericFibPublicTest.METHOD_NAME_fibNaiveRec, 0, gfk.fibNaiveRecLog_stack.size());
		assertEquals("YOU CALLED THE WRONG METHOD from " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDP + "(" + a + ", " + b + ", " + c + ", " + n + ") - found calls to " + GenericFibPublicTest.METHOD_NAME_fibDvE, 0, gfk.fibDvELog_stack.size());
	}

	@Test(timeout = 2666)
	public void pubTest_fibDP_classicFib() {
		double fn = 0, fnp1 = 1;
		for (int n = 0; n < 100; n++) {
			check_fibDP(1, 1, 2, n, fn, null, null);
			fnp1 = fn + (fn = fnp1);
		}
		check_fibDP(1.5, -0.5, 3, 9, 2.515625, null, null);
		check_fibDP(1.5, -0.5, 3, 10, 2.0859375, null, null);
		check_fibDP(1.5, -0.5, 3, 11, -1.32421875, null, null);
	}

	@Test(timeout = 1666)
	public void pubTest_fibDP_classicFib_64__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT_IF_YOU_WANT_POINTS() {
		double expected = 1.0610209857723E13;
		Integer[] fibDPLog_stack_expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		Integer[] fibDPLog_n_expected = { 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62 };
		check_fibDP(1, 1, 2, 64, expected, fibDPLog_stack_expected, fibDPLog_n_expected);
		double expected_ExSheet = -0.7251037860927677;
		check_fibDP(1.5, -0.5, 2, 64, expected_ExSheet, fibDPLog_stack_expected, fibDPLog_n_expected);
	}

	// --------------------------------------------------------------------------------
	protected static final void check_fibDvE(double a, double b, int c, int n, double expected, Integer[] fibDvELog_stack_expected, Integer[] fibDvELog_n_expected, Integer[] fibDvELog_i_expected, Double[] fibDvELog_mems_expected) {
		GenericFibPublicTest.BigBrother gfk = new GenericFibPublicTest.BigBrother();
		double actual = GenericFib.fibDvE(gfk, a, b, c, n);
		assertEquals(GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ")", expected, actual, expected * 1e-7);
		if (fibDvELog_stack_expected != null) {
			Integer[] fibDvELog_stack_actual = gfk.fibDvELog_stack.toArray(new Integer[0]);
			assertArrayEquals("Recursion depth/width in stack trace counter seems wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibDvELog_stack_expected, fibDvELog_stack_actual);
		}
		if (fibDvELog_n_expected != null) {
			Integer[] fibDvELog_n_actual = gfk.fibDvELog_n.toArray(new Integer[0]);
			assertArrayEquals("Recursion arguments seem wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibDvELog_n_expected, fibDvELog_n_actual);
		}
		if (fibDvELog_i_expected != null) {
			Integer[] fibDvELog_i_actual = gfk.fibDvELog_i.toArray(new Integer[0]);
			assertArrayEquals("Recursion arguments seem wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibDvELog_i_expected, fibDvELog_i_actual);
		}
		if (fibDvELog_mems_expected != null) {
			Double[] fibDvELog_mems_actual = gfk.fibDvELog_mems.toArray(new Double[0]);
			assertArrayEquals("Recursion arguments seem wrong for " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ")", fibDvELog_mems_expected, fibDvELog_mems_actual);
		}
		assertEquals("YOU CALLED THE WRONG METHOD from " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ") - found calls to " + GenericFibPublicTest.METHOD_NAME_fibNaiveRec, 0, gfk.fibNaiveRecLog_stack.size());
		assertEquals("YOU CALLED THE WRONG METHOD from " + GenericFibPublicTest.CLASS_NAME + "." + GenericFibPublicTest.METHOD_NAME_fibDvE + "(" + a + ", " + b + ", " + c + ", " + n + ") - found calls to " + GenericFibPublicTest.METHOD_NAME_fibDvE, 0, gfk.fibDPLog_stack.size());
	}

	@Test
	public void pubTest_fibDvE_classicFib() {
		double fn = 0, fnp1 = 1;
		for (int n = 0; n < 100; n++) {
			check_fibDvE(1, 1, 2, n, fn, null, null, null, null);
			fnp1 = fn + (fn = fnp1);
		}
		check_fibDvE(1.5, -0.5, 3, 9, 2.515625, null, null, null, null);
		check_fibDvE(1.5, -0.5, 3, 10, 2.0859375, null, null, null, null);
		check_fibDvE(1.5, -0.5, 3, 11, -1.32421875, null, null, null, null);
	}

//	@Test
//	public void pubTest_fibDvE_classicFib_64__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT_IF_YOU_WANT_POINTS() {
//		double expected = 1.0610209857723E13;
//		Integer[] fibDvELog_stack_expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65 };
//		Integer[] fibDvELog_n_expected = { 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64 };
//		Integer[] fibDvELog_i_expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64 };
//		Double[] fibDvELog_mems_expected = { Double.NaN, Double.NaN, Double.NaN, 0.0, Double.NaN, Double.NaN, 1.0, 0.0, Double.NaN, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 3.0, 2.0, 1.0, 5.0, 3.0, 2.0, 8.0, 5.0, 3.0, 13.0, 8.0, 5.0, 21.0, 13.0, 8.0, 34.0, 21.0, 13.0, 55.0, 34.0, 21.0, 89.0, 55.0, 34.0, 144.0, 89.0, 55.0, 233.0, 144.0, 89.0, 377.0, 233.0, 144.0, 610.0, 377.0, 233.0, 987.0, 610.0, 377.0, 1597.0, 987.0, 610.0, 2584.0, 1597.0, 987.0, 4181.0, 2584.0, 1597.0, 6765.0, 4181.0, 2584.0, 10946.0, 6765.0, 4181.0, 17711.0, 10946.0, 6765.0, 28657.0, 17711.0, 10946.0, 46368.0, 28657.0, 17711.0, 75025.0, 46368.0, 28657.0, 121393.0, 75025.0, 46368.0, 196418.0, 121393.0, 75025.0, 317811.0, 196418.0,
//				121393.0, 514229.0, 317811.0, 196418.0, 832040.0, 514229.0, 317811.0, 1346269.0, 832040.0, 514229.0, 2178309.0, 1346269.0, 832040.0, 3524578.0, 2178309.0, 1346269.0, 5702887.0, 3524578.0, 2178309.0, 9227465.0, 5702887.0, 3524578.0, 1.4930352E7, 9227465.0, 5702887.0, 2.4157817E7, 1.4930352E7, 9227465.0, 3.9088169E7, 2.4157817E7, 1.4930352E7, 6.3245986E7, 3.9088169E7, 2.4157817E7, 1.02334155E8, 6.3245986E7, 3.9088169E7, 1.65580141E8, 1.02334155E8, 6.3245986E7, 2.67914296E8, 1.65580141E8, 1.02334155E8, 4.33494437E8, 2.67914296E8, 1.65580141E8, 7.01408733E8, 4.33494437E8, 2.67914296E8, 1.13490317E9, 7.01408733E8, 4.33494437E8, 1.836311903E9, 1.13490317E9, 7.01408733E8,
//				2.971215073E9, 1.836311903E9, 1.13490317E9, 4.807526976E9, 2.971215073E9, 1.836311903E9, 7.778742049E9, 4.807526976E9, 2.971215073E9, 1.2586269025E10, 7.778742049E9, 4.807526976E9, 2.0365011074E10, 1.2586269025E10, 7.778742049E9, 3.2951280099E10, 2.0365011074E10, 1.2586269025E10, 5.3316291173E10, 3.2951280099E10, 2.0365011074E10, 8.6267571272E10, 5.3316291173E10, 3.2951280099E10, 1.39583862445E11, 8.6267571272E10, 5.3316291173E10, 2.25851433717E11, 1.39583862445E11, 8.6267571272E10, 3.65435296162E11, 2.25851433717E11, 1.39583862445E11, 5.91286729879E11, 3.65435296162E11, 2.25851433717E11, 9.56722026041E11, 5.91286729879E11, 3.65435296162E11, 1.54800875592E12,
//				9.56722026041E11, 5.91286729879E11, 2.504730781961E12, 1.54800875592E12, 9.56722026041E11, 4.052739537881E12, 2.504730781961E12, 1.54800875592E12, 6.557470319842E12, 4.052739537881E12, 2.504730781961E12 };
//		check_fibDvE(1, 1, 2, 64, expected, fibDvELog_stack_expected, fibDvELog_n_expected, fibDvELog_i_expected, fibDvELog_mems_expected);
//		double expected_ExSheet = -0.7251037860927677;
//		check_fibDvE(1.5, -0.5, 2, 64, expected_ExSheet, fibDvELog_stack_expected, fibDvELog_n_expected, null, null);
//	}

	// ========== main ==========
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}