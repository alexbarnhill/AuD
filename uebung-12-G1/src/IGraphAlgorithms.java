import java.util.*;

public interface IGraphAlgorithms {
    /**
     * <p>
     * Graphs represented using {@link GraphNode} instances always have directed edges due to the inherent representation of the adjacency relationship.
     *
     * In order to easily represent undirected graphs, one might use <i>"bidirection"</i>, i.e. for each edge (v,w) there is also an edge (w,v) in the graph, connecting the same nodes in opposite direction.
     *
     * A graph without any edges at all is said to be undirected.
     * </p>
     * <p>
     * More formally: This method returns {@literal false} iff the graph provided as argument has no edge at all or is entirely <i>bidirected</i>.
     * </p>
     *
     * @param graph
     *            - the graph to be classified
     * @return {@literal true} if the {@code graph} has at least one edge and is truly directed (i.e. not completely <i>bidirected</i>)
     */
    public boolean isDirected(List<GraphNode> graph);

    /**
     * A <i>root</i> node of an arbitrary graph is a node that cannot be reached from any other node of that graph.
     *
     * In undirected connected graphs, such a node exists iff the graph contains only that node.
     *
     * Empty graphs and directed graphs containing exactly one cycle have no root node at all.
     *
     * A directed graph containing several weakly or unconnected components may have <b>several</b> <i>roots</i>.
     *
     * @see {@link #isDirected(List)}
     * @see {@link GraphConnectivityType#WEAKLY_CONNECTED}, {@link GraphConnectivityType#DISCONNECTED}
     * @param graph
     *            - the graph of which to determine the root node(s)
     * @return a {@link java.util#Collection} of {@link GraphNode} instances containing exactly the root node(s) of the {@code graph}
     */
    public Collection<GraphNode> getRoots(List<GraphNode> graph);

    /**
     * This method identifies and returns the list of all <b>weakly connected components</b> of a graph.
     *
     * Each component itself is represented as the list of nodes that belong exactly to the corresponding component.
     *
     * In order to identify a <b>weakly connected component</b>, one might virtually replace each directed edge by an undirected edge and search the graph for connected components.
     *
     * A connected component is a maximum subgraph containing exactly those nodes of the original graph, that are directly or indirectly connected to each other.
     *
     * @see {@link GraphConnectivityType#WEAKLY_CONNECTED}
     * @param graph
     *            - the graph of which to determine the <b>(weakly connected) components</b>
     * @return the list of weakly connected components, where each component is the list of nodes belonging to that component
     */
    public List<List<GraphNode>> getComponents(List<GraphNode> graph);

    /**
     * Returns a list of nodes representing an arbitrary <b>simple</b> cycle in the graph, if there exists one ({@literal null} otherwise).
     *
     * This lists <b>starts and ends with the same node</b>. Consecutive nodes in the list must also occur consecutively in the graph, i.e. they must be connected with exactly one (eventually <i>bidirected</i>) edge in the direction of occurence.
     *
     * Except for the first and the last node in the list/cycle, all nodes in the list must be <b>pairwise distinct</b> (by definition of <b>simple</b> cycle).
     *
     * @param graph
     *            - the graph to search for an arbitrary <b>simple</b> cycle
     * @return an arbitrary <b>simple</b> cycle in the {@code graph} if any, {@literal null} otherwise
     */
    public List<GraphNode> getACycle(List<GraphNode> graph);

    /**
     * Determines and returns the type of connectivity of a graph.
     *
     * @param graph
     *            - the graph whose connectivity type is to be determined
     * @return the type of the connectivity of the {@code graph}
     */
    public GraphConnectivityType classifyConnectivity(List<GraphNode> graph);
}