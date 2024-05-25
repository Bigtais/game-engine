package AI;

import Utils.Vector2;

import java.util.List;

/**
 * The main AI Engine class
 */
public class AIEngine {
    public Grid grid;
    public Graph graph;
    public IPathFinder pathFinder;

    public AIEngine(String levelFile) {
        try {
            this.grid = Grid.buildGrid(levelFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.graph = Graph.buildGraph(grid);
        this.pathFinder = new Dijkstra(graph);
    }

    /**
     * Computes the shortest path
     * @param source
     * @param destination
     */
    public void computePath(Vector2 source, Vector2 destination) {
        Node sourceNode = grid.VectorToNode(source);
        Node destNode = grid.VectorToNode(destination);
        pathFinder.compute(sourceNode, destNode);
    }

    /**
     * Tests whether a destination is reachable from source.
     * computePath should be called before calling this method.
     * @return
     */
    public boolean isReachable() {
        return pathFinder.isReachable();
    }

    /**
     * Returns the shortest path computed.
     * computePath should be called before calling this method.
     * @return
     */
    public List<Node> getPath() {
        return pathFinder.getPath();
    }
}
