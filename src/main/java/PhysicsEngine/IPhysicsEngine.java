package PhysicsEngine;

import Kernel.Collider;
import Kernel.Entity;
import Utils.Vector2;

/**
 * General interface of a physics engine functionnalities
 * simulating the movements and collisions of an entity.
 */
public interface IPhysicsEngine {

    /**
     * Moves the entity and its associated collider to the asked position.
     * @param e The entity to move.
     * @param v The vector corresponding to the new position of the entity.
     */
    static void move(Entity e, Vector2 v){};

    /**
     * Set the position of the entity and its associated collider to the provided position.
     * @param e The entity to change position.
     * @param v The vector corresponding to the new position of the entity.
     */
    void setPosition(Entity e, Vector2 v);

    /**
     * Verifies if the point provided is inside the collider associated to the entity
     * @param e The entity to which we verify the collision.
     * @param location The vector corresponding to the absolute position of the point.
     * @return true if the point is inside the entity's collider, false otherwise.
     */
    Boolean isPointMeetingEntity(Entity e, Vector2 location);

    /**
     * Verifies if the collider associated the first entity
     * overlaps the collider associated to the second entity.
     * It essentially verifies if the colliders associated to the entities provided overlap each other.
     * @param a The first entity.
     * @param b The second entity.
     * @return true if the colliders overlap each other, false otherwise.
     */
    Boolean isMeetingEntity(Entity a, Entity b);

    /**
     * Sets the size of the provided collider to the size asked.
     * @param c The collider to modify.
     * @param newSize The new size of the collider.
     */
    void setColliderSize(Collider c, Vector2 newSize);

    /**
     * Sets the position of the provided collider relative to the parent entity's position
     * @param c The collider to modify.
     * @param newOffset The new offset of the collider relative to the parent entity.
     */
    void setColliderOffset(Collider c, Vector2 newOffset);
}
