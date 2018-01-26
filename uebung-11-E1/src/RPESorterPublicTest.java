import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class RPESorterPublicTest {
    // ========== SYSTEM ==========
    protected static final String EX_NAME_extract = "extract";
    protected static final String EX_NAME_merge = "merge";
    protected static final String EX_NAME_sort = "sort";
    // --------------------

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

    // ========== PUBLIC TEST ==========
    // -------------------- Innereien --------------------
    @Test(timeout = 666)
    public void pubTest__extract__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__merge__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__sort__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void check__Innereien() {
        @SuppressWarnings("rawtypes")
        Class<RPESorter> clazz = RPESorter.class;
        assertSame("Du sollst genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
        assertEquals("Du sollst genau >eine< bestimmte Schnittstelle implementieren!", 1, clazz.getInterfaces().length);
        assertEquals("Du sollst genau eine >bestimmte< Schnittstelle implementieren!", RPESorterInterface.class, clazz.getInterfaces()[0]);
        assertEquals("Du sollst keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
        assertEquals("Du sollst keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
        assertEquals("Du sollst genau >ein< bestimmtes Attribut haben!", 1, getDeclaredFields(clazz).length);
        assertEquals("Du sollst genau ein >bestimmtes< Attribut haben!", Comparator.class, getDeclaredFields(clazz)[0].getType());
        Constructor<?>[] constructors = getDeclaredConstructors(clazz);
        assertEquals("Du sollst genau >einen< Konstruktoren haben und der soll public sein!", 1, constructors.length);
        assertTrue("Du sollst genau einen Konstruktoren haben und der soll >public< sein!", Modifier.isPublic((constructors[0].getModifiers())));
        Method[] interfaceMethods = getDeclaredMethods(RPESorterInterface.class);
        Method[] methods = getDeclaredMethods(RPESorter.class);
        assertEquals("Du sollst >genau< diejenigen oeffentlichen Methoden haben, die die Schnittstelle vorschreibt!", interfaceMethods.length, methods.length);
        HashSet<String> interfaceMethodsSet = new HashSet<>();
        for (Method method : interfaceMethods) {
            interfaceMethodsSet.add(method.getName());
        }
        for (Method method : methods) {
            if (!interfaceMethodsSet.contains(method.getName())) {
                fail("Du sollst >genau< diejenigen oeffentlichen Methoden haben, die die Schnittstelle vorschreibt! (die passt nicht: " + method + ")");
            }
            assertTrue("Du sollst >genau< diejenigen >oeffentlichen< Methoden haben, die die Schnittstelle vorschreibt! (die passt nicht: " + method + ")", Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()));
        }
    }

    // ------------------------------------------------------------
    @Test
    public void pubTest__extract() {
        MyWrapper<Integer> a = new MyWrapper<>(42);
        MyWrapper<Integer> b = new MyWrapper<>(21);
        MyWrapper<Integer> c = new MyWrapper<>(42);
        MyWrapper<Integer> d = new MyWrapper<>(666);
        MyWrapper<Integer> e = new MyWrapper<>(42);
        MyWrapper<Integer> f = new MyWrapper<>(4711);
        MyWrapper<Integer> g = new MyWrapper<>(666);
        LinkedList<MyWrapper<Integer>> in = new LinkedList<>(Arrays.asList(a, b, c, d, e, f, g));
        MyWrapperComparator<Integer> myWrapperComparatorInteger = new MyWrapperComparator<>();
        RPESorterInterface<MyWrapper<Integer>> sorter = new RPESorter<>(myWrapperComparatorInteger);
        LinkedList<MyWrapper<Integer>> result = sorter.extract(in);
        assertNotNull(result);
        assertEquals(4, result.size());
        assertTrue(result.contains(a));
        assertTrue(result.contains(c));
        assertTrue(result.contains(d));
        assertTrue(result.contains(f));
    }

    // ------------------------------------------------------------
    @Test
    public void pubTest__merge() {
        LinkedList<Integer> a = new LinkedList<>();
        for (int i = 0; i < 24; i += 2) {
            a.add(i);
        }
        LinkedList<Integer> b = new LinkedList<>();
        for (int i = 9; i < 30; i += 2) {
            b.add(i);
        }
        RPESorterInterface<Integer> sorter = new RPESorter<>(INTEGER_COMPARATOR);
        LinkedList<Integer> result = sorter.merge(new LinkedList<>(a), new LinkedList<>(b));
        assertNotNull(result);
        assertEquals(a.size() + b.size(), result.size());
        assertIsSorted(result, INTEGER_COMPARATOR);
        for (int i : a) {
            assertTrue(result.contains(i));
        }
        for (int i : b) {
            assertTrue(result.contains(i));
        }
    }

    // ------------------------------------------------------------
    @Test
    public void pubTest__sort() {
        LinkedList<String> strings = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            strings.add(getRandomString());
        }
        RPESorterInterface<String> sorter = new RPESorter<>(STRING_COMPARATOR);
        LinkedList<String> result = sorter.sort(new LinkedList<>(strings));
        assertNotNull(result);
        assertEquals(100, result.size());
        assertIsSorted(result, STRING_COMPARATOR);
        for (String s : strings) {
            assertTrue(result.contains(s));
        }
    }

    // ========== TEST HELPER ==========
    protected static final java.util.Random RANDOM = new java.util.Random(4711_0815_666L);

    private static final class MyWrapper<T extends Comparable<? super T>> {
        private T value;

        private MyWrapper(T value) {
            this.value = value;
        }
    }

    private static final class MyWrapperComparator<T extends Comparable<? super T>> implements Comparator<MyWrapper<T>> {
        @Override
        public int compare(MyWrapper<T> aMyWrapper, MyWrapper<T> anotherMyWrapper) {
            return aMyWrapper.value.compareTo(anotherMyWrapper.value);
        }
    }

    protected static final Comparator<Integer> INTEGER_COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(Integer anInteger, Integer anotherInteger) {
            return anInteger.compareTo(anotherInteger);
        }
    };

    protected static final Comparator<String> STRING_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String anString, String anotherString) {
            return anString.compareTo(anotherString);
        }
    };

    private static final String getRandomString() {
        int variance = RANDOM.nextInt(6) - 2;
        int size = 8 + variance;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char c = (char) ('A' + RANDOM.nextInt(26));
            if (RANDOM.nextBoolean()) {
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static final <T> void assertIsSorted(LinkedList<T> list, Comparator<T> comparator) {
        T last = null;
        for (T current : list) {
            if (last != null) {
                assertTrue("Wrong order of elements: " + current + " after " + last, comparator.compare(last, current) <= 0);
            }
            last = current;
        }
    }
}