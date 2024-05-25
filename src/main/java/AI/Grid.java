package AI;

import LevelEditor.LevelEditor;
import Utils.Vector2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main Grid class
 */
public class Grid {
    private List<List<Node>> tiles;
    private Integer colSize;

    public Grid() {
        tiles = new ArrayList<>();
        colSize = 0;
    }

    /**
     * Returns all the nodes in a row
     * @param row
     * @return
     */
    public List<Node> getRow(int row) {
        return tiles.get(row);
    }

    /**
     * Adds a row to the grid
     * @param nodes
     */
    public void addRow(List<Node> nodes) {
        if (nodes.size() > colSize) colSize = nodes.size();
        tiles.add(nodes);
    }

    /**
     * Returns grid's row size
     * @return
     */
    public int getRowSize() {
        return tiles.size();
    }

    /**
     * Returns grid's col size
     * @return
     */
    public int getColSize() {
        return colSize;
    }

    /**
     * Returns a node from tile.
     * @param row
     * @param col
     * @return
     */
    public Node getTile(int row, int col) {
        return tiles.get(row).get(Math.max(0, Math.min(18, col)));
    }

    /**
     * Builds a grid from a level file
     * @param levelFile
     * @return
     * @throws FileNotFoundException
     */
    public static Grid buildGrid(String levelFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(Grid.class.getResourceAsStream(levelFile));
        Grid grid = new Grid();

        int y = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Node> nodes = new ArrayList<>();
            for (int x = 0; x < line.length(); x++) {
                Node node = new Node(String.format("%s", line.charAt(x)), new Vector2(x * LevelEditor.levelScale.x, y * LevelEditor.levelScale.y));
                nodes.add(node);
            }
            grid.addRow(nodes);
            y++;
        }

        return grid;
    }

    /**
     * Converts a Vector2 coordinates to grid coordinates
     * @param vector
     * @return
     */
    public Node VectorToNode(Vector2 vector) {
        int row = Math.round(vector.y / LevelEditor.levelScale.y);
        int col = Math.round(vector.x / LevelEditor.levelScale.x);

        return getTile(row, col);
    }
}
