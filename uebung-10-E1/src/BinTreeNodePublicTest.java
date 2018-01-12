import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class BinTreeNodePublicTest {
    // ========== SYSTEM ==========
    protected static final String EX_NAME_addLeft_addRight_isLeftChild_isRightChild_swap = "addLeft/addRight/isLeftChild/isRightChild/swap";
    protected static final String EX_NAME_getHeight = "getHeight";
    protected static final String EX_NAME_findNode_contains = "findNode/contains";
    protected static final String EX_NAME_insert = "insert";
    protected static final String EX_NAME_getMinNode_getMaxNode = "getMinNode/getMaxNode";
    protected static final String EX_NAME_deleteNode_delete = "deleteNode/delete";
    protected static final String EX_NAME_traverse = "traverse";
    protected static final String EX_NAME_nextNode = "nextNode";
    protected static final String CONSTRUCTOR_NAME_T = "BinTreeNode(T)";
    protected static final String CONSTRUCTOR_NAME_T_BinTreeNode_BinTreeNode = "BinTreeNode(T, BinTreeNode, BinTreeNode)";
    protected static final String METHOD_NAME_addLeft = "BinTreeNode.addLeft";
    protected static final String METHOD_NAME_addRight = "BinTreeNode.addRight";
    protected static final String METHOD_NAME_isLeftChild = "BinTreeNode.isLeftChild";
    protected static final String METHOD_NAME_isRightChild = "BinTreeNode.isRightChild";
    protected static final String METHOD_NAME_swap = "BinTreeNode.swap";
    protected static final String METHOD_NAME_getHeight = "BinTreeNode.getHeight";
    protected static final String METHOD_NAME_findNode = "BinTreeNode.findNode";
    protected static final String METHOD_NAME_contains = "BinTreeNode.contains";
    protected static final String METHOD_NAME_insert = "BinTreeNode.insert";
    protected static final String METHOD_NAME_getMinNode = "BinTreeNode.getMinNode";
    protected static final String METHOD_NAME_getMaxNode = "BinTreeNode.getMaxNode";
    protected static final String METHOD_NAME_deleteNode = "BinTreeNode.deleteNode";
    protected static final String METHOD_NAME_delete = "BinTreeNode.delete";
    protected static final String METHOD_NAME_traverse = "BinTreeNode.traverse";
    protected static final String METHOD_NAME_nextNode = "BinTreeNode.nextNode";
    // --------------------

    // ========== TEST DATA ==========
    private static final java.util.Random RANDOM = new java.util.Random(4711_0815_666L);

    protected static final int getRandomInt(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }

    protected static final String getRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((char) ((RANDOM.nextBoolean() ? 'a' : 'A') + RANDOM.nextInt(26)));
        }
        return sb.toString();
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
    public void pubTest__addX__isX__swap__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__getHeight__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__findNode_contains__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__insert__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__getMinNode_getMaxNode__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__deleteNode_delete__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__traverse__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    @Test(timeout = 666)
    public void pubTest__nextNode__Innereien__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien();
    }

    private void check__Innereien() {
        @SuppressWarnings("rawtypes")
        Class<BinTreeNode> clazz = BinTreeNode.class;
        assertSame("Du sollst genau eine bestimmte Super-Klasse haben!", AbstractBinTreeNode.class, clazz.getSuperclass());
        assertEquals("Du sollst keine Schnittstellen implementieren!", 0, clazz.getInterfaces().length);
        assertEquals("Du sollst keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
        assertEquals("Du sollst keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
        assertEquals("Du sollst keine Attribute haben!", 0, getDeclaredFields(clazz).length);
        Constructor<?>[] constructors = getDeclaredConstructors(clazz);
        assertEquals("Du sollst genau zwei >public< Konstruktoren haben!", 2, constructors.length);
        HashSet<String> abstractMethods = new HashSet<>();
        for (Method method : getDeclaredMethods(AbstractBinTreeNode.class)) {
            if (Modifier.isAbstract(method.getModifiers())) {
                abstractMethods.add(method.getName());
            }
        }
        for (Method method : getDeclaredMethods(clazz)) {
            if (!abstractMethods.contains(method.getName())) {
                assertTrue("Du sollst nur private Methoden ausser den vorgegebenen haben! (die passt nicht: " + method + ")", Modifier.isPrivate(method.getModifiers()));
            }
        }
    }

    // -------------------- addLeft/addRight/isLeftChild/isRightChild/swap --------------------
    @Test(timeout = 666)
    public void pubTest__addLeft__addRight() {
        for (int pass = 0; pass < 42; pass++) {
            AbstractBinTreeNode<String> n = new BinTreeNode<>("O" + getRandomString());
            String expectedLeft = "A" + getRandomString();
            String expectedRight = "Z" + getRandomString();
            n.addLeft(expectedLeft);
            n.addRight(expectedRight);
            assertNotNull(METHOD_NAME_addLeft + " failed", n.getLeft());
            assertNotNull(METHOD_NAME_addRight + " failed", n.getRight());
            String actualLeft = n.getLeft().getValue();
            String actualRight = n.getRight().getValue();
            assertEquals(METHOD_NAME_addLeft + " failed", expectedLeft, actualLeft);
            assertEquals(METHOD_NAME_addRight + " failed", expectedRight, actualRight);
            assertEquals(METHOD_NAME_addLeft + " failed", n, n.getLeft().getParent());
            assertEquals(METHOD_NAME_addRight + " failed", n, n.getRight().getParent());
        }
    }

    @Test(timeout = 666)
    public void pubTest__isLeftChild__isRightChild() {
        for (int pass = 0; pass < 42; pass++) {
            AbstractBinTreeNode<Integer> l = new BinTreeNode<Integer>(getRandomInt(-666, 42));
            AbstractBinTreeNode<Integer> r = new BinTreeNode<Integer>(getRandomInt(0x815, 4711));
            AbstractBinTreeNode<Integer> n = new BinTreeNode<Integer>(getRandomInt(666, 999), l, r);
            l.addLeft(getRandomInt(-0x815, -999));
            l.addRight(getRandomInt(123, 456));
            r.addRight(getRandomInt(4711_666, 4711_0815));
            assertSame(CONSTRUCTOR_NAME_T_BinTreeNode_BinTreeNode + " failed", l, n.getLeft());
            assertSame(CONSTRUCTOR_NAME_T_BinTreeNode_BinTreeNode + " failed", r, n.getRight());
            assertTrue(METHOD_NAME_isLeftChild + " failed", l.isLeftChild());
            assertFalse(METHOD_NAME_isLeftChild + " failed", n.isLeftChild());
            assertFalse(METHOD_NAME_isLeftChild + " failed", r.isLeftChild());

        }
    }

    @Test(timeout = 666)
    public void pubTest__swap() {
        for (int pass = 0; pass < 42; pass++) {
            Integer llVal = getRandomInt(-0x815, -999);
            Integer lVal = getRandomInt(-666, 42);
            Integer lrVal = getRandomInt(123, 456);
            Integer nVal = getRandomInt(666, 999);
            Integer rVal = getRandomInt(0x815, 4711);
            Integer rrVal = getRandomInt(4711_666, 4711_0815);
            AbstractBinTreeNode<Integer> l = new BinTreeNode<Integer>(lVal);
            AbstractBinTreeNode<Integer> r = new BinTreeNode<Integer>(rVal);
            AbstractBinTreeNode<Integer> n = new BinTreeNode<Integer>(nVal, l, r);
            assertSame(CONSTRUCTOR_NAME_T_BinTreeNode_BinTreeNode + " failed", l, n.getLeft());
            assertSame(CONSTRUCTOR_NAME_T_BinTreeNode_BinTreeNode + " failed", r, n.getRight());
            l.addLeft(llVal);
            l.addRight(lrVal);
            r.addRight(rrVal);
            AbstractBinTreeNode<Integer> ll = l.getLeft();
            AbstractBinTreeNode<Integer> lr = l.getRight();
            AbstractBinTreeNode<Integer> rr = r.getRight();
            l.swap(r);
            assertSame("structure wrong after " + METHOD_NAME_swap, l, n.getLeft());
            assertSame("structure wrong after " + METHOD_NAME_swap, r, n.getRight());
            assertSame("structure wrong after " + METHOD_NAME_swap, ll, l.getLeft());
            assertSame("structure wrong after " + METHOD_NAME_swap, lr, l.getRight());
            assertSame("structure wrong after " + METHOD_NAME_swap, rr, r.getRight());
            assertNull("structure wrong after " + METHOD_NAME_swap, n.getParent());
            assertSame("structure wrong after " + METHOD_NAME_swap, n, l.getParent());
            assertSame("structure wrong after " + METHOD_NAME_swap, n, r.getParent());
            assertSame("structure wrong after " + METHOD_NAME_swap, l, ll.getParent());
            assertSame("structure wrong after " + METHOD_NAME_swap, l, lr.getParent());
            assertSame("structure wrong after " + METHOD_NAME_swap, r, rr.getParent());
            assertEquals(METHOD_NAME_swap + " failed", llVal, ll.getValue());
            assertEquals(METHOD_NAME_swap + " failed", rVal, l.getValue());
            assertEquals(METHOD_NAME_swap + " failed", lrVal, lr.getValue());
            assertEquals(METHOD_NAME_swap + " failed", nVal, n.getValue());
            assertEquals(METHOD_NAME_swap + " failed", lVal, r.getValue());
            assertEquals(METHOD_NAME_swap + " failed", rrVal, rr.getValue());
        }
    }

    // -------------------- height --------------------
    @Test
    public void pubTest__getHeight() {
        for (int pass = 0; pass < 42; pass++) {
            AbstractBinTreeNode<String> ll = new BinTreeNode<>("A" + getRandomString());
            AbstractBinTreeNode<String> lr = new BinTreeNode<>("C" + getRandomString());
            AbstractBinTreeNode<String> l = new BinTreeNode<>("B" + getRandomString(), ll, lr);
            AbstractBinTreeNode<String> r = new BinTreeNode<>("E" + getRandomString());
            AbstractBinTreeNode<String> n = new BinTreeNode<>("D" + getRandomString(), l, r);
            r.addRight("F" + getRandomString());
            assertEquals(METHOD_NAME_getHeight + " failed", 0, ll.getHeight());
            assertEquals(METHOD_NAME_getHeight + " failed", 1, l.getHeight());
            assertEquals(METHOD_NAME_getHeight + " failed", 1, r.getHeight());
            assertEquals(METHOD_NAME_getHeight + " failed", 2, n.getHeight());
        }
    }

    // -------------------- findNode/contains --------------------
    @Test(timeout = 666)
    public void pubTest__findNode() {
        for (int pass = 0; pass < 42; pass++) {
            String llRnd, lRnd, lrRnd, nRnd, rRnd, rrRnd;
            String llVal = "B" + (llRnd = getRandomString());
            String lVal = "D" + (lRnd = getRandomString());
            String lrVal = "F" + (lrRnd = getRandomString());
            String nVal = "H" + (nRnd = getRandomString());
            String rVal = "J" + (rRnd = getRandomString());
            String rrVal = "L" + (rrRnd = getRandomString());
            AbstractBinTreeNode<String> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<String> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<String> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<String> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<String> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<String> n = new BinTreeNode<>(nVal, l, r);
            // ----------
            assertFalse("This should not fail, but show you something...", nVal == ("H" + nRnd));
            assertTrue("This should not fail, but show you something...", nVal.equals("H" + nRnd));
            assertTrue("This should not fail, but show you something...", nVal.compareTo("H" + nRnd) == 0);
            assertNotSame("This should not fail, but show you something...", nVal, ("H" + nRnd));
            assertEquals("This should not fail, but show you something...", nVal, ("H" + nRnd));
            assertEquals("This should not fail, but show you something...", 0, nVal.compareTo("H" + nRnd));
            // ----------
            assertSame(METHOD_NAME_findNode + " failed", ll, n.findNode("B" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", l, n.findNode("D" + lRnd));
            assertSame(METHOD_NAME_findNode + " failed", lr, n.findNode("F" + lrRnd));
            assertSame(METHOD_NAME_findNode + " failed", n, n.findNode("H" + nRnd));
            assertSame(METHOD_NAME_findNode + " failed", r, n.findNode("J" + rRnd));
            assertSame(METHOD_NAME_findNode + " failed", rr, n.findNode("L" + rrRnd));
            // ----------
            assertSame(METHOD_NAME_findNode + " failed", ll, n.findNode("A" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", ll, n.findNode("C" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", lr, n.findNode("E" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", lr, n.findNode("F" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", r, n.findNode("I" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", rr, n.findNode("K" + llRnd));
            assertSame(METHOD_NAME_findNode + " failed", rr, n.findNode("M" + llRnd));
        }
    }

    @Test(timeout = 666)
    public void pubTest__contains() {
        for (int pass = 0; pass < 42; pass++) {
            String llRnd, lRnd, lrRnd, nRnd, rRnd, rrRnd;
            String llVal = "B" + (llRnd = getRandomString());
            String lVal = "D" + (lRnd = getRandomString());
            String lrVal = "F" + (lrRnd = getRandomString());
            String nVal = "H" + (nRnd = getRandomString());
            String rVal = "J" + (rRnd = getRandomString());
            String rrVal = "L" + (rrRnd = getRandomString());
            AbstractBinTreeNode<String> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<String> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<String> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<String> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<String> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<String> n = new BinTreeNode<>(nVal, l, r);
            for (String s : new String[] { "B" + llRnd, "D" + lRnd, "F" + lrRnd, "H" + nRnd, "J" + rRnd, "L" + rrRnd }) {
                assertTrue(METHOD_NAME_contains + " failed", n.contains(s));
            }
            for (String s : new String[] { "A" + llRnd, "C" + llRnd, "E" + lrRnd, "G" + lrRnd, "I" + rRnd, "K" + rrRnd, "M" + rrRnd }) {
                assertFalse(METHOD_NAME_contains + " failed", n.contains(s));
            }
        }
    }

    // -------------------- insert --------------------
    @Test(timeout = 666)
    public void pubTest__insert() {
        for (int pass = 0; pass < 42; pass++) {
            String llRnd, lRnd, lrRnd, nRnd, rRnd, rrRnd;
            String llVal = "B" + (llRnd = getRandomString());
            String lVal = "D" + (lRnd = getRandomString());
            String lrVal = "F" + (lrRnd = getRandomString());
            String nVal = "H" + (nRnd = getRandomString());
            String rVal = "J" + (rRnd = getRandomString());
            String rrVal = "L" + (rrRnd = getRandomString());
            AbstractBinTreeNode<String> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<String> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<String> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<String> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<String> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<String> n = new BinTreeNode<>(nVal, l, r);
            assertTrue(METHOD_NAME_insert + " failed", n.insert("A" + llRnd));
            assertNotNull(METHOD_NAME_insert + " failed", ll.getLeft());
            assertEquals(METHOD_NAME_insert + " failed", "A" + llRnd, ll.getLeft().getValue());
            assertTrue(METHOD_NAME_insert + " failed", n.insert("C" + llRnd));
            assertNotNull(METHOD_NAME_insert + " failed", ll.getRight());
            assertEquals(METHOD_NAME_insert + " failed", "C" + llRnd, ll.getRight().getValue());
            assertTrue(METHOD_NAME_insert + " failed", n.insert("E" + lrRnd));
            assertNotNull(METHOD_NAME_insert + " failed", lr.getLeft());
            assertEquals(METHOD_NAME_insert + " failed", "E" + lrRnd, lr.getLeft().getValue());
            assertTrue(METHOD_NAME_insert + " failed", n.insert("G" + lrRnd));
            assertNotNull(METHOD_NAME_insert + " failed", lr.getRight());
            assertEquals(METHOD_NAME_insert + " failed", "G" + lrRnd, lr.getRight().getValue());
            assertTrue(METHOD_NAME_insert + " failed", n.insert("I" + rRnd));
            assertNotNull(METHOD_NAME_insert + " failed", r.getLeft());
            assertEquals(METHOD_NAME_insert + " failed", "I" + rRnd, r.getLeft().getValue());
            assertTrue(METHOD_NAME_insert + " failed", n.insert("K" + rrRnd));
            assertNotNull(METHOD_NAME_insert + " failed", lr.getLeft());
            assertEquals(METHOD_NAME_insert + " failed", "K" + rrRnd, rr.getLeft().getValue());
            assertTrue(METHOD_NAME_insert + " failed", n.insert("M" + rrRnd));
            assertNotNull(METHOD_NAME_insert + " failed", rr.getRight());
            assertEquals(METHOD_NAME_insert + " failed", "M" + rrRnd, rr.getRight().getValue());
            assertFalse(METHOD_NAME_insert + " failed", n.insert("D" + lRnd));
            assertFalse(METHOD_NAME_insert + " failed", n.insert("H" + nRnd));
            assertSame("structure wrong after " + METHOD_NAME_insert, l, n.getLeft());
            assertSame("structure wrong after " + METHOD_NAME_insert, r, n.getRight());
            assertSame("structure wrong after " + METHOD_NAME_insert, ll, l.getLeft());
            assertSame("structure wrong after " + METHOD_NAME_insert, lr, l.getRight());
            assertSame("structure wrong after " + METHOD_NAME_insert, rr, r.getRight());
            assertNull("structure wrong after " + METHOD_NAME_insert, n.getParent());
            assertSame("structure wrong after " + METHOD_NAME_insert, n, l.getParent());
            assertSame("structure wrong after " + METHOD_NAME_insert, n, r.getParent());
            assertSame("structure wrong after " + METHOD_NAME_insert, l, ll.getParent());
            assertSame("structure wrong after " + METHOD_NAME_insert, l, lr.getParent());
            assertSame("structure wrong after " + METHOD_NAME_insert, r, rr.getParent());
        }
    }

    // -------------------- getMinNode/getMaxNode --------------------
    @Test
    public void pubTest__getMinNode__getMaxNode() {
        for (int pass = 0; pass < 42; pass++) {
            AbstractBinTreeNode<String> ll = new BinTreeNode<>("B" + getRandomString());
            AbstractBinTreeNode<String> lr = new BinTreeNode<>("F" + getRandomString());
            AbstractBinTreeNode<String> l = new BinTreeNode<>("D" + getRandomString(), ll, lr);
            AbstractBinTreeNode<String> rr = new BinTreeNode<>("L" + getRandomString());
            AbstractBinTreeNode<String> r = new BinTreeNode<>("J" + getRandomString(), null, rr);
            AbstractBinTreeNode<String> n = new BinTreeNode<>("H" + getRandomString(), l, r);
            assertSame(METHOD_NAME_getMinNode + " failed", ll, n.getMinNode());
            assertSame(METHOD_NAME_getMaxNode + " failed", rr, n.getMaxNode());
            assertSame(METHOD_NAME_getMinNode + " failed", ll, l.getMinNode());
            assertSame(METHOD_NAME_getMaxNode + " failed", lr, l.getMaxNode());
            assertSame(METHOD_NAME_getMinNode + " failed", r, r.getMinNode());
            assertSame(METHOD_NAME_getMaxNode + " failed", rr, r.getMaxNode());
        }
    }

    // -------------------- deleteNode/delete --------------------
    @Test(timeout = 666)
    public void pubTest__deleteNode() {
        // ______H_______
        // _____/_\_____
        // ____D___J____
        // ___/_\___\___
        // __B___F___L__
        for (int pass = 0; pass < 42; pass++) {
            String llRnd, lRnd, lrRnd, nRnd, rRnd, rrRnd;
            String llVal = "B" + (llRnd = getRandomString());
            String lVal = "D" + (lRnd = getRandomString());
            String lrVal = "F" + (lrRnd = getRandomString());
            String nVal = "H" + (nRnd = getRandomString());
            String rVal = "J" + (rRnd = getRandomString());
            String rrVal = "L" + (rrRnd = getRandomString());
            AbstractBinTreeNode<String> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<String> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<String> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<String> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<String> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<String> n = new BinTreeNode<>(nVal, l, r);
            if (pass < 15) {
                assertNull(METHOD_NAME_deleteNode + " failed", ll.deleteNode());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, n.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, n.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, l.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, lr, l.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, rr, r.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, n.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, l.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, r.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, lr.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, rr.getParent());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "D" + lRnd, l.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "F" + lrRnd, lr.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "H" + nRnd, n.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "J" + rRnd, r.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "L" + rrRnd, rr.getValue());
            } else if (pass < 30) {
                assertNotNull(METHOD_NAME_deleteNode + " failed", l.deleteNode());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, n.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, n.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, ll, l.getLeft());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, l.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, rr, r.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, n.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, l.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, r.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, ll.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, lr.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, rr.getParent());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "B" + llRnd, ll.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "F" + lrRnd, l.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "H" + nRnd, n.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "J" + rRnd, r.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "L" + rrRnd, rr.getValue());
            } else {
                assertNotNull(METHOD_NAME_deleteNode + " failed", n.deleteNode());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, n.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, rr, n.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, ll, l.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, lr, l.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, n.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, l.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, ll.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, lr.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, rr.getParent());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "B" + llRnd, ll.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "D" + lRnd, l.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "F" + lrRnd, lr.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "J" + rRnd, n.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "L" + rrRnd, rr.getValue());
            }
        }
    }

    @Test(timeout = 666)
    public void pubTest__delete() {
        for (int pass = 0; pass < 42; pass++) {
            String llRnd, lRnd, lrRnd, nRnd, rRnd, rrRnd;
            String llVal = "B" + (llRnd = getRandomString());
            String lVal = "D" + (lRnd = getRandomString());
            String lrVal = "F" + (lrRnd = getRandomString());
            String nVal = "H" + (nRnd = getRandomString());
            String rVal = "J" + (rRnd = getRandomString());
            String rrVal = "L" + (rrRnd = getRandomString());
            AbstractBinTreeNode<String> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<String> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<String> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<String> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<String> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<String> n = new BinTreeNode<>(nVal, l, r);
            if (pass < 15) {
                n.delete("B" + llRnd);
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, n.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, n.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, l.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, lr, l.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, rr, r.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, n.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, l.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, r.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, lr.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, rr.getParent());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "D" + lRnd, l.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "F" + lrRnd, lr.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "H" + nRnd, n.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "J" + rRnd, r.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "L" + rrRnd, rr.getValue());
            } else if (pass < 30) {
                n.delete("D" + lRnd);
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, n.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, n.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, ll, l.getLeft());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, l.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, rr, r.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, n.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, l.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, r.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, ll.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, lr.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, r, rr.getParent());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "B" + llRnd, ll.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "F" + lrRnd, l.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "H" + nRnd, n.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "J" + rRnd, r.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "L" + rrRnd, rr.getValue());
            } else {
                n.delete("H" + nRnd);
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, n.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, rr, n.getRight());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, ll, l.getLeft());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, lr, l.getRight());
                assertNull("structure wrong after " + METHOD_NAME_deleteNode, n.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, l.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, ll.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, l, lr.getParent());
                assertSame("structure wrong after " + METHOD_NAME_deleteNode, n, rr.getParent());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "B" + llRnd, ll.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "D" + lRnd, l.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "F" + lrRnd, lr.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "J" + rRnd, n.getValue());
                assertEquals("structure wrong after " + METHOD_NAME_deleteNode, "L" + rrRnd, rr.getValue());
            }
        }
    }

    // -------------------- traverse --------------------
    @Test(timeout = 666)
    public void pubTest__traverse() {
        for (int pass = 0; pass < 42; pass++) {
            Integer llVal = getRandomInt(-0x815, -999);
            Integer lVal = getRandomInt(-666, 42);
            Integer lrVal = getRandomInt(123, 456);
            Integer nVal = getRandomInt(666, 999);
            Integer rVal = getRandomInt(0x815, 4711);
            Integer rrVal = getRandomInt(4711_666, 4711_0815);
            AbstractBinTreeNode<Integer> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<Integer> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<Integer> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<Integer> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<Integer> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<Integer> n = new BinTreeNode<>(nVal, l, r);
            assertEquals(METHOD_NAME_traverse + "(PRE) failed", Arrays.asList(new Integer[] { nVal, lVal, llVal, lrVal, rVal, rrVal }), n.traverse(TreeTraversalOrderType.PRE));
            assertEquals(METHOD_NAME_traverse + "(IN) failed", Arrays.asList(new Integer[] { llVal, lVal, lrVal, nVal, rVal, rrVal }), n.traverse(TreeTraversalOrderType.IN));
            assertEquals(METHOD_NAME_traverse + "(POST) failed", Arrays.asList(new Integer[] { llVal, lrVal, lVal, rrVal, rVal, nVal }), n.traverse(TreeTraversalOrderType.POST));
        }
    }

    // -------------------- nextNode --------------------
    @Test(timeout = 666)
    public void pubTest__nextNode() {
        for (int pass = 0; pass < 42; pass++) {
            Integer llVal = getRandomInt(-0x815, -999);
            Integer lVal = getRandomInt(-666, 42);
            Integer lrVal = getRandomInt(123, 456);
            Integer nVal = getRandomInt(666, 999);
            Integer rVal = getRandomInt(0x815, 4711);
            Integer rrVal = getRandomInt(4711_666, 4711_0815);
            AbstractBinTreeNode<Integer> ll = new BinTreeNode<>(llVal);
            AbstractBinTreeNode<Integer> lr = new BinTreeNode<>(lrVal);
            AbstractBinTreeNode<Integer> l = new BinTreeNode<>(lVal, ll, lr);
            AbstractBinTreeNode<Integer> rr = new BinTreeNode<>(rrVal);
            AbstractBinTreeNode<Integer> r = new BinTreeNode<>(rVal, null, rr);
            AbstractBinTreeNode<Integer> n = new BinTreeNode<>(nVal, l, r);
            AbstractBinTreeNode<?>[] nodes = { ll, l, lr, n, r, rr };
            for (int i = 0; i < nodes.length - 1; i++) {
                assertSame(METHOD_NAME_nextNode + " failed", nodes[i + 1], nodes[i].nextNode());
            }
            assertNull(METHOD_NAME_nextNode + " failed", nodes[nodes.length - 1].nextNode());
        }
    }
}