package data;

import graphStructure.Graph;

import java.io.File;
import java.util.Scanner;

public class DataLoader {

    public static Graph loadGraph() {
        return loadGraph("src/data/nodes.txt", "src/data/edges.txt");
    }

    // Existing loadGraph method
    public static Graph loadGraph(String nodesFilePath, String edgesFilePath) {
        Graph graph = new Graph();
        try {
            // Leer nodos
            Scanner nodeScanner = new Scanner(new File(nodesFilePath));
            while (nodeScanner.hasNextLine()) {
                String line = nodeScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    graph.addNode(id, name);
                }
            }
            nodeScanner.close();

            // Leer aristas
            Scanner edgeScanner = new Scanner(new File(edgesFilePath));
            while (edgeScanner.hasNextLine()) {
                String line = edgeScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    int source = Integer.parseInt(parts[0]);
                    int destination = Integer.parseInt(parts[1]);
                    double distance = Double.parseDouble(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    graph.addEdge(source, destination, distance, weight);
                }
            }
            edgeScanner.close();

        } catch (Exception e) {
            System.err.println("Error al leer los archivos: " + e.getMessage());
            e.printStackTrace();
        }
        return graph;
    }
}