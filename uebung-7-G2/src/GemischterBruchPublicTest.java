import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import org.junit.*;

public class GemischterBruchPublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME = "GemischterBruch";
	public static final String CLASS_NAME = "GemischterBruch";
	public static final String METHOD_NAME_istPositiv = "istPositiv";
	public static final String METHOD_NAME_holeGanzzahligerAnteil = "holeGanzzahligerAnteil";
	public static final String METHOD_NAME_holeZaehler = "holeZaehler";
	public static final String METHOD_NAME_holeNenner = "holeNenner";
	public static final String METHOD_NAME_vereinfache = "vereinfache";
	public static final String METHOD_NAME_compareTo = "compareTo";

	// ========== TEST DATA ==========
	private static final java.util.Random RND = new java.util.Random(4711_0815_666L);
	private static final long[] PRIMES = new long[4711];
	static {
		PRIMES[0] = 2;
		for (int i = 1; i < PRIMES.length; i++) {
			long p = PRIMES[i - 1];
			boolean isPrime;
			do {
				p++;
				isPrime = true;
				for (int j = 0; j < i && isPrime; j++) {
					isPrime &= p % PRIMES[j] != 0;
				}
			} while (!isPrime);
			PRIMES[i] = p;
		}
	}

	// ========== Intestines ==========
	private static final Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

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

	// ========== PUBLIC TESTS ==========
	// -------------------- Innereien --------------------
	@Test(timeout = 666)
	public void pubTest__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<GemischterBruch> clazz = GemischterBruch.class;
		Class<?>[] classes = getDeclaredClasses(clazz);
		assertEquals("Du sollst keine inneren Klassen haben!", 0, classes.length);
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		assertEquals("Du sollst keine inneren Annotationen haben!", 0, annotations.length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals("Du sollst keine anderen Attribute neben meinen abstrakten haben!", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		int pubCons = 0, privCons = 0;
		for (Constructor<?> cons : constructors) {
			if (Modifier.isPublic(cons.getModifiers())) {
				pubCons++;
			} else if (Modifier.isPrivate(cons.getModifiers())) {
				privCons++;
			}
		}
		assertEquals("Du sollst genau zwei >public< Konstruktoren haben!", 2, pubCons);
		assertTrue("Du sollst hoechstens einen >private< Konstruktor haben!", 0 <= privCons && privCons <= 1);
		assertTrue("Du sollst genau zwei >public< Konstruktoren und hoechstens einen >private< Konstruktor haben - und sonst KEINEN anderen!", 0 <= constructors.length && constructors.length <= 3);
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals("Du sollst genau sechs Methoden haben und die sollen allesamt >public< sein!", 6, methods.length);
		for (Method meth : methods) {
			assertTrue("Du sollst NUR >public< Methoden haben! (aber du hast: >" + meth + "< ?)", Modifier.isPublic(meth.getModifiers()));
		}
	}

	// -------------------- Konstruktoren --------------------
	@Test(timeout = 666)
	public void pubTest__Konstruktoren() {
		AbstrakterGemischterBruch b;
		boolean istPositiv;
		for (int pass = 0; pass < 42; pass++) {
			for (int sign = 0x000; sign <= 0x111; sign++) {
				long g = ((sign & 0b100) != 0 ? -1 : 1) * RND.nextLong();
				long z = ((sign & 0b010) != 0 ? -1 : 1) * RND.nextLong();
				long n = ((sign & 0b001) != 0 ? -1 : 1) * (1 + RND.nextLong());
				// -----
				b = new GemischterBruch(z, n);
				istPositiv = !((Math.signum(z) >= 0) ^ (Math.signum(n) >= 0)) || (z == 0);
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, b.istPositiv());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 0, b.holeGanzzahligerAnteil());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", Math.abs(z), b.holeZaehler());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", Math.abs(n), b.holeNenner());
				// -----
				b = new GemischterBruch(g, z, n);
				istPositiv = ((Math.signum(g) >= 0) ^ (Math.signum(z) >= 0) ^ (Math.signum(n) >= 0)) || (g == 0 && z == 0);
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, b.istPositiv());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", Math.abs(g), b.holeGanzzahligerAnteil());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", Math.abs(z), b.holeZaehler());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", Math.abs(n), b.holeNenner());
			}
		}
	}

	// -------------------- vereinfache --------------------
	@Test(timeout = 666)
	public void pubTest__vereinfache() {
		AbstrakterGemischterBruch b, bv;
		boolean istPositiv;
		for (int pass = 0; pass < 42; pass++) {
			for (int sign = 0x000; sign <= 0x111; sign++) {
				long fak = PRIMES[RND.nextInt(100)];
				long g = ((sign & 0b100) != 0 ? -1 : 1) * PRIMES[RND.nextInt(100)];
				long z = ((sign & 0b010) != 0 ? -1 : 1) * PRIMES[42 + RND.nextInt(100)];
				long n = ((sign & 0b001) != 0 ? -1 : 1) * PRIMES[666 + RND.nextInt(100)];
				// -----
				b = new GemischterBruch(fak * z, fak * n);
				bv = b.vereinfache();
				istPositiv = !((Math.signum(z) >= 0) ^ (Math.signum(n) >= 0)) || (z == 0);
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, bv.istPositiv());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 0, bv.holeGanzzahligerAnteil());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", Math.abs(z), bv.holeZaehler());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", Math.abs(n), bv.holeNenner());
				// -----
				b = new GemischterBruch((z <= 0 ? -1 : 1) * (Math.abs(z) + fak * Math.abs(n)), n);
				bv = b.vereinfache();
				istPositiv = !((Math.signum(z) >= 0) ^ (Math.signum(n) >= 0)) || (z == 0);
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, bv.istPositiv());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", fak, bv.holeGanzzahligerAnteil());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", Math.abs(z), bv.holeZaehler());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", Math.abs(n), bv.holeNenner());
				// -----
				b = new GemischterBruch(g, fak * z, fak * n);
				bv = b.vereinfache();
				istPositiv = ((Math.signum(g) >= 0) ^ (Math.signum(z) >= 0) ^ (Math.signum(n) >= 0)) || (g == 0 && z == 0);
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, bv.istPositiv());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", Math.abs(g), bv.holeGanzzahligerAnteil());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", Math.abs(z), bv.holeZaehler());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", Math.abs(n), bv.holeNenner());
				// -----
				b = new GemischterBruch(g, (z <= 0 ? -1 : 1) * (Math.abs(z) + fak * Math.abs(n)), n);
				bv = b.vereinfache();
				istPositiv = ((Math.signum(g) >= 0) ^ (Math.signum(z) >= 0) ^ (Math.signum(n) >= 0)) || (g == 0 && z == 0);
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, bv.istPositiv());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", Math.abs(g) + fak, bv.holeGanzzahligerAnteil());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", Math.abs(z), bv.holeZaehler());
				assertEquals(GemischterBruchPublicTest.CLASS_NAME + "(" + g + ", " + z + ", " + n + ")." + GemischterBruchPublicTest.METHOD_NAME_vereinfache + "." + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", Math.abs(n), bv.holeNenner());
			}
		}
	}

	// -------------------- mul / div --------------------
	@Test(timeout = 666)
	public void pubTest__multipliziereMit() {
		for (int pass = 0; pass < 42; pass++) {
			long z1 = PRIMES[RND.nextInt(100)], n1 = PRIMES[666 + RND.nextInt(100)];
			long z2 = PRIMES[RND.nextInt(100)], n2 = PRIMES[666 + RND.nextInt(100)];
			boolean istPositiv = RND.nextBoolean();
			AbstrakterGemischterBruch b1 = new GemischterBruch((istPositiv ? 1 : -1) * z1, n1);
			AbstrakterGemischterBruch b2 = new GemischterBruch(z2, n2);
			AbstrakterGemischterBruch b1_mul_b2 = b1.multipliziereMit(b2);
			assertEquals(b1 + "x" + b2 + "=?=" + b1_mul_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, b1_mul_b2.istPositiv());
			assertEquals(b1 + "x" + b2 + "=?=" + b1_mul_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 0, b1_mul_b2.holeGanzzahligerAnteil());
			assertEquals(b1 + "x" + b2 + "=?=" + b1_mul_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", z1 * z2, b1_mul_b2.holeZaehler());
			assertEquals(b1 + "x" + b2 + "=?=" + b1_mul_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", n1 * n2, b1_mul_b2.holeNenner());
		}
	}

	@Test(timeout = 666)
	public void pubTest__dividiereDurch() {
		for (int pass = 0; pass < 42; pass++) {
			long z1 = PRIMES[RND.nextInt(100)], n1 = PRIMES[666 + RND.nextInt(100)];
			long n2 = PRIMES[RND.nextInt(100)], z2 = PRIMES[666 + RND.nextInt(100)];
			boolean istPositiv = RND.nextBoolean();
			AbstrakterGemischterBruch b1 = new GemischterBruch(z1, n1);
			AbstrakterGemischterBruch b2 = new GemischterBruch(z2, (istPositiv ? 1 : -1) * n2);
			AbstrakterGemischterBruch b1_div_b2 = b1.dividiereDurch(b2);
			assertEquals(b1 + "/" + b2 + "=?=" + b1_div_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv, b1_div_b2.istPositiv());
			assertEquals(b1 + "/" + b2 + "=?=" + b1_div_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 0, b1_div_b2.holeGanzzahligerAnteil());
			assertEquals(b1 + "/" + b2 + "=?=" + b1_div_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", z1 * n2, b1_div_b2.holeZaehler());
			assertEquals(b1 + "/" + b2 + "=?=" + b1_div_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", n1 * z2, b1_div_b2.holeNenner());
		}
	}

	// -------------------- add / sub --------------------
	@Test
	public void pubTest__addiereZu() {
		AbstrakterGemischterBruch[][] inOut = { //
				{ new GemischterBruch(2, 3, 5), new GemischterBruch(7, 1, 10) }, //
				{ new GemischterBruch(2, 3, 4), new GemischterBruch(7, 1, 2) }, //
				{ new GemischterBruch(2, 17, 42), new GemischterBruch(-3, 19, 42) }, //
		};
		long[][] expected = { //
				{ 9, 7, 10 }, //
				{ 10, 1, 4 }, //
				{ 1, 1, 21 }, //
		};
		boolean[] istPositiv = { //
				true, //
				true, //
				false, //
		};
		for (int i = 0; i < inOut.length; i++) {
			AbstrakterGemischterBruch b1 = inOut[i][0];
			AbstrakterGemischterBruch b2 = inOut[i][1];
			AbstrakterGemischterBruch b1_add_b2 = b1.addiereZu(b2);
			assertEquals(b1 + "+" + b2 + "=?=" + b1_add_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv[i], b1_add_b2.istPositiv());
			assertEquals(b1 + "+" + b2 + "=?=" + b1_add_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", expected[i][0], b1_add_b2.holeGanzzahligerAnteil());
			assertEquals(b1 + "+" + b2 + "=?=" + b1_add_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", expected[i][1], b1_add_b2.holeZaehler());
			assertEquals(b1 + "+" + b2 + "=?=" + b1_add_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", expected[i][2], b1_add_b2.holeNenner());
		}
		
		GemischterBruch b3 = new GemischterBruch(-1, 4, 5);
		GemischterBruch b4 = new GemischterBruch(1, 4, 5);
		GemischterBruch r1 = (GemischterBruch) b3.addiereZu(b4);
		assertEquals(b3 + "+" + b4 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", true, r1.istPositiv());
		assertEquals(b3 + "+" + b4 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 0, r1.holeGanzzahligerAnteil());
		assertEquals(b3 + "+" + b4 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 0, r1.holeZaehler());
		assertEquals(b3 + "+" + b4 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 1, r1.holeNenner());

		GemischterBruch b5 = new GemischterBruch(-1, 4, 5);
		GemischterBruch b6 = new GemischterBruch(5, 4, 5);
		GemischterBruch r2 = (GemischterBruch) b5.addiereZu(b6);
		assertEquals(b5 + "+" + b6 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", true, r2.istPositiv());
		assertEquals(b5 + "+" + b6 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 4, r2.holeGanzzahligerAnteil());
		assertEquals(b5 + "+" + b6 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 0, r2.holeZaehler());
		assertEquals(b5 + "+" + b6 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 1, r2.holeNenner());
		
		GemischterBruch b7 = new GemischterBruch(-1, 4, 5);
		GemischterBruch b8 = new GemischterBruch(-5, 4, 5);
		GemischterBruch r3 = (GemischterBruch) b7.addiereZu(b8);
		assertEquals(b7 + "+" + b8 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", false, r3.istPositiv());
		assertEquals(b7 + "+" + b8 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 7, r3.holeGanzzahligerAnteil());
		assertEquals(b7 + "+" + b8 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 3, r3.holeZaehler());
		assertEquals(b7 + "+" + b8 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 5, r3.holeNenner());
		
		GemischterBruch b9 = new GemischterBruch(-3, 3, 5);
		GemischterBruch b10 = new GemischterBruch(5, 4, 5);
		GemischterBruch r4 = (GemischterBruch) b9.addiereZu(b10);
		assertEquals(b9 + "+" + b10 + "=?=" + r4 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", true, r4.istPositiv());
		assertEquals(b9 + "+" + b10 + "=?=" + r4 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 2, r4.holeGanzzahligerAnteil());
		assertEquals(b9 + "+" + b10 + "=?=" + r4 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 1, r4.holeZaehler());
		assertEquals(b9 + "+" + b10 + "=?=" + r4 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 5, r4.holeNenner());
		
		GemischterBruch b11= new GemischterBruch(3, 3, 5);
		GemischterBruch b12 = new GemischterBruch(-5, 4, 5);
		GemischterBruch r5 = (GemischterBruch) b11.addiereZu(b12);
		assertEquals(b11 + "+" + b12 + "=?=" + r5 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", false, r5.istPositiv());
		assertEquals(b11 + "+" + b12 + "=?=" + r5 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 2, r5.holeGanzzahligerAnteil());
		assertEquals(b11 + "+" + b12 + "=?=" + r5 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 1, r5.holeZaehler());
		assertEquals(b11 + "+" + b12 + "=?=" + r5 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 5, r5.holeNenner());
		

	}

	@Test
	public void pubTest__subtrahiereDavon() {
		AbstrakterGemischterBruch[][] inOut = { //
				{ new GemischterBruch(2, 3, 5), new GemischterBruch(7, 1, 10) }, //
				{ new GemischterBruch(2, 3, 4), new GemischterBruch(7, 1, 2) }, //
				{ new GemischterBruch(2, 17, 42), new GemischterBruch(-3, 19, 42) }, //
		};
		long[][] expected = { //
				{ 4, 1, 2 }, //
				{ 4, 3, 4 }, //
				{ 5, 6, 7 }, //
		};
		boolean[] istPositiv = { //
				false, //
				false, //
				true, //
		};
		for (int i = 0; i < inOut.length; i++) {
			AbstrakterGemischterBruch b1 = inOut[i][0];
			AbstrakterGemischterBruch b2 = inOut[i][1];
			AbstrakterGemischterBruch b1_sub_b2 = b1.subtrahiereDavon(b2);
			assertEquals(b1 + "-" + b2 + "=?=" + b1_sub_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", istPositiv[i], b1_sub_b2.istPositiv());
			assertEquals(b1 + "-" + b2 + "=?=" + b1_sub_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", expected[i][0], b1_sub_b2.holeGanzzahligerAnteil());
			assertEquals(b1 + "-" + b2 + "=?=" + b1_sub_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", expected[i][1], b1_sub_b2.holeZaehler());
			assertEquals(b1 + "-" + b2 + "=?=" + b1_sub_b2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", expected[i][2], b1_sub_b2.holeNenner());
		}
		
		GemischterBruch b1 = new GemischterBruch(-5, 1, 5);
		GemischterBruch b2 = new GemischterBruch(-5, 1, 5);
		GemischterBruch r1 = (GemischterBruch) b1.subtrahiereDavon(b2);
		assertEquals(b1 + "-" + b2 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", true, r1.istPositiv());
		assertEquals(b1 + "-" + b2 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 0, r1.holeGanzzahligerAnteil());
		assertEquals(b1 + "-" + b2 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 0, r1.holeZaehler());
		assertEquals(b1 + "-" + b2 + "=?=" + r1 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 1, r1.holeNenner());

		GemischterBruch b3 = new GemischterBruch(-5, 1, 5);
		GemischterBruch b4 = new GemischterBruch(-6, 2, 5);
		GemischterBruch r2 = (GemischterBruch) b3.subtrahiereDavon(b4);
		assertEquals(b3 + "-" + b4 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", true, r2.istPositiv());
		assertEquals(b3 + "-" + b4 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 1, r2.holeGanzzahligerAnteil());
		assertEquals(b3 + "-" + b4 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 1, r2.holeZaehler());
		assertEquals(b3 + "-" + b4 + "=?=" + r2 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 5, r2.holeNenner());

		GemischterBruch b5 = new GemischterBruch(-5, 1, 5);
		GemischterBruch b6 = new GemischterBruch(6, 2, 5);
		GemischterBruch r3 = (GemischterBruch) b5.subtrahiereDavon(b6);
		assertEquals(b5 + "-" + b6 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_istPositiv + " failed.", false, r3.istPositiv());
		assertEquals(b5 + "-" + b6 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeGanzzahligerAnteil + " failed.", 11, r3.holeGanzzahligerAnteil());
		assertEquals(b5 + "-" + b6 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeZaehler + " failed.", 3, r3.holeZaehler());
		assertEquals(b5 + "-" + b6 + "=?=" + r3 + " @ " + GemischterBruchPublicTest.METHOD_NAME_holeNenner + " failed.", 5, r3.holeNenner());

		
		
	}

	// -------------------- compareTo --------------------
	@Test
	public void pubTest__compareTo() {
//		for (int pass = 0; pass < 42; pass++) {
//			long g1 = PRIMES[RND.nextInt(100)], z1 = PRIMES[RND.nextInt(100)], n1 = PRIMES[666 + RND.nextInt(100)];
//			long g2 = PRIMES[666 + RND.nextInt(100)], n2 = PRIMES[RND.nextInt(100)], z2 = PRIMES[666 + RND.nextInt(100)];
//			AbstrakterGemischterBruch b1 = new GemischterBruch(g1, z1, n1);
//			AbstrakterGemischterBruch b2 = new GemischterBruch(g2, z2, n2);
//			int b1_comp_b2 = b1.compareTo(b2);
//			assertEquals(b1 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b2 + ")" + " failed.", -1, b1_comp_b2);
//			int b1_comp_b1 = b1.compareTo(b1);
//			assertEquals(b1 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b1 + ")" + " failed.", 0, b1_comp_b1);
//			int b2_comp_b1 = b2.compareTo(b1);
//			assertEquals(b2 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b1 + ")" + " failed.", 1, b2_comp_b1);
//		}
		
		AbstrakterGemischterBruch b1 = new GemischterBruch(1, 2, 3);
		AbstrakterGemischterBruch b2 = new GemischterBruch(1, 1, 3);
		int result = b1.compareTo(b2);
		assertEquals(b1 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b2 + ")" + " failed.", 1, result);
		
		AbstrakterGemischterBruch b3 = new GemischterBruch(1581728, 182948492, 1181991991);
		AbstrakterGemischterBruch b4 = new GemischterBruch(1581728, 182948491, 1181991991);
		int result2 = b3.compareTo(b4);
		assertEquals(b3 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b4 + ")" + " failed.", 1, result2);
		
		AbstrakterGemischterBruch b5 = new GemischterBruch(1581721, 182948492, 1181991991);
		AbstrakterGemischterBruch b6 = new GemischterBruch(1581728, 182948491, 1181991991);
		int result3 = b5.compareTo(b6);
		assertEquals(b5 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b6 + ")" + " failed.", -1, result3);
		
		AbstrakterGemischterBruch b7 = new GemischterBruch(1581722, 1829484929, 1181991991);
		AbstrakterGemischterBruch b8 = new GemischterBruch(1581722, 182948491, 1181991991);
		int result4 = b7.compareTo(b8);
		assertEquals(b7 + "." + GemischterBruchPublicTest.METHOD_NAME_compareTo + "(" + b8 + ")" + " failed.", 1, result4);
		
		
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