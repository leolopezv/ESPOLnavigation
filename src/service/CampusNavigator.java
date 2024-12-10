package service;

import data.DataLoader;
import graphStructure.Graph;

import java.util.*;

public class CampusNavigator {

    public void run() {
        displayMenu();
        Graph graph = DataLoader.loadGraph();
        Scanner scanner = new Scanner(System.in);

        int source = getInput(scanner, "Por favor, ingresa tu punto de partida: ");
        int destination = getInput(scanner, "Por favor, ingresa tu destino: ");

        if (!isValidNode(graph, source) || !isValidNode(graph, destination)) {
            System.out.println("Error: El nodo ingresado no existe en el grafo.");
            return;
        }

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        long startTime = System.nanoTime();
        dijkstra.computeShortestPaths(source);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("-------------------------------------------------------");
        System.out.printf("Tiempo de ejecución del algoritmo: %.2f ms\n", duration / 1_000_000.0);
        System.out.println("-------------------------------------------------------");

        printPath(graph, dijkstra, source, destination);
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("       Bienvenido al maps de la ESPOL    ");
        System.out.println("------------------------------------------");
        System.out.println("            Menú de destinos:             ");
        System.out.println("---------------------------------------------");
        System.out.println("""
        0.FCSH    1.FCNM         2.FIMCP
        3.SWEET   4.DOBE         5.PARKFIEC
        6.FIEC    7.FADCOM       8.FEPOL
        9.UBEP    10.COLISEON    11.COLISEOV
        12.FICT   13,FIMCM
        """);
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    private int getInput(Scanner scanner, String prompt) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Error: Por favor, ingresa un número entero válido.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    private boolean isValidNode(Graph graph, int node) {
        return graph.getNodes().containsKey(node);
    }

    private void printPath(Graph graph, DijkstraAlgorithm dijkstra, int source, int destination) {
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