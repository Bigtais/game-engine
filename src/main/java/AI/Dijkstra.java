package AI;

import java.util.*;

/**
 * The main Dijkstra algorithm.
 */
public class Dijkstra implements IPathFinder {
    Graph graph;
    HashMap<Node, Double> dist;
    HashMap<Node, Node> prev;
    Node source;
    Node destination;
    List<Node> path;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    /**
     * Computes the shortest path.
     * @param source
     * @param destination
     */
    @Override
    public void compute(Node source, Node destination) {
        this.source = source;
        this.destination = destination;

        dist = new HashMap<>();
        prev = new HashMap<>();
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();

        for (Node node : graph.nodes) {
            if (node != source) {
                dist.put(node, Double.MAX_VALUE);
                prev.put(node, null);
            }
        }
        dist.put(source, 0.0);
        pq.add(new WeightedEdge(null, source, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll().destination;
            for (WeightedEdge edge : node.edges) {
                Node target = edge.destination;
                Double weight = edge.weight;
                Double newDist = weight + dist.get(node);

                if (newDist < dist.get(target)) {
                    pq.remove(node);
                    prev.put(target, node);
                    dist.put(target, newDist);
                    pq.add(new WeightedEdge(node, target, newDist));
                }
            }
        }

        path = computePath();
    }

    /**
     * Tests whether a destination Node is reachable from source Node.
     * @return
     */
    public boolean isReachable() {
        return path.size() > 1
                && path.get(0) == source
                && path.get(path.size() - 1) == destination;
    }

    /**
     * Returns the shortest path.
     * @return
     */
    @Override
    public List<Node> getPath() {
        return path;
    }

    private List<Node> computePath() {
        List<Node> path = new ArrayList<>();

        Node actual = destination;
        while (actual != null) {
            path.add(actual);
            actual = prev.get(actual);
        }
        Collections.reverse(path);

        return path;
    }
}
