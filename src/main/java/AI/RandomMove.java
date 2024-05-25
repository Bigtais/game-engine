package AI;

import Gameplay.GameplayMain;
import Gameplay.GhostObject;
import Utils.Vector2;
import Kernel.Kernel;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * The main RandomMove class
 */
public class RandomMove implements MovingStrategy{
    Kernel kernel = GameplayMain.kernel;
    AIEngine aiEngine = kernel.getAiEngine();
    Vector2 nextDestination;
    Vector2 previousCord;
    Random random;
    Queue<Node> path;
    GhostObject ghost;

    public RandomMove() {
        random = new Random();
        nextDestination = new Vector2();
        path = new LinkedList<>();
    }

    private Vector2 pickNextTarget() {
        Node randomNode = (Node) aiEngine.graph.getNodes().toArray()[random.nextInt(aiEngine.graph.getNodes().size())];
        return randomNode.coordinates();
    }

    /**
     * Moves the Ghost one step towards the random target
     */
    @Override
    public void move() {
        previousCord = ghost.transform.position;
        if (path.isEmpty()) {
            Vector2 randomDest = pickNextTarget();
            aiEngine.computePath(ghost.transform.position, randomDest);
            if (!aiEngine.isReachable()) return;
            buildPath(aiEngine.getPath());
            nextDestination = path.poll().coordinates();
        }

        if (reachedDest()) {
            Node target = path.poll();
            nextDestination = target.coordinates();
        }

        ghost.moveTo(nextDestination);
    }

    /**
     * Attaches the Ghost object.
     * Should be called by Ghost
     * @param ghost
     */
    @Override
    public void attach(GhostObject ghost) {
        this.ghost = ghost;
        previousCord = ghost.transform.position;
    }

    /**
     * Resets the local variables.
     */
    @Override
    public void reset() {
        nextDestination = null;
        previousCord = null;
        path.clear();
    }


    private void buildPath(List<Node> path) {
        for (int i = 1; i < path.size(); i++) {
            this.path.offer(path.get(i));
        }
    }

    private boolean reachedDest() {
        float dist1 = ghost.transform.position.distance(previousCord);
        float dist2 = nextDestination.distance(previousCord);
        float dist3 = nextDestination.distance(ghost.transform.position);
        return (dist2 + dist3) == dist1;
    }
}
