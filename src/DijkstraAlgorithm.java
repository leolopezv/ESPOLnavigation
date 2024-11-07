import java.util.*;

class DijkstraAlgorithm {
    private Graph graph;
    private Map<Integer, Double> distances;
    private Map<Integer, Integer> predecessors;
    private Set<Integer> settledNodes;
    private PriorityQueue<Node> priorityQueue;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public void computeShortestPaths(int source) {
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        settledNodes = new HashSet<>();
        priorityQueue = new PriorityQueue<>();

        distances.put(source, 0.0);
        priorityQueue.add(new Node(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int u = currentNode.getId();

            if (!settledNodes.contains(u)) {
                settledNodes.add(u);
                evaluateNeighbours(u);
            }
        }
    }

    private void evaluateNeighbours(int u) {
        List<Edge> adjacentNodes = graph.getAdjacents(u);
        for (Edge edge : adjacentNodes) {
            int v = edge.getTo();
            if (!settledNodes.contains(v)) {
                double newDist = distances.get(u) + edge.getWeight();
                if (distances.getOrDefault(v, Double.POSITIVE_INFINITY) > newDist) {
                    distances.put(v, newDist);
                    predecessors.put(v, u);
                    priorityQueue.add(new Node(v, newDist));
                }
            }
        }
    }

    public List<Integer> getPath(int destination) {
        LinkedList<Integer> path = new LinkedList<>();
        Integer step = destination;

        if (!distances.containsKey(step)) {
            return null;
        }

        while (step != null) {
            path.addFirst(step);
            step = predecessors.get(step);
        }

        return path;
    }

    public double getDistance(int destination) {
        return distances.getOrDefault(destination, Double.POSITIVE_INFINITY);
    }
}
