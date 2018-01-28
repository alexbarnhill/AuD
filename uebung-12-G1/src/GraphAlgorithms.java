import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphAlgorithms implements IGraphAlgorithms{

    @Override
    public boolean isDirected(List<GraphNode> graph) {
        GraphIteratorFactory graphIteratorFactory = new GraphIteratorFactory();
        int nonBidirectional = 0;

        for(Iterator<GraphNode> graphNodeIterator = graphIteratorFactory.createGraphIteratorBFS(graph); graphNodeIterator.hasNext();) {
            GraphNode node = graphNodeIterator.next();
            if(node.getAdjacentNodes().size() > 0) {
                for(GraphNode neighbor : node.getAdjacentNodes()) {
                    if(!neighbor.getAdjacentNodes().contains(node)) {
                        nonBidirectional++;
                    }
                }

            }
        }

        return nonBidirectional != 0;
    }

    @Override
    public List<GraphNode> getRoots(List<GraphNode> graph) {
        GraphIteratorFactory graphIteratorFactory = new GraphIteratorFactory();
        List<GraphNode> graphNodes = new ArrayList<>();
        graphNodes.addAll(graph);

        if(isDirected(graph)) {
            for(Iterator<GraphNode> graphNodeIterator = graphIteratorFactory.createGraphIteratorBFS(graph); graphNodeIterator.hasNext();) {
                GraphNode node = graphNodeIterator.next();
                if(node.getAdjacentNodes().size() > 0) {
                    for(GraphNode neighbor : node.getAdjacentNodes()) {
                        if(neighbor.getAdjacentNodes().contains(node)) {
                            graphNodes.remove(node);
                        }
                        graphNodes.remove(neighbor);
                    }

                }
            }

        } else {
            if(graph.size() <= 1) {
                return graphNodes;
            } else {
                return new ArrayList<>();
            }
        }

        return graphNodes;

    }

    @Override
    public List<List<GraphNode>> getComponents(List<GraphNode> graph) {
        List<List<GraphNode>> lists = new ArrayList<>();

        List<GraphNode> visited = new ArrayList<>();
        int listCounter = 0;
        int graphSize = graph.size();

        List<GraphNode> graphCopy = new ArrayList<>();
        graphCopy.addAll(graph);

        while(graphCopy.size() > 0) {
            boolean changed = false;
            List<GraphNode> list = new ArrayList<>();
            list = (getComponent(graphCopy, listCounter));

            for(List<GraphNode> list1 : lists) {
                for(int i = 0; i < list1.size(); i++) {
                    GraphNode node = list1.get(i);
                    // if the list just created contains a list from another iteration
                    // add elements from the new list to the old one
                    if(list.contains(node)) {
                        changed = true;
                        for(int j = 0; j < list.size(); j++) {
                            GraphNode node1 = list.get(j);
                            if(!list1.contains(node1)) {
                                list1.add(node1);
                                list.remove(node1);
                            }
                        }
                    }
                }
            }


            if(!changed) {
                lists.add(list);
            }

            graphCopy.removeAll(list);

        }


        return lists;


    }

    @Override
    public List<GraphNode> getACycle(List<GraphNode> graph) {

        List<GraphNode> cycle = new ArrayList<>();
        for(GraphNode node : graph) {
            List<GraphNode> cycleAttempt = cycleAttempt(node, node, cycle, graph);
            cycle = cycleAttempt == null ? cycle : cycleAttempt;
            if(cycle != null && cycle.size() >= 1&& cycle.get(0) == cycle.get(cycle.size() - 1)) {
                return cycle;
            }

        }

        return null;

    }

    private List<GraphNode> cycleAttempt(GraphNode searched, GraphNode current, List<GraphNode> cycle, List<GraphNode> graph) {
        if((searched == current && cycle.size() > 1) || cycle.size() > graph.size()) {
            cycle.add(current);
            return cycle;
        } else if (searched.getAdjacentNodes().size() == 0) {
            return null;
        }
        if(!cycle.contains(current)) {
            cycle.add(current);
        }


        if(current.getAdjacentNodes().size() > 0) {
            for(GraphNode element : current.getAdjacentNodes()) {
                if(!cycle.contains(element) || element.equals(searched)) {
                    cycleAttempt(searched, element, cycle, graph);
                }

            }
        }

        return cycle;
    }

    @Override
    public GraphConnectivityType classifyConnectivity(List<GraphNode> graph) {
        if(graph.size() == 0) {
            return GraphConnectivityType.EMPTY;
        } else if(getComponents(graph).size() > 1) {
            return GraphConnectivityType.DISCONNECTED;
        } else if(!isDirected(graph)) {
            return GraphConnectivityType.CONNECTED;
        } else if(stronglyConnected(graph)) {
            return GraphConnectivityType.STRONGLY_CONNECTED;
        } else {
            return GraphConnectivityType.WEAKLY_CONNECTED;
        }


    }

    private List<GraphNode> getComponent(List<GraphNode> graph, int start) {

        List<GraphNode> list = new ArrayList<>();
        GraphNode el = graph.get(start);
        if(!list.contains(el)) {
            list.add(el);
        }


        for(int i = 0; i < list.size(); i++) {
            el = list.get(i);
            for(GraphNode node : el.getAdjacentNodes()) {
                if(!list.contains(node)) {
                    list.add(node);
                }
            }


        }
        return list;
    }

    private boolean stronglyConnected(List<GraphNode> graph) {

        for(GraphNode node : graph) {
            List<GraphNode> visited = new ArrayList<>();

            if(!stronglyConnectedHelper(graph, visited, graph.indexOf(node))) {
                return false;
            }
        }
        return true;
    }

    private boolean stronglyConnectedHelper(List<GraphNode> graph, List<GraphNode> visited, int start) {


        GraphNode node = graph.get(start);
        if(!visited.contains(node)) {
            visited.add(node);
        }

        if(node.getAdjacentNodes().size() > 0) {
            for(GraphNode graphNode : node.getAdjacentNodes()) {
                if(!visited.contains(graphNode)) {
                    stronglyConnectedHelper(graph, visited, graph.indexOf(graphNode));
                }
            }
        }

        if(visited.containsAll(graph)) {
            return true;
        } else {
            return false;
        }



    }


}
