import java.util.*;

public class GraphIteratorFactory implements IGraphIteratorFactory {
    @Override
    public Iterator<GraphNode> createGraphIteratorBFS(List<GraphNode> graph) {
        List<GraphNode> visited = new ArrayList<>();
        List<GraphNode> g = graph;
        Queue<GraphNode> queue = new LinkedList<>();

        final Queue<GraphNode> finalQueue = bfsQueueBuild(g, visited, queue, 0);

        Iterator<GraphNode> iterator = new Iterator<GraphNode>() {
            @Override
            public boolean hasNext() {
                return !finalQueue.isEmpty();
            }

            @Override
            public GraphNode next() {
                GraphNode next= null;
                if(hasNext()) {
                    next = finalQueue.remove();
                }

                return next;

            }

            @Override
            public void remove() {
                if(hasNext()) {
                    finalQueue.remove();
                }
            }
        };

        return iterator;
    }

    private Queue<GraphNode> bfsQueueBuild(List<GraphNode> g, List<GraphNode> visited, Queue<GraphNode> queue, int start) {
        if(queue.containsAll(g)) {
            return queue;
        }

        GraphNode first = g.get(start);
        if(!visited.contains(first)) {
            visited.add(first);
            queue.add(first);
        }


        for(int i = 0; i < visited.size(); i++) {
            GraphNode node = visited.get(i);
            if(node.getAdjacentNodes().size() > 0) {
                for(GraphNode element : node.getAdjacentNodes()) {
                    if(!visited.contains(element)) {
                        queue.add(element);
                        visited.add(element);
                    }
                }
            }
            
        }

        if(queue.size() < g.size()) {
            return bfsQueueBuild(g, visited, queue, start + 1);
        }

        return queue;
    }



    @Override
    public Iterator<GraphNode> createGraphIteratorDFS(List<GraphNode> graph) {
        List<GraphNode> visited = new ArrayList<>();
        List stack = new ArrayList<>();
        List<GraphNode> g = graph;
        List finalStack = new ArrayList();
        int startCounter = 0;
        while (finalStack.size() < g.size()) {
            finalStack = dfsStackBuild(g, visited, stack, startCounter);
            startCounter++;
        }

        final List finalStack1 = finalStack;
        Iterator<GraphNode> iterator = new Iterator<GraphNode>() {
            @Override
            public boolean hasNext() {
                return !finalStack1.isEmpty();
            }

            @Override
            public GraphNode next() {
                GraphNode next = null;
                if(hasNext()) {
                    next = (GraphNode) finalStack1.remove(0);
                }

                return next;
            }

            @Override
            public void remove() {
                if(hasNext()) {
                    finalStack1.remove(0);
                }
            }
        };
        return iterator;
    }

    private List dfsStackBuild(List<GraphNode> g, List<GraphNode> visited, List stack, int start) {
        if(stack.containsAll(g)) {
            return stack;
        }

        GraphNode first = g.get(start);
        if(!visited.contains(first)) {
            visited.add(first);
            stack.add(first);
        }

        if(first.getAdjacentNodes().size() > 0) {
            for(GraphNode element : first.getAdjacentNodes()) {
                if(!visited.contains(element)) {
                    dfsStackBuild(g, visited, stack, g.indexOf(element));
                }
            }
        }


        return stack;

    }


}
