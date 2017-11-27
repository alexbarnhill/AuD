import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class Matrix3x3PublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME_get_set = "get, set";
	public static final String EX_NAME_toIdentity = "toIdentity";
	public static final String EX_NAME_deepCopyFrom = "deepCopyFrom";
	public static final String EX_NAME_setRotXYZ = "setRotX , setRotY, setRotZ";
	public static final String EX_NAME_immutMul = "immutMul";
	public static final String EX_NAME_mutMul = "mutMul";
	public static final String EX_NAME_mulVec = "mulVec";
	public static final String CLASS_NAME = "Matrix3x3";
	public static final String METHOD_NAME_get = CLASS_NAME + "." + "get";

	// ========== TEST DATA ==========
	private static final double DELTA_FAC = 1e-9;
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);

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

	private static final void check_Matrix3x3_intestines() {
		Class<?> clazz = Matrix3x3.class;
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
		assertTrue("Hat falschen Datentyp fuer das Attribut - sollte ein Array sein.", fields[0].getType().isArray());
		assertEquals("Hat falschen Datentyp fuer das Attribut.", Vector3D.class, fields[0].getType().getComponentType());
		assertEquals(clazz.getName() + " hat falsche Anzahl an Konstruktoren.", 1, getDeclaredConstructors(clazz).length); // default cons only!
		assertEquals(clazz.getName() + " hat falsche Anzahl an Methoden.", 10, getDeclaredMethods(clazz).length);
	}

	// ========== PUBLIC TESTS ==========
	@Test(timeout = 666)
	public void pubTest_Matrix3x3___get_set__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_Matrix3x3__toIdentity__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_Matrix3x3__deepCopyFrom__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_Matrix3x3__setRotXYZ__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_Matrix3x3__immutMul__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_Matrix3x3__mutMul__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	@Test(timeout = 666)
	public void pubTest_Matrix3x3__mulVec__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_Matrix3x3_intestines();
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__cons_get_set() {
		Matrix3x3 m = new Matrix3x3();
		HashMap<String, Double> mem = new HashMap<>();
		int x, y;
		double expected, actual;
		for (int pass = 0; pass < 42; pass++) {
			// A - randomly modify and check m
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m.set(x, y, expected);
			mem.put("(" + x + ", " + y + ")", expected);
			actual = m.get(x, y);
			assertEquals("A - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			// B - randomly check m
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = mem.containsKey("(" + x + ", " + y + ")") ? mem.get("(" + x + ", " + y + ")") : 0d;
			actual = m.get(x, y);
			assertEquals("B - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__toIdentity() {
		Matrix3x3 m = new Matrix3x3();
		int x, y;
		double expected, actual;
		for (int pass = 0; pass < 42; pass++) {
			// A - randomly modify and check m
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m.set(x, y, expected);
			actual = m.get(x, y);
			assertEquals("A - m: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
		m.toIdentity();
		for (int pass = 0; pass < 42; pass++) {
			// B - randomly check m
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = x == y ? 1 : 0;
			actual = m.get(x, y);
			assertEquals("B - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__deepCopyFrom() {
		Matrix3x3 m1 = new Matrix3x3(), m2 = new Matrix3x3();
		HashMap<String, Double> mem1 = new HashMap<>(), mem2 = new HashMap<>();
		int x, y;
		double expected, actual;
		for (int pass = 0; pass < 42; pass++) {
			// A - randomly modify and check m1
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m1.set(x, y, expected);
			mem1.put("(" + x + ", " + y + ")", expected);
			actual = m1.get(x, y);
			assertEquals("A - m1: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			// A - randomly modify and check m2
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m2.set(x, y, expected);
			mem2.put("(" + x + ", " + y + ")", expected);
			actual = m2.get(x, y);
			assertEquals("A - m2: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
		m1.deepCopyFrom(m2);
		mem1 = new HashMap<>(mem2);
		for (int pass = 0; pass < 42; pass++) {
			// B - randomly check m1
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = mem1.containsKey("(" + x + ", " + y + ")") ? mem1.get("(" + x + ", " + y + ")") : 0d;
			actual = m1.get(x, y);
			assertEquals("B - m1: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			// B - randomly modify and check m2
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m2.set(x, y, expected);
			mem2.put("(" + x + ", " + y + ")", expected);
			actual = m2.get(x, y);
			assertEquals("B - m2: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__setRotXYZ() {
		Matrix3x3 m = new Matrix3x3();
		int x, y;
		double expected, actual;
		// ----------------------------------------
		m.setRotX(3.14);
		x = y = 0;
		expected = 1;
		actual = m.get(x, y);
		assertEquals("A - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		x = y = 2;
		expected = -0.999998731727539545d;
		actual = m.get(x, y);
		assertEquals("B - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		// ----------------------------------------
		m.setRotY(3.14);
		x = y = 1;
		expected = 1;
		actual = m.get(x, y);
		assertEquals("A - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		x = y = 2;
		expected = -0.999998731727539545d;
		actual = m.get(x, y);
		assertEquals("B - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		// ----------------------------------------
		m.setRotZ(3.14);
		x = y = 2;
		expected = 1;
		actual = m.get(x, y);
		assertEquals("A - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		x = y = 0;
		expected = -0.999998731727539545d;
		actual = m.get(x, y);
		assertEquals("B - m: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__immutMul() {
		Matrix3x3 m1 = new Matrix3x3(), m2 = new Matrix3x3(), m3;
		HashMap<String, Double> mem1 = new HashMap<>(), mem2 = new HashMap<>(), mem3;
		int x, y;
		double expected, actual;
		double fac = RND.nextDouble() * 666 + 42;
		for (int pass = 0; pass < 42; pass++) {
			// A - randomly modify and check m1
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE / fac;
			m1.set(x, y, expected);
			mem1.put("(" + x + ", " + y + ")", expected);
			actual = m1.get(x, y);
			assertEquals("A - m1: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
		m2.toIdentity();
		m2.set(0, 0, fac);
		m2.set(1, 1, fac);
		m2.set(2, 2, fac);
		m3 = RND.nextBoolean() ? m1.immutMul(m2) : m2.immutMul(m1);
		mem3 = new HashMap<>(mem1);
		for (int pass = 0; pass < 42; pass++) {
			// B - randomly check m3
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = fac * (mem3.containsKey("(" + x + ", " + y + ")") ? mem3.get("(" + x + ", " + y + ")") : 0d);
			actual = m3.get(x, y);
			assertEquals("B - m3: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			// C - randomly modify and check m1 and m2
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m1.set(x, y, expected);
			mem1.put("(" + x + ", " + y + ")", expected);
			actual = m1.get(x, y);
			assertEquals("C - m1: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m2.set(x, y, expected);
			mem2.put("(" + x + ", " + y + ")", expected);
			actual = m2.get(x, y);
			assertEquals("C - m2: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__mutMul() {
		Matrix3x3 m1 = new Matrix3x3(), m2 = new Matrix3x3();
		HashMap<String, Double> mem1 = new HashMap<>(), mem2 = new HashMap<>();
		int x, y;
		double expected, actual;
		double fac = RND.nextDouble() * 666 + 42;
		for (int pass = 0; pass < 42; pass++) {
			// A - randomly modify and check m1
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE / fac;
			m1.set(x, y, expected);
			mem1.put("(" + x + ", " + y + ")", expected);
			actual = m1.get(x, y);
			assertEquals("A - m1: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
		m2.toIdentity();
		m2.set(0, 0, fac);
		m2.set(1, 1, fac);
		m2.set(2, 2, fac);
		m1.mutMul(m2);
		for (int pass = 0; pass < 42; pass++) {
			// B - randomly check m1
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = fac * (mem1.containsKey("(" + x + ", " + y + ")") ? mem1.get("(" + x + ", " + y + ")") : 0d);
			actual = m1.get(x, y);
			assertEquals("B - m1: " + METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
		for (int pass = 0; pass < 42; pass++) {
			// B - randomly modify and check m1 and m2
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m1.set(x, y, expected);
			mem1.put("(" + x + ", " + y + ")", expected);
			actual = m1.get(x, y);
			assertEquals("B - m1: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			x = RND.nextInt(3);
			y = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE;
			m2.set(x, y, expected);
			mem2.put("(" + x + ", " + y + ")", expected);
			actual = m2.get(x, y);
			assertEquals("B - m2: " + Matrix3x3PublicTest.METHOD_NAME_get + "(" + x + ", " + y + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
	}

	// --------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest_mulVec() {
		Matrix3x3 m = new Matrix3x3();
		Vector3D v1 = new Vector3D(), v2;
		HashMap<Integer, Double> mem1 = new HashMap<>(), mem2;
		int p;
		double expected, actual;
		double fac = RND.nextDouble() * 666 + 42;
		for (int pass = 0; pass < 42; pass++) {
			// A - randomly modify and check v1
			p = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE / fac;
			v1.set(p, expected);
			mem1.put(p, expected);
			actual = v1.get(p);
			assertEquals("A - v1: (" + p + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
		m.toIdentity();
		m.set(0, 0, fac);
		m.set(1, 1, fac);
		m.set(2, 2, fac);
		v2 = m.mulVec(v1);
		mem2 = new HashMap<>(mem1);
		for (int pass = 0; pass < 42; pass++) {
			// B - randomly check v2
			p = RND.nextInt(3);
			expected = fac * (mem2.containsKey(p) ? mem2.get(p) : 0d);
			actual = v2.get(p);
			assertEquals("A - v2: (" + p + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
			// A - randomly modify and check v1
			p = RND.nextInt(3);
			expected = RND.nextDouble() * Double.MAX_VALUE / fac;
			v1.set(p, expected);
			mem1.put(p, expected);
			actual = v1.get(p);
			assertEquals("A - v1: (" + p + ")", expected, actual, Math.abs(expected) * DELTA_FAC);
		}
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