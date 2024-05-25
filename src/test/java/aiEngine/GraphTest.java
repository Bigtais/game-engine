package aiEngine;

import AI.Graph;
import AI.Grid;
import AI.Node;
import Utils.Vector2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testAddNode() {
        Graph graph = new Graph();
        Node node = new Node("1", new Vector2());

        graph.addNode(node);
        assertEquals(1, graph.getNodes().size());

        graph.addNode(node);
        assertEquals(1, graph.getNodes().size());
        assertTrue(graph.getNodes().contains(node));
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        Node node1 = new Node("1", new Vector2());
        Node node2 = new Node("2", new Vector2());
        Node node3 = new Node("3", new Vector2());
        Node node4 = new Node("4", new Vector2());

        graph.addEdge(node1, node2, 5.0);
        graph.addEdge(node2, node1, 5.0);
        assertEquals(2, graph.getNodes().size());
        assertEquals(node1, node1.edges().get(0).source());
        assertEquals(node2, node1.edges().get(0).destination());
        assertEquals(node2, node2.edges().get(0).source());
        assertEquals(node1, node2.edges().get(0).destination());

        graph.addEdge(node1, node3, 5.0);
        assertEquals(3, graph.getNodes().size());
        assertEquals(node1, node1.edges().get(1).source());
        assertEquals(node3, node1.edges().get(1).destination());
        assertEquals(node3, node3.edges().get(0).source());
        assertEquals(node1, node3.edges().get(0).destination());
    }

    @Test
    public void testHasEdge() {
        Graph graph = new Graph();
        Node node1 = new Node("1", new Vector2());
        Node node2 = new Node("2", new Vector2());
        Node node3 = new Node("3", new Vector2());
        Node node4 = new Node("4", new Vector2());

        graph.addEdge(node1, node2, 5.0);
        assertTrue(graph.hasEdge(node1, node2));
        assertTrue(graph.hasEdge(node2, node1));
        assertFalse(graph.hasEdge(node1, node3));
    }

    @Test
    public void testBuildGraph0() throws FileNotFoundException {
        Grid grid = Grid.buildGrid("src/test/java/aiEngine/level0");
        Graph graph = Graph.buildGraph(grid);

        assertEquals(0, graph.getNodes().size());
    }

    @Test
    public void testBuildGraph1() throws FileNotFoundException {
        Grid grid = Grid.buildGrid("src/test/java/aiEngine/level1");
        Graph graph = Graph.buildGraph(grid);

        assertEquals(4, graph.getNodes().size());

        Node node1 = grid.getTile(0, 0);
        Node node2 = grid.getTile(0, 1);
        Node node3 = grid.getTile(1, 0);
        Node node4 = grid.getTile(1, 1);

        assertTrue(graph.hasEdge(node1, node2));
        assertTrue(graph.hasEdge(node1, node3));
        assertFalse(graph.hasEdge(node1, node4));

        assertTrue(graph.hasEdge(node4, node2));
        assertTrue(graph.hasEdge(node4, node3));
        assertFalse(graph.hasEdge(node4, node1));
    }

    @Test
    public void testBuildGraph2() throws FileNotFoundException {
        Grid grid = Grid.buildGrid("src/test/java/aiEngine/level2");
        Graph graph = Graph.buildGraph(grid);

        assertEquals(5, graph.getNodes().size());

        Node node1 = grid.getTile(0, 1);
        Node node2 = grid.getTile(1, 0);
        Node node3 = grid.getTile(1, 1);
        Node node4 = grid.getTile(1, 2);
        Node node5 = grid.getTile(2, 1);

        assertTrue(graph.hasEdge(node3, node1));
        assertTrue(graph.hasEdge(node3, node2));
        assertTrue(graph.hasEdge(node3, node4));
        assertTrue(graph.hasEdge(node3, node5));

        assertFalse(graph.hasEdge(node1, grid.getTile(0, 2)));
    }
}
