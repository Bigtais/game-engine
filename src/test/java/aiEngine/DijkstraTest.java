package aiEngine;

import AI.Dijkstra;
import AI.Graph;
import AI.Grid;
import AI.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

public class DijkstraTest {
    Dijkstra dijkstra;
    static Graph graph;
    static Grid grid;

    @BeforeAll
    static void init() throws FileNotFoundException {
        grid = Grid.buildGrid("src/test/java/aiEngine/Level");
        graph = Graph.buildGraph(grid);
    }

    @BeforeEach
    void initAlgo() {
        this.dijkstra = new Dijkstra(graph);
    }

    @Test
    void testCompute() {
        Node source = grid.getTile(10,10);
        Node destination = grid.getTile(18, 15);

        dijkstra.compute(source, destination);

        Node pathSource = dijkstra.getPath().get(0);
        Node pathDest = dijkstra.getPath().get(dijkstra.getPath().size() - 1);

        assertSame(source, pathSource);
        assertSame(destination, pathDest);
    }

    @Test
    void testIsReachable() {
        Node source = grid.getTile(10,10);
        Node destination = grid.getTile(18, 15);

        dijkstra.compute(source, destination);

        assertTrue(dijkstra.isReachable());
    }

    @Test
    void testNotReachable() {
        Node source = grid.getTile(10,10);
        Node destination = grid.getTile(19, 10);

        dijkstra.compute(source, destination);

        assertFalse(dijkstra.isReachable());
    }
}
