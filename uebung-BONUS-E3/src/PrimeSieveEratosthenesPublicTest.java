import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.math.*;
import org.junit.*;

public class PrimeSieveEratosthenesPublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME = "PSE";

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
		Class<PrimeSieveEratosthenes> clazz = PrimeSieveEratosthenes.class;
		Class<?>[] classes = getDeclaredClasses(clazz);
		assertEquals("Du sollst keine inneren Klassen haben!", 0, classes.length);
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		assertEquals("Du sollst keine inneren Annotationen haben!", 0, annotations.length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals("Du sollst genau zwei Attribute haben!", 2, fields.length);
		for (Field field : fields) {
			assertTrue("Du sollst genau zwei Attribute haben: Eines vom Typ " + BigInteger.class + " und eines vom Typ " + clazz, field.getType().equals(BigInteger.class) || field.getType().equals(clazz));
		}
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals("Du sollst genau einen >public< Konstruktor (den default-cons) haben!", 1, constructors.length);
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals("Du sollst genau eine Methode haben!", 1, methods.length);
	}

	@Test(timeout = 19666)
	public void pubTest__getNextPrime() {
		PrimeSieveEratosthenes primeSieveEratosthenes = new PrimeSieveEratosthenes();
		BigInteger prime = null;
		for (int counter = 0; counter < primes.length; counter++) {
			prime = primeSieveEratosthenes.getNextPrime();
			assertNotNull("null ist keine Primzahl!", prime);
			Assert.assertEquals(counter + ". Primzahl ist falsch.", primes[counter], prime.intValue());
		}
	}

	// ========== TEST DATA ==========
	private static final int[] primes = getPrimes(10_000);

	protected static final int[] getPrimes(int n) {
		int[] primes = new int[n];
		primes[0] = 2;
		for (int i = 1; i < n; i++) {
			int p = primes[i - 1];
			od: do {
				p++;
				rof: for (long t = 2; t < p; t++) {
					if (t >= p - 1) {
						break od;
					} else if (p % t == 0) {
						break rof;
					}
				}
			} while (true);
			primes[i] = p;
		}
		return primes;
	}
}