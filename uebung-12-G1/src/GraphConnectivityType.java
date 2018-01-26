public enum GraphConnectivityType {
    /**
     * The graph has no nodes at all.
     */
    EMPTY,

    /**
     * The graph is disconnected, i.e. it consists of at least two unconnected subgraphs.
     */
    DISCONNECTED,

    /**
     * The graph is undirected (resp. totally bidirected) and all of its nodes are pairwise connected to each other (directly or indirectly).
     */
    CONNECTED,

    /**
     * The graph is directed and consists of at least two strongly connected components, but an undirected view of the graph is still connected.
     */
    WEAKLY_CONNECTED,

    /**
     * The graph is directed and consists of exactly one strongly connected component, i.e. all of its nodes are pairwise connected to each other (directly or indirectly) using the directed edges only.
     */
    STRONGLY_CONNECTED
}