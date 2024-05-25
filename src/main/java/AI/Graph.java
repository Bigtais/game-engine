package AI;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * The main Graph class.
 */
public class Graph {
    Set<Node> nodes;

    public Graph() {
        nodes = new HashSet<>();
    }

    /**
     * Adds a node to the graph.
     * @param node
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Adds an edge to the graph.
     * @param source
     * @param destination
     * @param weight
     */
    public void addEdge(Node source, Node destination, double weight) {
        nodes.add(source);
        nodes.add(destination);

        if (source == destination) return;
        addEdgeHelper(source, destination, weight);
        addEdgeHelper(destination, source, weight);
    }

    private void addEdgeHelper(Node a, Node b, double weight) {
        for (WeightedEdge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                edge.weight = weight;
                return;
            }
        }

        a.edges.add(new WeightedEdge(a, b, weight));
    }

    /**
     * Tests whether there is an edge between two nodes
     * @param source
     * @param destination
     * @return
     */
    public boolean hasEdge(Node source, Node destination) {
        LinkedList<WeightedEdge> edges = source.edges;
        for (WeightedEdge edge : edges) {
            if (edge.destination == destination) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all the nodes.
     * @return
     */
    public Set<Node> getNodes() {
        return nodes;
    }

    /**
     * Builds a graph from a grid
     * @param grid
     * @return
     */
    public static Graph buildGraph(Grid grid) {
        Graph graph = new Graph();
        Node source, up, left;

        for (int row = 0; row < grid.getRowSize(); row++) {
            for (int col = 0; col < grid.getColSize(); col++) {
                source = grid.getTile(row, col);
                if (source.type.equals("M")) continue;
                if (row > 0) {
                    up = grid.getTile(row - 1, col);
                    if (!up.type.equals("M")) graph.addEdge(source, up, 1);
                }

                if (col > 0) {
                    left = grid.getTile(row, col - 1);
                    if (!left.type.equals("M")) graph.addEdge(source, left, 1);
                }
            }
        }

        return graph;
    }
}
