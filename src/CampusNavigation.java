import java.util.*;

public class CampusNavigation {

    public static void main(String[] args) {
        Graph graph = getGraph();

        int source = 0;
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.computeShortestPaths(source);

        System.out.println("Rutas más convenientes desde " + graph.getNodeName(source) + ":");
        for (int destination : graph.getNodes().keySet()) {
            if (destination != source) {
                System.out.print("Camino a " + graph.getNodeName(destination) + ": ");
                List<Integer> path = dijkstra.getPath(destination);
                if (path == null) {
                    System.out.println("No hay camino disponible.");
                } else {
                    for (int i = 0; i < path.size(); i++) {
                        System.out.print(graph.getNodeName(path.get(i)));
                        if (i < path.size() - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    System.out.printf("\nDistancia total ajustada: %.2f metros\n", dijkstra.getDistance(destination));
                }
                System.out.println();
            }
        }
    }

    private static Graph getGraph() {
        Graph graph = new Graph();

        graph.addNode(0, "Facultad de Ingeniería en Electricidad y Computación");
        graph.addNode(1, "Biblioteca Central");
        graph.addNode(2, "Cafetería");
        graph.addNode(3, "Auditorio Principal");
        graph.addNode(4, "Laboratorios de Química");

        graph.addEdge(0, 1, 500, 1.5);
        graph.addEdge(0, 2, 300, 1.0);
        graph.addEdge(2, 1, 200, 0.9);
        graph.addEdge(1, 3, 400, 1.1);
        graph.addEdge(2, 3, 600, 1.2);
        graph.addEdge(3, 4, 350, 1.0);
        graph.addEdge(1, 4, 800, 0.8);
        return graph;
    }
}
