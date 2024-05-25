package AI;

import Gameplay.GhostObject;
import Utils.Vector2;

public interface MovingStrategy {
    /**
     * Moves the Ghost one step towards the target.
     */
    void move();

    /**
     * Attaches a Ghost object.
     * Should be called by Ghost Object during initialization.
     * @param ghost
     */
    void attach(GhostObject ghost);

    /**
     * Resets the state of variables.
     */
    void reset();
}
