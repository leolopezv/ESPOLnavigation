package graphStructure;

public class Edge {
    private int to;
    private double weight;

    public Edge(int to, double weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }
}
