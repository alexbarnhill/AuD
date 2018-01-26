import java.util.*;

/**
 * Factory class for creating graph iterators.
 *
 * <p>
 * Graph iterators created by factories implementing this interface iterate over the nodes of a graph in proper sequence as requested (e.g. <b>BFS</b> or <b>DFS</b>).
 *
 * Adjacent nodes of the graph node returned by the last call to {@link java.util.Iterator#next() next()} are always provided in the order they were added to the adjacency list (unless they have already been processed).
 *
 * The iterators always start at the first node of the graphs node list. If the graph is <i>disconnected or weakly connected</i>, the nodes of the <b>remaining components</b> are provided in the order their nodes appear in the graphs node list (unless they have already been processed).
 * </p>
 *
 * <p>
 * The nodes provided by the iterator reflect the state of the graph at the time, the factory method was called.
 *
 * If the graph is structurally modified at any time after the iterator is created, the view of the iterator remains unchanged. Note that this behavior is <b>neither thread-safe nor fail-fast</b> !
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Factory method pattern</a>
 * @see {@link java.util.Iterator Iterator}
 *
 */
public interface IGraphIteratorFactory {
    /**
     * Creates a new graph iterator that provides the graph nodes in <b>breadth-first-search</b> order.
     *
     * @param graph
     *            - the graph to iterate in <b>BFS</b> order
     * @return an iterator over the nodes in the graph in proper <b>BFS</b> sequence
     */
    public Iterator<GraphNode> createGraphIteratorBFS(List<GraphNode> graph);

    /**
     * Creates a new graph iterator that provides the graph nodes in <b>depth-first-search</b> order.
     *
     * @param graph
     *            - the graph to iterate in <b>DFS</b> order
     * @return an iterator over the nodes in the graph in proper <b>DFS</b> sequence
     */
    public Iterator<GraphNode> createGraphIteratorDFS(List<GraphNode> graph);
}