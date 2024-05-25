package aiEngine;

import AI.Grid;

import AI.Node;
import LevelEditor.LevelEditor;
import Utils.Vector2;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GridTest {
    @Test
    public void testAddRow() {
        Grid grid = new Grid();

        Node node1 = new Node("1", new Vector2());
        Node node2 = new Node("2", new Vector2());
        Node node3 = new Node("3", new Vector2());
        Node node4 = new Node("4", new Vector2());
        List<Node> row = new ArrayList<>();
        row.add(node1);
        row.add(node2);
        grid.addRow(row);

        row = new ArrayList<>();
        row.add(node3);
        row.add(node4);
        grid.addRow(row);

        assertEquals(grid.getRow(0).get(0), node1);
        assertEquals(grid.getRow(0).get(1), node2);
        assertEquals(grid.getRow(1).get(0), node3);
        assertEquals(grid.getRow(1).get(1), node4);
        assertNotEquals(node1, grid.getRow(1).get(1));
    }

    @Test
    public void testGetRowSize() {
        Grid grid = new Grid();

        Node node1 = new Node("1", new Vector2());
        Node node2 = new Node("2", new Vector2());
        Node node3 = new Node("3", new Vector2());
        Node node4 = new Node("4", new Vector2());
        List<Node> row = new ArrayList<>();
        row.add(node1);
        row.add(node2);
        grid.addRow(row);

        row = new ArrayList<>();
        row.add(node3);
        row.add(node4);
        grid.addRow(row);

        assertEquals(2, grid.getRowSize());
    }

    @Test
    public void testGetColSize() {
        Grid grid = new Grid();

        Node node1 = new Node("1", new Vector2());
        Node node2 = new Node("2", new Vector2());
        Node node3 = new Node("3", new Vector2());
        Node node4 = new Node("4", new Vector2());
        List<Node> row = new ArrayList<>();
        row.add(node2);
        grid.addRow(row);

        row = new ArrayList<>();
        row.add(node3);
        row.add(node4);
        grid.addRow(row);

        assertEquals(2, grid.getColSize());
    }

    @Test
    public void testGetTile() {
        Grid grid = new Grid();

        Node node1 = new Node("1", new Vector2());
        Node node2 = new Node("2", new Vector2());
        Node node3 = new Node("3", new Vector2());
        Node node4 = new Node("4", new Vector2());
        List<Node> row = new ArrayList<>();
        row.add(node1);
        row.add(node2);
        grid.addRow(row);

        row = new ArrayList<>();
        row.add(node3);
        row.add(node4);
        grid.addRow(row);

        assertEquals(node1, grid.getTile(0, 0));
        assertEquals(node2, grid.getTile(0, 1));
        assertEquals(node3, grid.getTile(1, 0));
        assertEquals(node4, grid.getTile(1, 1));
    }

    @Test
    public void testBuildGrid0() throws FileNotFoundException {
        Grid grid = Grid.buildGrid("src/test/java/aiEngine/level0");

        assertEquals(2, grid.getRowSize());
        assertEquals(4, grid.getColSize());

        for (int row = 0; row < grid.getRowSize(); row++) {
            for (int col = 0; col < grid.getColSize(); col++) {
                assertEquals("M", grid.getTile(row, col).type());
            }
        }
    }

    @Test
    public void testBuildGrid1() throws FileNotFoundException {
        Grid grid = Grid.buildGrid("src/test/java/aiEngine/level1");

        assertEquals(2, grid.getRowSize());
        assertEquals(2, grid.getColSize());

        for (int row = 0; row < grid.getRowSize(); row++) {
            for (int col = 0; col < grid.getColSize(); col++) {
                Vector2 actual = grid.getTile(row, col).coordinates();
                assertEquals(col * LevelEditor.levelScale.x, actual.x);
                assertEquals(row * LevelEditor.levelScale.y, actual.y);
            }
        }
    }
}
