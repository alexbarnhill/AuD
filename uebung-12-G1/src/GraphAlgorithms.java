import java.util.Collection;
import java.util.List;

public class GraphAlgorithms implements IGraphAlgorithms{
    @Override
    public boolean isDirected(List<GraphNode> graph) {
        return false;
    }

    @Override
    public Collection<GraphNode> getRoots(List<GraphNode> graph) {
        return null;
    }

    @Override
    public List<List<GraphNode>> getComponents(List<GraphNode> graph) {
        return null;
    }

    @Override
    public List<GraphNode> getACycle(List<GraphNode> graph) {
        return null;
    }

    @Override
    public GraphConnectivityType classifyConnectivity(List<GraphNode> graph) {
        return null;
    }
}
