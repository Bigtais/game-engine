package AI;

import java.util.List;

/**
 * PathFinder Interface
 */
public interface IPathFinder {
    /**
     * Computes the shortest path.
     * @param source
     * @param destination
     */
    void compute(Node source, Node destination);

    /**
     * Returns the shortest path.
     * @return
     */
    List<Node> getPath();

    /**
     * Tests whether a destination Node is reachable from source Node.
     * @return
     */
    boolean isReachable();
}
