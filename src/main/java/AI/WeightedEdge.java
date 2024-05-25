package AI;

/**
 * The main WeightedEdge class
 */
public class WeightedEdge implements Comparable<WeightedEdge>{
    Node source;
    Node destination;
    double weight;

    WeightedEdge(Node s, Node d, double weight) {
        this.source = s;
        this.destination = d;
        this.weight = weight;
    }

    /**
     * Gets source node
     * @return
     */
    public Node source() {
        return source;
    }

    /**
     * Gets destination node
     * @return
     */
    public Node destination() {
        return destination;
    }

    /**
     * Gets weight of Edge
     * @return
     */
    public double weight() {
        return weight;
    }

    /**
     * Compares two edges by weight
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(WeightedEdge o) {
        if (this.weight == o.weight) return 0;
        if (this.weight > o.weight) return 1;
        return -1;
    }
}
