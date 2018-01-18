import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;
import org.junit.*;

public class SkipListPublicTest {
    // ========== SYSTEM ==========
    protected static final String TEST_NAME_publicTest = "SkipListPublicTest";
    protected static final String TEST_NAME_secretTest = "SkipListSecretTest";
    // ----------
    protected static final String EX_NAME_add = "add [+contains]";
    protected static final String EX_NAME_contains = "contains [+add]";
    protected static final String EX_NAME_remove = "remove [+add+contains]";
    protected static final String EX_NAME_size = "size [+add+remove+contains]";
    protected static final String EX_NAME_misc = "miscellaneous [+everything]";
    // ----------
    protected static final String METHOD_NAME_getRandomLevel = "SkipList.getRandomLevel";
    protected static final String METHOD_NAME_add = "SkipList.add";
    protected static final String METHOD_NAME_addAll = "SkipList.addAll";
    protected static final String METHOD_NAME_clear = "SkipList.clear";
    protected static final String METHOD_NAME_contains = "SkipList.contains";
    protected static final String METHOD_NAME_containsAll = "SkipList.containsAll";
    protected static final String METHOD_NAME_isEmpty = "SkipList.isEmpty";
    protected static final String METHOD_NAME_remove = "SkipList.remove";
    protected static final String METHOD_NAME_removeAll = "SkipList.removeAll";
    protected static final String METHOD_NAME_size = "SkipList.size";
    // ----------

    // ========== TEST DATA ==========
    protected static final Random RANDOM = new Random(4711_0815_666L);
    protected static final int MAXRANDOM = 12345 + RANDOM.nextInt(12345);

    @Before
    public void initRandomSeed() {
        RANDOM.setSeed(4711_0815_666L);
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

    // ========== PUBLIC TEST ==========
    // -------------------- Innereien --------------------
    @Test(timeout = 666)
    public void pubTest__add__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__contains__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__remove__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__size__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__misc__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    private void check__Innereien() {
        @SuppressWarnings("rawtypes")
        Class<SkipList> clazz = SkipList.class;
        Class<?>[] classes = getDeclaredClasses(clazz);
        assertEquals("Du sollst keine inneren Klassen haben!", 0, classes.length);
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        assertEquals("Du sollst keine inneren Annotationen haben!", 0, annotations.length);
        Field[] fields = getDeclaredFields(clazz);
        assertTrue("Du sollst hoechstens ein Attribut (statt " + fields.length + ") haben!", fields.length <= 1);
        for (Field field : fields) {
            assertSame("Wenn du schon ein Attribut hast, dann eines vom Typ " + int.class, int.class, field.getType());
        }
        Constructor<?>[] constructors = getDeclaredConstructors(clazz);
        assertEquals("Du sollst genau einen >public< Konstruktor (den default-cons) haben!", 1, constructors.length);
        Method[] methods = getDeclaredMethods(clazz);
        assertEquals("Du sollst genau die zehn vorgegebenen Methoden haben!", 10, methods.length);
    }

    // -------------------- add [+contains] --------------------
    @Test
    public void pubTest__add() {
        Set<Integer> reference = new TreeSet<>();
        AbstractSkipList<Integer> sl = new SkipList<>();
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed long after " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
        }
        for (Integer n : reference) {
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed long after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
    }

    @Test(timeout = 4666)
    public void pubTest__add__checkComplexity__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        Set<MyWrapper<Integer>> reference = new TreeSet<>();
        AbstractSkipList<MyWrapper<Integer>> sl = new SkipList<>();
        HashMap<Integer, HashSet<Integer>> compareToTracker = new HashMap<>();
        MyWrapper<Integer> n;
        for (int i = -1000 + RANDOM.nextInt(2); i < 1000; i += 2) {
            n = new MyWrapper<>(i + RANDOM.nextInt(2), compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        for (int i = -42; i <= 42; i++) {
            compareToTracker.clear();
            n = new MyWrapper<>(RANDOM.nextInt(2000) - 1000, compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " shall not use hashCode() at all", 0, n.hashCodeCount);
            long totalComparisons = 0;
            for (HashSet<Integer> s : compareToTracker.values()) {
                totalComparisons += s.size();
            }
            assertTrue(SkipListPublicTest.METHOD_NAME_add + " seems to have wrong runtime complexity: MUST HAVE O(log(n))!!", 7 < totalComparisons && totalComparisons < 42);
            // cleanroom: 11-34
            checkSkipListStructure(sl);
        }
    }

    // -------------------- contains [+add] --------------------
    @Test(timeout = 666)
    public void pubTest__contains() {
        Set<String> sl = new SkipList<>();
        assertFalse(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, sl.contains("AuD"));
        assertTrue(SkipListPublicTest.METHOD_NAME_add + " failed", sl.add("AuD"));
        assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains("AuD"));
        assertFalse(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, sl.contains("PFP"));
        assertTrue(SkipListPublicTest.METHOD_NAME_add + " failed", sl.add("PFP"));
        assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains("PFP"));
    }

    @Test(timeout = 1666)
    public void pubTest__contains__checkComplexity__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        Set<MyWrapper<Integer>> reference = new TreeSet<>();
        AbstractSkipList<MyWrapper<Integer>> sl = new SkipList<>();
        HashMap<Integer, HashSet<Integer>> compareToTracker = new HashMap<>();
        MyWrapper<Integer> n;
        for (int i = -1000 + RANDOM.nextInt(2); i < 1000; i += 2) {
            n = new MyWrapper<>(i + RANDOM.nextInt(2), compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        checkSkipListStructure(sl);
        for (int i = -42; i <= 42; i++) {
            compareToTracker.clear();
            n = new MyWrapper<>(RANDOM.nextInt(2000) - 1000, compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed", reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " shall not use hashCode() at all", 0, n.hashCodeCount);
            long totalComparisons = 0;
            for (HashSet<Integer> s : compareToTracker.values()) {
                totalComparisons += s.size();
            }
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " seems to have wrong runtime complexity: MUST HAVE O(log(n))!!", 7 < totalComparisons && totalComparisons < 42);
            // cleanroom: 11-33
        }
    }

    // -------------------- remove [+add+contains] --------------------
    @Test(timeout = 1666)
    public void pubTest__remove() {
        Set<Integer> reference = new TreeSet<>();
        AbstractSkipList<Integer> sl = new SkipList<>();
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        Set<Integer> referenceFrozen = Collections.unmodifiableSet(new TreeSet<Integer>(reference));
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextBoolean() && reference.size() > 0 ? reference.toArray(new Integer[0])[RANDOM.nextInt(reference.size())] : RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " failed", reference.remove(n), sl.remove(n));
        }
        for (Integer n : referenceFrozen) {
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " failed", reference.remove(n), sl.remove(n));
        }
        for (Integer n : referenceFrozen) {
            assertFalse(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_remove, sl.contains(n));
        }
    }

    @Test(timeout = 2666)
    public void pubTest__remove__checkComplexity__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        Set<MyWrapper<Integer>> reference = new TreeSet<>();
        AbstractSkipList<MyWrapper<Integer>> sl = new SkipList<>();
        HashMap<Integer, HashSet<Integer>> compareToTracker = new HashMap<>();
        MyWrapper<Integer> n;
        for (int i = -1000 + RANDOM.nextInt(2); i < 1000; i += 2) {
            n = new MyWrapper<>(i + RANDOM.nextInt(2), compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        for (int i = -42; i <= 42; i++) {
            compareToTracker.clear();
            n = new MyWrapper<>(RANDOM.nextInt(2000) - 1000, compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " failed", reference.remove(n), sl.remove(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " shall not use hashCode() at all", 0, n.hashCodeCount);
            long totalComparisons = 0;
            for (HashSet<Integer> s : compareToTracker.values()) {
                totalComparisons += s.size();
            }
            assertTrue(SkipListPublicTest.METHOD_NAME_remove + " seems to have wrong runtime complexity: MUST HAVE O(log(n))!!", 7 < totalComparisons && totalComparisons < 42);
            // cleanroom: 13-30
            checkSkipListStructure(sl);
        }
    }

    // -------------------- size [+add+remove+contains+isEmpty] --------------------
    @Test(timeout = 666)
    public void pubTest__size() {
        Set<Integer> reference = new TreeSet<>();
        AbstractSkipList<Integer> sl = new SkipList<>();
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_add, reference.size(), sl.size());
        }
        assertFalse(SkipListPublicTest.METHOD_NAME_isEmpty + "failed", sl.isEmpty());
        Set<Integer> referenceFrozen = Collections.unmodifiableSet(new TreeSet<Integer>(reference));
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " failed", reference.remove(n), sl.remove(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_remove, reference.size(), sl.size());
        }
        for (Integer n : referenceFrozen) {
            assertEquals(SkipListPublicTest.METHOD_NAME_remove + " failed", reference.remove(n), sl.remove(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_remove, reference.size(), sl.size());
        }
        assertEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_remove, 0, sl.size());
        assertTrue(SkipListPublicTest.METHOD_NAME_isEmpty + "failed", sl.isEmpty());
    }

    // -------------------- miscellaneous: getRandomLevel, addAll, clear, containsAll, isEmpty, removeAll --------------------
    @Test
    public void pubTest__getRandomLevel() {
        AbstractSkipList<?> sl = new SkipList<>();
        int[] distribution = new int[SkipList.MAX_LEVELS];
        for (int i = 0; i < 1000000; i++) {
            int n = sl.getRandomLevel();
            assertTrue(SkipListPublicTest.METHOD_NAME_getRandomLevel + "failed: returned value (" + n + ") is not in the range [0," + SkipList.MAX_LEVELS + ")", n >= 0 && n < SkipList.MAX_LEVELS);
            distribution[n]++;
        }
        for (int c = 1; c < SkipList.MAX_LEVELS; c++) {
            assertTrue(SkipListPublicTest.METHOD_NAME_getRandomLevel + "failed: distribution (" + Arrays.toString(distribution) + ") is not as specified", distribution[c - 1] - distribution[c] >= -10);
        }
        for (int c = 0; c < 4; c++) {
            assertTrue(SkipListPublicTest.METHOD_NAME_getRandomLevel + "failed: level (" + c + ") was never returned", distribution[c] > 0);
        }
    }

    @Test(timeout = 666)
    public void pubTest__addAll__clear() {
        Set<Integer> reference = new TreeSet<>();
        AbstractSkipList<Integer> sl = new SkipList<>();
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            reference.add(n);
        }
        assertEquals(SkipListPublicTest.METHOD_NAME_size + " failed before " + SkipListPublicTest.METHOD_NAME_addAll, 0, sl.size());
        assertTrue(SkipListPublicTest.METHOD_NAME_isEmpty + "failed before " + SkipListPublicTest.METHOD_NAME_addAll, sl.isEmpty());
        assertTrue(SkipListPublicTest.METHOD_NAME_addAll + "failed", sl.addAll(Collections.unmodifiableSet(reference)));
        assertNotEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_addAll, 0, sl.size());
        assertFalse(SkipListPublicTest.METHOD_NAME_isEmpty + "failed after " + SkipListPublicTest.METHOD_NAME_addAll, sl.isEmpty());
        assertFalse(SkipListPublicTest.METHOD_NAME_addAll + "failed", sl.addAll(Collections.unmodifiableSet(reference)));
        assertNotEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_addAll, 0, sl.size());
        assertFalse(SkipListPublicTest.METHOD_NAME_isEmpty + "failed after " + SkipListPublicTest.METHOD_NAME_addAll, sl.isEmpty());
        for (Integer n : reference) {
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_addAll, sl.contains(n));
        }
        sl.clear();
        for (Integer n : reference) {
            assertFalse(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_clear, sl.contains(n));
        }
        assertEquals(SkipListPublicTest.METHOD_NAME_size + " failed after " + SkipListPublicTest.METHOD_NAME_clear, 0, sl.size());
        assertTrue(SkipListPublicTest.METHOD_NAME_isEmpty + "failed after " + SkipListPublicTest.METHOD_NAME_clear, sl.isEmpty());
    }

    @Test(timeout = 1666)
    public void pubTest__addAll__checkComplexity__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        Set<MyWrapper<Integer>> reference = new TreeSet<>();
        AbstractSkipList<MyWrapper<Integer>> sl = new SkipList<>();
        HashMap<Integer, HashSet<Integer>> compareToTracker = new HashMap<>();
        for (int i = -1000 + RANDOM.nextInt(2); i < 1000; i += 2) {
            MyWrapper<Integer> n = new MyWrapper<>(i + RANDOM.nextInt(2), compareToTracker);
            reference.add(n);
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_addAll + "failed", sl.addAll(Collections.unmodifiableSet(reference)));
        for (MyWrapper<Integer> n : reference) {
            assertEquals(SkipListPublicTest.METHOD_NAME_addAll + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_addAll + " shall not use hashCode() at all", 0, n.hashCodeCount);
        }
        long totalComparisons = 0;
        for (HashSet<Integer> s : compareToTracker.values()) {
            totalComparisons += s.size();
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_addAll + " seems to have wrong runtime complexity: MUST HAVE O(m*log(n))!!", 7_666 < totalComparisons && totalComparisons < 42_666);
        // cleanroom: 20_236 - 21_336
        for (MyWrapper<Integer> n : reference) {
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_addAll, sl.contains(n));
        }
        checkSkipListStructure(sl);
    }

    @Test(timeout = 1666)
    public void pubTest__containsAll() {
        Set<Integer> reference = new TreeSet<Integer>();
        AbstractSkipList<Integer> sl = new SkipList<>();
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        assertFalse(SkipListPublicTest.METHOD_NAME_isEmpty + "failed", sl.isEmpty());
        assertTrue(SkipListPublicTest.METHOD_NAME_containsAll + " failed", sl.containsAll(Collections.unmodifiableSet(reference)));
        int size = reference.size();
        for (int i = 0; i < size / 2; i++) { // remove half
            reference.remove(reference.toArray(new Integer[0])[RANDOM.nextInt(reference.size())]);
            assertTrue(SkipListPublicTest.METHOD_NAME_containsAll + " failed", sl.containsAll(Collections.unmodifiableSet(reference)));
        }
        for (int i = 0; i < size; i++) { // add a few
            reference.add(RANDOM.nextBoolean() ? -RANDOM.nextInt(MAXRANDOM) - 1 : MAXRANDOM + RANDOM.nextInt(MAXRANDOM));
            assertFalse(SkipListPublicTest.METHOD_NAME_containsAll + " failed", sl.containsAll(Collections.unmodifiableSet(reference)));
        }
    }

    @Test(timeout = 1666)
    public void pubTest__containsAll__checkComplexity__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        Set<MyWrapper<Integer>> reference = new TreeSet<>();
        AbstractSkipList<MyWrapper<Integer>> sl = new SkipList<>();
        HashMap<Integer, HashSet<Integer>> compareToTracker = new HashMap<>();
        for (int i = -1000 + RANDOM.nextInt(2); i < 1000; i += 2) {
            MyWrapper<Integer> n = new MyWrapper<>(i + RANDOM.nextInt(2), compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_containsAll + " failed", sl.containsAll(Collections.unmodifiableSet(reference)));
        for (MyWrapper<Integer> n : reference) {
            assertEquals(SkipListPublicTest.METHOD_NAME_containsAll + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_containsAll + " shall not use hashCode() at all", 0, n.hashCodeCount);
        }
        long totalComparisons = 0;
        for (HashSet<Integer> s : compareToTracker.values()) {
            totalComparisons += s.size();
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_containsAll + " seems to have wrong runtime complexity: MUST HAVE O(m*log(n))!!", 7_666 < totalComparisons && totalComparisons < 42_666);
        // cleanroom: 23_071 - 24_208
        for (int i = -5; i <= 5; i++) {
            reference.add(new MyWrapper<>(i, compareToTracker));
        }
        compareToTracker.clear();
        assertFalse(SkipListPublicTest.METHOD_NAME_containsAll + " failed", sl.containsAll(Collections.unmodifiableSet(reference)));
        for (MyWrapper<Integer> n : reference) {
            assertEquals(SkipListPublicTest.METHOD_NAME_containsAll + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_containsAll + " shall not use hashCode() at all", 0, n.hashCodeCount);
        }
        totalComparisons = 0;
        for (HashSet<Integer> s : compareToTracker.values()) {
            totalComparisons += s.size();
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_containsAll + " seems to have wrong runtime complexity: MUST HAVE O(m*log(n))!!", 666 < totalComparisons && totalComparisons < 10_666);
        // cleanroom: 5_294 - 5_394
        checkSkipListStructure(sl);
    }

    @Test(timeout = 2666)
    public void pubTest__removeAll() {
        Set<Integer> reference = new java.util.TreeSet<Integer>();
        SkipList<Integer> sl = new SkipList<>();
        for (int i = 0; i < 666; i++) {
            int n = RANDOM.nextInt(MAXRANDOM);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        assertFalse(SkipListPublicTest.METHOD_NAME_isEmpty + "failed", sl.isEmpty());
        Set<Integer> referenceFrozen = Collections.unmodifiableSet(new TreeSet<Integer>(reference));
        assertTrue(SkipListPublicTest.METHOD_NAME_containsAll + " failed", sl.containsAll(Collections.unmodifiableSet(reference)));
        int size = reference.size();
        for (int i = 0; i < size / 2; i++) { // remove half
            reference.remove(reference.toArray(new Integer[0])[RANDOM.nextInt(reference.size())]);
        }
        for (int i = 0; i < size; i++) { // add a few
            reference.add(RANDOM.nextBoolean() ? -RANDOM.nextInt(MAXRANDOM) - 1 : MAXRANDOM + RANDOM.nextInt(MAXRANDOM));
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_removeAll + " failed", sl.removeAll(Collections.unmodifiableSet(reference)));
        assertFalse(SkipListPublicTest.METHOD_NAME_removeAll + " failed", sl.removeAll(Collections.unmodifiableSet(reference)));
        for (Integer n : reference) {
            assertFalse(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_removeAll, sl.contains(n));
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_removeAll + " failed", sl.removeAll(Collections.unmodifiableSet(referenceFrozen)));
        assertFalse(SkipListPublicTest.METHOD_NAME_removeAll + " failed", sl.removeAll(Collections.unmodifiableSet(referenceFrozen)));
        for (Integer n : referenceFrozen) {
            assertFalse(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_removeAll, sl.contains(n));
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_isEmpty + "failed", sl.isEmpty());
    }

    @Test(timeout = 4666)
    public void pubTest__removeAll__checkComplexity__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        Set<MyWrapper<Integer>> reference = new TreeSet<>();
        AbstractSkipList<MyWrapper<Integer>> sl = new SkipList<>();
        HashMap<Integer, HashSet<Integer>> compareToTracker = new HashMap<>();
        for (int i = -1000 + RANDOM.nextInt(2); i < 1000; i += 2) {
            MyWrapper<Integer> n = new MyWrapper<>(i + RANDOM.nextInt(2), compareToTracker);
            assertEquals(SkipListPublicTest.METHOD_NAME_contains + " failed before " + SkipListPublicTest.METHOD_NAME_add, reference.contains(n), sl.contains(n));
            assertEquals(SkipListPublicTest.METHOD_NAME_add + " failed", reference.add(n), sl.add(n));
            assertTrue(SkipListPublicTest.METHOD_NAME_contains + " failed after " + SkipListPublicTest.METHOD_NAME_add, sl.contains(n));
        }
        for (int i = -10; i <= 0; i++) {
            reference.add(new MyWrapper<>(i, compareToTracker));
        }
        for (int i = 0; i <= 10; i++) {
            reference.remove(new MyWrapper<>(i, compareToTracker));
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_removeAll + " failed", sl.removeAll(Collections.unmodifiableSet(reference)));
        for (MyWrapper<Integer> n : reference) {
            assertEquals(SkipListPublicTest.METHOD_NAME_removeAll + " shall not use equals() at all", 0, n.equalsCount);
            assertEquals(SkipListPublicTest.METHOD_NAME_removeAll + " shall not use hashCode() at all", 0, n.hashCodeCount);
        }
        long totalComparisons = 0;
        for (HashSet<Integer> s : compareToTracker.values()) {
            totalComparisons += s.size();
        }
        assertTrue(SkipListPublicTest.METHOD_NAME_removeAll + " seems to have wrong runtime complexity: MUST HAVE O(m*log(n))!!", totalComparisons < 42_666);
        // cleanroom: 24_662 - 26_010
        checkSkipListStructure(sl);
    }

    // ========== TEST HELPER ==========
    private static final class MyWrapper<T extends Comparable<T>> implements Comparable<MyWrapper<T>> {
        private T value;
        private HashMap<T, HashSet<T>> compareToTracker;
        private int equalsCount;
        private int hashCodeCount;

        private MyWrapper(T value, HashMap<T, HashSet<T>> compareToTracker) {
            this.value = value;
            this.compareToTracker = compareToTracker;
        }

        @Override
        public boolean equals(Object other) {
            equalsCount++;
            if (other instanceof MyWrapper) {
                return this.value.equals(((MyWrapper<?>) other).value);
            } else {
                return super.equals(other);
            }
        }

        @Override
        public int hashCode() {
            hashCodeCount++;
            return value.hashCode();
        }

        @Override
        public int compareTo(MyWrapper<T> other) {
            if (compareToTracker != null) {
                T trackerKey, trackerValue;
                if (this.value.compareTo(other.value) <= 0) {
                    trackerKey = this.value;
                    trackerValue = other.value;
                } else {
                    trackerKey = other.value;
                    trackerValue = this.value;
                }
                if (!compareToTracker.containsKey(trackerKey)) {
                    compareToTracker.put(trackerKey, new HashSet<T>());
                }
                compareToTracker.get(trackerKey).add(trackerValue);
            }
            return value.compareTo(other.value);
        }

        @Override
        public String toString() {
            return this.getClass().getName() + "(" + value.toString() + ")";
        }
    }

    protected static final <X extends Comparable<? super X>> void checkSkipListStructure(AbstractSkipList<X> sl) {
        assertNotNull("head must never be null, but was now!", sl.head);
        assertNotNull("head.next must never be null, but was now!", sl.head.next);
        assertEquals("head must have (" + AbstractSkipList.MAX_LEVELS + ") levels!", AbstractSkipList.MAX_LEVELS, sl.head.next.length);
        int[] elems = new int[AbstractSkipList.MAX_LEVELS];
        for (int level = 0; level < AbstractSkipList.MAX_LEVELS; level++) {
            SkipListNode<X> node = sl.head.next[level];
            X prevValue = null;
            while (node != null) {
                elems[level]++;
                assertNotNull("value must never be null, but was now!", node.value);
                prevValue = node.value;
                node = node.next[level];
                if (node != null) {
                    assertNotNull("value must never be null, but was now!", node.value);
                    assertTrue("values in wrong order: " + prevValue + " before " + node.value, prevValue.compareTo(node.value) <= 0);
                }
            }
        }
        for (int level = 1; level < AbstractSkipList.MAX_LEVELS; level++) {
            assertTrue("there should be more values on level " + (level - 1) + " (found: " + elems[level - 1] + ") than on the next level " + (level) + " (found: " + elems[level] + ")", elems[level - 1] >= elems[level]);
        }
    }
}