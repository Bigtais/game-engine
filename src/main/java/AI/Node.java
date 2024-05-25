package AI;

import Utils.Vector2;

import java.util.LinkedList;
import java.util.List;

/**
 * The main Node class
 */
public class Node{
    String type;;
    final Vector2 coordinates;
    LinkedList<WeightedEdge> edges;

    public Node(String type, Vector2 coordinates) {
        this.type = type;
        this.coordinates = coordinates;
        edges = new LinkedList<>();
    }

    /**
     * Returns the node's name
     * @return
     */
    public String getName() {
        return type;
    }

    /**
     * Returns node's coordinates
     * @return
     */
    public Vector2 coordinates() {
        return coordinates;
    }

    /**
     * Returns list of edges in which the node is contained
     * @return
     */
    public List<WeightedEdge> edges() {
        return edges;
    }

    /**
     * Returns the type of the node
     * @return
     */
    public String type() {
        return type;
    }

    /**
     * Gets String representation of Node
     * @return
     */
    public String toString() {
        return getName();
    }
}
