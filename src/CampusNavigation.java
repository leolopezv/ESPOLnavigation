import data.DataLoader;
import graphStructure.Graph;

import java.util.*;

public class CampusNavigation {

    public static void main(String[] args) {
        Graph graph = DataLoader.loadGraph();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al maps de la ESPOL");
        System.out.println("Por favor, ingresa tu punto de partida: ");

        int source = scanner.nextInt();
        System.out.println("Por favor, ingresa tu destino: ");
        int destination = scanner.nextInt();

        if (!graph.getNodes().containsKey(source) || !graph.getNodes().containsKey(destination)) {
            System.out.println("Error: El nodo ingresado no existe en el grafo.");
            return;
        }

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.computeShortestPaths(source);

        System.out.println("Ruta más convenientes desde " + graph.getNodeName(source) + ":");

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