import java.util.*;

public class GraphIteratorFactory implements IGraphIteratorFactory {
    @Override
    public Iterator<GraphNode> createGraphIteratorBFS(List<GraphNode> graph) {
        List<GraphNode> visited = new ArrayList<>();
        List<GraphNode> g = graph;
        Queue<GraphNode> queue = new LinkedList<>();

        Iterator<GraphNode> iterator = new Iterator<GraphNode>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public GraphNode next() {
                GraphNode next= null;
                if(hasNext()) {
                    next = queue.remove();
                }

                return next;

            }
        };

        queue = bfsQueueBuild(g, visited, queue);
        return iterator;
    }

    private Queue<GraphNode> bfsQueueBuild(List<GraphNode> g, List<GraphNode> visited, Queue<GraphNode> queue) {
        List<GraphNode> tempVisited = new ArrayList<>();
        if(queue.containsAll(g)) {
            return queue;
        }

        GraphNode first = g.remove(0);

        visited.add(first);
        tempVisited.add(first);
        queue.add(first);

        for(Iterator<GraphNode> i = tempVisited.iterator(); i.hasNext();) {
            GraphNode node = i.next();
            
        }
    }

    private Queue<GraphNode> dfsQueueBuild(List<GraphNode> g, List<GraphNode> visited, Queue<GraphNode> queue) {
    }

    @Override
    public Iterator<GraphNode> createGraphIteratorDFS(List<GraphNode> graph) {
        Iterator<GraphNode> iterator = new Iterator<GraphNode>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public GraphNode next() {
                return null;
            }
        };
        return iterator;
    }
}
