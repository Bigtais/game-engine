package AI;

import Gameplay.Gameplay;
import Gameplay.GameplayMain;
import Gameplay.GhostObject;
import Kernel.Kernel;
import Utils.Vector2;

/**
 * The main Follower Ghost class.
 */
public class FollowerMove implements MovingStrategy{
    Kernel kernel = GameplayMain.kernel;
    AIEngine aiEngine = kernel.getAiEngine();
    Vector2 nextDestination;
    Vector2 previousCord;;
    GhostObject ghost;

    public FollowerMove() {
    }

    /**
     * Moves the Ghost one step towards the target.
     */
    @Override
    public void move() {
        if (nextDestination == null || reachedDest()) {
            aiEngine.computePath(ghost.transform.position, GameplayMain.player.transform.position);
            if (!aiEngine.isReachable()) return;
            nextDestination = aiEngine.getPath().get(1).coordinates();
        }


        previousCord = ghost.transform.position;
        ghost.moveTo(nextDestination);
    }

    /**
     * Attaches a Ghost object.
     * Should be called by Ghost Object during initialization.
     * @param ghost
     */
    @Override
    public void attach(GhostObject ghost) {
        this.ghost = ghost;
        previousCord = ghost.transform.position;
    }

    /**
     * Resets the state of variables.
     */
    @Override
    public void reset() {
        nextDestination = null;
        previousCord = null;
    }

    private boolean reachedDest() {
        float dist1 = ghost.transform.position.distance(previousCord);
        float dist2 = nextDestination.distance(previousCord);
        float dist3 = nextDestination.distance(ghost.transform.position);
        return (dist2 + dist3) == dist1;
    }
}
