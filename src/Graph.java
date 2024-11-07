import java.util.*;

class Graph {
    private Map<Integer, String> nodes;
    private Map<Integer, List<Edge>> adjacents;

    public Graph() {
        nodes = new HashMap<>();
        adjacents = new HashMap<>();
    }

    public void addNode(int index, String name) {
        nodes.put(index, name);
        adjacents.put(index, new ArrayList<>());
    }

    public void addEdge(int from, int to, double distance, double accessibilityCoefficient) {
        double weight = distance * accessibilityCoefficient;
        adjacents.get(from).add(new Edge(to, weight));
        adjacents.get(to).add(new Edge(from, weight)); // Assuming undirected graph
    }

    public Map<Integer, String> getNodes() {
        return nodes;
    }

    public String getNodeName(int index) {
        return nodes.get(index);
    }

    public List<Edge> getAdjacents(int node) {
        return adjacents.get(node);
    }
}
