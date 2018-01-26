import java.util.*;

/**
 * Represents a simple labelled graph node (without self loops) by means of an adjacency list.
 *
 */
public final class GraphNode {
    private final String label;
    private final List<GraphNode> adjacentNodes = new LinkedList<>();

    /**
     * Creates a new labelled node. Note that different nodes might have the same label.
     *
     * @param label
     *            - the label attached to his node
     */
    public GraphNode(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Label should not be null!");
        }
        this.label = label;
    }

    /**
     * Adds a simple directed edge from this node to the other node provided as argument by adding the other node to the adjacency list of this node.
     *
     * @param other
     *            - the target node of the new edge
     */
    public void addEdge(GraphNode other) {
        if (other == null) {
            throw new NullPointerException("Target node must not be null!");
        } else if (other == this) {
            throw new IllegalArgumentException("Single node cycles are not allowed here!");
        }
        adjacentNodes.add(other);
    }

    /**
     * Adds a <i>"bidirected"</i> edge that might be used as a replacement for undirected edges depending on the context and the graph it belongs to.
     *
     * In fact, this method adds two directed edges to the graph: a first one from this node to the other and a second one from the other to this node.
     *
     * @param other
     *            - the neighbouring node to be connected in both ways to this node
     */
    public void addBidirectedEdge(GraphNode other) {
        addEdge(other);
        other.addEdge(this);
    }

    /**
     * Returns the <b>immutable</b> adjacency list of this node, i.e. a list containing all nodes directly reachable from this node via exactly one directed edge.
     *
     * @return the immutable adjacency list of this node
     */
    public final List<GraphNode> getAdjacentNodes() {
        return Collections.unmodifiableList(adjacentNodes);
    }

    @Override
    public String toString() {
        return "(" + label + ")";
    }
}