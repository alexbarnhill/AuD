import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class GraphAlgorithmsPublicTest {
    // ========== SYSTEM ==========
    protected static final String EX_NAME_isDirected = "isDirected";
    protected static final String EX_NAME_getRoots = "getRoots";
    protected static final String EX_NAME_getComponents = "getComponents";
    protected static final String EX_NAME_getACycle = "getACycle";
    protected static final String EX_NAME_classifyConnectivity = "classifyConnectivity";
    protected static final String EX_NAME_createGraphIteratorBFS = "createGraphIteratorBFS";
    protected static final String EX_NAME_createGraphIteratorDFS = "createGraphIteratorDFS";
    // --------------------

    // ========== TEST DATA ==========
    protected static final IGraphAlgorithms GRAPH_ALGORITHMS = new GraphAlgorithms();
    protected static final IGraphIteratorFactory GRAPH_ITERATOR_FACTORY = new GraphIteratorFactory();

    protected static final Comparator<GraphNode> GRAPH_NODE_COMPARATOR = new Comparator<GraphNode>() {
        @Override
        public int compare(GraphNode o1, GraphNode o2) {
            return o1.toString().compareTo(o2.toString());
        }
    };

    protected static final Comparator<List<GraphNode>> GRAPH_NODE_LIST_COMPARATOR = new Comparator<List<GraphNode>>() {
        @Override
        public int compare(List<GraphNode> o1, List<GraphNode> o2) {
            return o1.toString().compareTo(o2.toString());
        }
    };

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
    // -------------------- Innereien - GraphAlgorithms --------------------
    @Test(timeout = 666)
    public void pubTest__isDirected__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphAlgorithms();
    }

    @Test(timeout = 666)
    public void pubTest__getRoots__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphAlgorithms();
    }

    @Test(timeout = 666)
    public void pubTest__getComponents__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphAlgorithms();
    }

    @Test(timeout = 666)
    public void pubTest__getACycle__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphAlgorithms();
    }

    @Test(timeout = 666)
    public void pubTest__classifyConnectivity__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphAlgorithms();
    }

    private void check__Innereien__GraphAlgorithms() {
        Class<GraphAlgorithms> clazz = GraphAlgorithms.class;
        assertSame("Du sollst genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
        assertEquals("Du sollst >genau eine< bestimmte Schnittstelle implementieren!", 1, clazz.getInterfaces().length);
        assertSame("Du sollst genau eine >bestimmte< Schnittstelle implementieren!", IGraphAlgorithms.class, clazz.getInterfaces()[0]);
        assertEquals("Du sollst keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
        assertEquals("Du sollst keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
        assertEquals("Du sollst keine Attribute haben!", 0, getDeclaredFields(clazz).length);
        Constructor<?>[] constructors = getDeclaredConstructors(clazz);
        assertEquals("Du sollst genau einen Konstruktor haben (und zwar den >public< default cons)!", 1, constructors.length);
        HashSet<String> abstractMethods = new HashSet<>();
        for (Method method : getDeclaredMethods(IGraphAlgorithms.class)) {
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

    // -------------------- Innereien - GraphIteratorFactory --------------------
    @Test(timeout = 666)
    public void pubTest__createGraphIteratorBFS__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphIteratorFactory();
    }

    @Test(timeout = 666)
    public void pubTest__createGraphIteratorDFS__Innereien__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
        check__Innereien__GraphIteratorFactory();
    }

    private void check__Innereien__GraphIteratorFactory() {
        Class<GraphIteratorFactory> clazz = GraphIteratorFactory.class;
        assertSame("Du sollst genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
        assertEquals("Du sollst >genau eine< bestimmte Schnittstelle implementieren!", 1, clazz.getInterfaces().length);
        assertSame("Du sollst genau eine >bestimmte< Schnittstelle implementieren!", IGraphIteratorFactory.class, clazz.getInterfaces()[0]);
        assertEquals("Du sollst keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
        assertEquals("Du sollst keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
        assertEquals("Du sollst keine Attribute haben!", 0, getDeclaredFields(clazz).length);
        Constructor<?>[] constructors = getDeclaredConstructors(clazz);
        assertEquals("Du sollst genau einen Konstruktor haben (und zwar den >public< default cons)!", 1, constructors.length);
        HashSet<String> abstractMethods = new HashSet<>();
        for (Method method : getDeclaredMethods(IGraphIteratorFactory.class)) {
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

    // -------------------- isDirected --------------------
    @Test(timeout = 666)
    public void pubTest__isDirected() {
        GraphNode A, B, C;
        for (int pass = 0; pass <= 2; pass++) {
            List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A = new GraphNode("\u1F600"), B = new GraphNode("\u1F600"), C = new GraphNode("\u1F600")));
            if (pass <= 0) {
                A.addBidirectedEdge(B);
                B.addEdge(C);
                assertTrue("A <=> B => C", GRAPH_ALGORITHMS.isDirected(graph));
            } else if (pass <= 1) {
                B.addEdge(A);
                B.addBidirectedEdge(C);
                assertTrue("A <= B <=> C", GRAPH_ALGORITHMS.isDirected(graph));
            } else if (pass <= 2) {
                A.addBidirectedEdge(B);
                B.addBidirectedEdge(C);
                assertFalse("A <=> B <=> C", GRAPH_ALGORITHMS.isDirected(graph));
            }
        }
    }

    // -------------------- getRoots --------------------
    @Test(timeout = 666)
    public void pubTest__getRoots() {
        GraphNode A, B, C;
        for (int pass = 0; pass <= 2; pass++) {
            List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C")));
            if (pass <= 0) {
                A.addEdge(B);
                B.addBidirectedEdge(C);
                check__getRoots("A => B <=> C", graph, Arrays.asList(A));
            } else if (pass <= 1) {
                B.addEdge(A);
                B.addEdge(C);
                check__getRoots("A <= B => C", graph, Arrays.asList(B));
            } else if (pass <= 2) {
                A.addEdge(B);
                C.addEdge(B);
                check__getRoots("A => B <= C", graph, Arrays.asList(A, C));
            }
        }
    }

    protected static final void check__getRoots(String graphString, List<GraphNode> graph, Collection<GraphNode> expectedRootsRaw) {
        Collection<GraphNode> actualRootsRaw = GRAPH_ALGORITHMS.getRoots(graph);
        assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_getRoots + " failed for " + graphString, actualRootsRaw);
        List<GraphNode> actualRootsSorted = new ArrayList<>(actualRootsRaw);
        Collections.sort(actualRootsSorted, GRAPH_NODE_COMPARATOR);
        List<GraphNode> expectedRootsSorted = new ArrayList<>(expectedRootsRaw);
        Collections.sort(expectedRootsSorted, GRAPH_NODE_COMPARATOR);
        assertEquals(GraphAlgorithmsPublicTest.EX_NAME_getRoots + " failed for " + graphString, expectedRootsSorted, actualRootsSorted);
    }

    // -------------------- getComponents --------------------
    @Test(timeout = 666)
    public void pubTest__getComponents() {
        for (int pass = 0; pass <= 2; pass++) {
            if (pass <= 0) {
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                A.addBidirectedEdge(B);
                B.addBidirectedEdge(C);
                C.addBidirectedEdge(D);
                E.addBidirectedEdge(F);
                F.addBidirectedEdge(G);
                G.addBidirectedEdge(H);
                check__getComponents("A<=>B<=>C<=>D | E<=>F<=>G<=>H", graph, Arrays.asList(Arrays.asList(A, B, C, D), Arrays.asList(E, F, G, H)));
            } else if (pass <= 1) {
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                A.addBidirectedEdge(B);
                B.addBidirectedEdge(G);
                D.addBidirectedEdge(C);
                D.addBidirectedEdge(E);
                D.addBidirectedEdge(F);
                C.addBidirectedEdge(E);
                C.addBidirectedEdge(F);
                E.addBidirectedEdge(F);
                check__getComponents("A<=>B<=>G | D<=>{C,E,F} + C<=>{E,F} + E<=>F | H", graph, Arrays.asList(Arrays.asList(A, B, G), Arrays.asList(C, D, E, F), Arrays.asList(H)));
            } else if (pass <= 2) {
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                A.addEdge(B);
                B.addEdge(C);
                D.addEdge(B);
                B.addEdge(E);
                G.addEdge(F);
                G.addEdge(H);
                check__getComponents("A=>B=>C + D=>B=>E | F<=G=>H", graph, Arrays.asList(Arrays.asList(A, B, C, D, E), Arrays.asList(F, G, H)));
            }
        }
    }

    protected static final void check__getComponents(String graphString, List<GraphNode> graph, List<List<GraphNode>> expectedComponentsRaw) {
        List<List<GraphNode>> actualComponentsRaw = GRAPH_ALGORITHMS.getComponents(graph);
        assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_getComponents + " failed for " + graphString, actualComponentsRaw);
        List<List<GraphNode>> actualComponents = new ArrayList<>();
        for (List<GraphNode> actualComponentRaw : actualComponentsRaw) {
            assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_getComponents + " failed for " + graphString, actualComponentRaw);
            List<GraphNode> actualComponent = new ArrayList<>(actualComponentRaw);
            Collections.sort(actualComponent, GRAPH_NODE_COMPARATOR);
            actualComponents.add(actualComponent);
        }
        Collections.sort(actualComponents, GRAPH_NODE_LIST_COMPARATOR);
        List<List<GraphNode>> expectedComponents = new ArrayList<>();
        for (List<GraphNode> expectedComponentRaw : expectedComponentsRaw) {
            List<GraphNode> expectedComponent = new ArrayList<>(expectedComponentRaw);
            Collections.sort(expectedComponent, GRAPH_NODE_COMPARATOR);
            expectedComponents.add(expectedComponent);
        }
        Collections.sort(expectedComponents, GRAPH_NODE_LIST_COMPARATOR);
        assertTrue(GraphAlgorithmsPublicTest.EX_NAME_getComponents + " failed for " + graphString, expectedComponents.equals(actualComponents));
    }

    // -------------------- getACycle --------------------
    @Test(timeout = 666)
    public void pubTest__getACycle() {
        GraphNode A, B, C;
        for (int pass = 0; pass <= 3; pass++) {
            List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A = new GraphNode("\u1F600"), B = new GraphNode("\u1F600"), C = new GraphNode("\u1F600")));
            if (pass <= 0) {
                A.addEdge(B);
                B.addEdge(C);
                C.addEdge(A);
                check__getACycle("A=>B=>C=>A", graph, true);
            } else if (pass <= 1) {
                A.addEdge(B);
                B.addEdge(C);
                A.addEdge(C);
                check__getACycle("A=>{B,C} + B=>C (DAG)", graph, false);
            } else if (pass <= 2) {
                B.addBidirectedEdge(C);
                check__getACycle("A | B<=>C", graph, true);
            } else if (pass <= 3) {
                B.addEdge(C);
                check__getACycle("A | B=>C", graph, false);
            }
        }
    }

    public static void check__getACycle(String graphString, List<GraphNode> graph, boolean hasCycle) {
        List<GraphNode> actualCycleList = GRAPH_ALGORITHMS.getACycle(graph);
        if (!hasCycle) {
            assertNull(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assertNull", actualCycleList);
        } else {
            assertTrue(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assert at least three nodes", actualCycleList != null && actualCycleList.size() >= 3);
            assertSame(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assert cycle (first = last)", actualCycleList.get(0), actualCycleList.get(actualCycleList.size() - 1));
            Set<GraphNode> actualCycleSet = new HashSet<>(actualCycleList);
            assertEquals(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assert primitive cycle", actualCycleSet.size(), actualCycleList.size() - 1);
            for (int i = 1; i < actualCycleList.size(); i++) {
                GraphNode p = actualCycleList.get(i - 1);
                GraphNode n = actualCycleList.get(i);
                assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assertNotNull consecutive nodes", p);
                assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assertNotNull consecutive nodes", n);
                assertTrue(GraphAlgorithmsPublicTest.EX_NAME_getACycle + " failed for " + graphString + "  @ assert nodes are consecutive", p.getAdjacentNodes().contains(n));
            }
        }
    }

    // -------------------- classifyConnectivity --------------------
    @Test(timeout = 666)
    public void pubTest__classifyConnectivity() {
        GraphNode A = new GraphNode("\u1F600"), B = new GraphNode("\u1F600"), C = new GraphNode("\u1F600"), D = new GraphNode("\u1F600");
        List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D));
        A.addBidirectedEdge(B);
        B.addEdge(C);
        C.addBidirectedEdge(D);
        assertEquals("A<=>B=>C<=>D", GraphConnectivityType.WEAKLY_CONNECTED, GRAPH_ALGORITHMS.classifyConnectivity(graph));
    }

    // -------------------- createGraphIteratorBFS --------------------
    @Test
    public void pubTest__createGraphIteratorBFS() {
        for (int pass = 0; pass <= 2; pass++) {
            if (pass <= 0) {
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                // _____________A______________
                // _____________|______________
                // _____________v______________
                // ____/------> B -------\_____
                // ____|________|________|_____
                // ____|________v________|_____
                // ____|__/---> C ----\__|_____
                // ____|__|____/_\____|__|_____
                // ____|__|___v___v___|__|_____
                // ____|__|__D_____E__|__|_____
                // ____|__|___\___/___|__|_____
                // ____|__|____v_v____|__|_____
                // ____|__|_____F_____|__|_____
                // ____|__\----/______|__|_____
                // ____|______________|__|_____
                // ____|_______ G <---/__|_____
                // ____\-------/ ________|_____
                // ______________________|_____
                // _____________H <------/_____
                A.addEdge(B);
                B.addEdge(C);
                B.addEdge(H);
                C.addEdge(D);
                C.addEdge(E);
                C.addEdge(G);
                D.addEdge(F);
                E.addEdge(F);
                F.addEdge(C);
                G.addEdge(B);
                check__createGraphIteratorBFS("1) A=>B | B=>{C,H} | C=>{D,E,G} | D=>F | E=>F | F=>C | G=>B", graph, Arrays.asList(A, B, C, H, D, E, G, F));
            } else if (pass <= 1) {
                // almost as the initial one above... but slightly different ;)
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                A.addEdge(B);
                B.addEdge(H); // C -
                B.addEdge(C); // H -
                C.addEdge(G); // - D
                C.addEdge(D); // - E
                C.addEdge(E); // - G
                D.addEdge(F);
                E.addEdge(F);
                F.addEdge(C);
                G.addEdge(B);
                check__createGraphIteratorBFS("2) A=>B | B=>{H,C} | C=>{G,D,E} | D=>F | E=>F | F=>C | G=>B", graph, Arrays.asList(A, B, H, C, G, D, E, F));
            } else if (pass <= 2) {
                // almost as the initial one above... but slightly different ;)
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(H, E, B, A, C, D, F, G)); // (A, B, C, D, E, F, G, H)
                A.addEdge(B);
                B.addEdge(C);
                B.addEdge(H);
                C.addEdge(D);
                C.addEdge(E);
                C.addEdge(G);
                D.addEdge(F);
                E.addEdge(F);
                F.addEdge(C);
                G.addEdge(B);
                check__createGraphIteratorBFS("3) A=>B | B=>{C,H} | C=>{D,E,G} | D=>F | E=>F | F=>C | G=>B", graph, Arrays.asList(H, E, F, C, D, G, B, A));
            }
        }
    }

    protected static final void check__createGraphIteratorBFS(String graphString, List<GraphNode> graph, List<GraphNode> expectedSequenceRaw) {
        Iterator<GraphNode> graphIterator = GRAPH_ITERATOR_FACTORY.createGraphIteratorBFS(graph);
        assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorBFS + " failed for " + graphString + "  @ assertNotNull create", graphIterator);
        for (GraphNode nodeExpected : expectedSequenceRaw) {
            assertTrue(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorBFS + " failed for " + graphString + "  @ assertTrue hasNext", graphIterator.hasNext());
            GraphNode nodeActual = graphIterator.next();
            assertSame(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorBFS + " failed for " + graphString + "  @ assertSame next", nodeExpected, nodeActual);
        }
        assertFalse(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorBFS + " failed for " + graphString + "  @ assertFalse hasNext", graphIterator.hasNext());
    }

    // -------------------- createGraphIteratorDFS --------------------
    @Test(timeout = 666)
    public void pubTest__createGraphIteratorDFS() {
        for (int pass = 0; pass <= 2; pass++) {
            if (pass <= 0) {
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                // _____________A______________
                // _____________|______________
                // _____________v______________
                // ____/------> B -------\_____
                // ____|________|________|_____
                // ____|________v________|_____
                // ____|__/---> C ----\__|_____
                // ____|__|____/_\____|__|_____
                // ____|__|___v___v___|__|_____
                // ____|__|__D_____E__|__|_____
                // ____|__|___\___/___|__|_____
                // ____|__|____v_v____|__|_____
                // ____|__|_____F_____|__|_____
                // ____|__\----/______|__|_____
                // ____|______________|__|_____
                // ____|_______ G <---/__|_____
                // ____\-------/ ________|_____
                // ______________________|_____
                // _____________H <------/_____
                A.addEdge(B);
                B.addEdge(C);
                B.addEdge(H);
                C.addEdge(D);
                C.addEdge(E);
                C.addEdge(G);
                D.addEdge(F);
                E.addEdge(F);
                F.addEdge(C);
                G.addEdge(B);
                check__createGraphIteratorDFS("1) A=>B | B=>{C,H} | C=>{D,E,G} | D=>F | E=>F | F=>C | G=>B", graph, Arrays.asList(A, B, C, D, F, E, G, H));
            } else if (pass <= 1) {
                // almost as the initial one above... but slightly different ;)
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(A, B, C, D, E, F, G, H));
                A.addEdge(B);
                B.addEdge(H); // C -
                B.addEdge(C); // H -
                C.addEdge(G); // - D
                C.addEdge(D); // - E
                C.addEdge(E); // - G
                D.addEdge(F);
                E.addEdge(F);
                F.addEdge(C);
                G.addEdge(B);
                check__createGraphIteratorDFS("2) A=>B | B=>{H,C} | C=>{G,D,E} | D=>F | E=>F | F=>C | G=>B", graph, Arrays.asList(A, B, H, C, G, D, F, E));
            } else if (pass <= 2) {
                // almost as the initial one above... but slightly different ;)
                GraphNode A = new GraphNode("A"), B = new GraphNode("B"), C = new GraphNode("C"), D = new GraphNode("D"), E = new GraphNode("E"), F = new GraphNode("F"), G = new GraphNode("G"), H = new GraphNode("H");
                List<GraphNode> graph = Collections.unmodifiableList(Arrays.asList(H, E, B, A, C, D, F, G)); // (A, B, C, D, E, F, G, H)
                A.addEdge(B);
                B.addEdge(C);
                B.addEdge(H);
                C.addEdge(D);
                C.addEdge(E);
                C.addEdge(G);
                D.addEdge(F);
                E.addEdge(F);
                F.addEdge(C);
                G.addEdge(B);
                check__createGraphIteratorDFS("3) A=>B | B=>{C,H} | C=>{D,E,G} | D=>F | E=>F | F=>C | G=>B", graph, Arrays.asList(H, E, F, C, D, G, B, A));
            }
        }
    }

    protected static final void check__createGraphIteratorDFS(String graphString, List<GraphNode> graph, List<GraphNode> expectedSequenceRaw) {
        Iterator<GraphNode> graphIterator = GRAPH_ITERATOR_FACTORY.createGraphIteratorDFS(graph);
        assertNotNull(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorDFS + " failed for " + graphString + "  @ assertNotNull create", graphIterator);
        for (GraphNode nodeExpected : expectedSequenceRaw) {
            assertTrue(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorDFS + " failed for " + graphString + "  @ assertTrue hasNext", graphIterator.hasNext());
            GraphNode nodeActual = graphIterator.next();
            assertSame(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorDFS + " failed for " + graphString + "  @ assertSame next", nodeExpected, nodeActual);
        }
        assertFalse(GraphAlgorithmsPublicTest.EX_NAME_createGraphIteratorDFS + " failed for " + graphString + "  @ assertFalse hasNext", graphIterator.hasNext());
    }
}