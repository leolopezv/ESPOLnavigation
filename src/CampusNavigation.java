import java.io.File;
import java.util.*;
import graphStructure.Graph;

public class CampusNavigation {

    public static void main(String[] args) {
        Graph graph = getGraph();

        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Por favor, ingresa tu punto de partida: ");
        
        int partida = scanner.nextInt();
        System.out.println("Por favor, ingresa tu destino: ");
        int destino = scanner.nextInt();

        if (!graph.getNodes().containsKey(partida) || !graph.getNodes().containsKey(destino) ) {
            System.out.println("Error: El nodo ingresado no existe en el grafo.");
            return;}
        
        
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.computeShortestPaths(partida);


        System.out.println("Ruta más convenientes desde " + graph.getNodeName(partida) + ":");
        
            System.out.print("Camino a " + graph.getNodeName(destino) + ": ");
                List<Integer> path = dijkstra.getPath(destino);
                if (path == null) {
                    System.out.println("No hay camino disponible.");
                } else {
                    for (int i = 0; i < path.size(); i++) {
                        System.out.print(graph.getNodeName(path.get(i)));
                        if (i < path.size() - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    
                    System.out.printf("\nDistancia total ajustada: %.2f metros\n", dijkstra.getDistance(destino));
                }
                System.out.println();
       
    }

    private static Graph getGraph() {
        Graph graph = new Graph();
        try {
            // Leer nodos
            Scanner nodeScanner = new Scanner(new File("src/nodes.txt"));
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
            Scanner edgeScanner = new Scanner(new File("src/edges.txt"));
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
    

