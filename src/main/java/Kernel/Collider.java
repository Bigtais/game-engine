package Kernel;

import Utils.Vector2;

/**
 * the collider represent the size and position of the collision box of the entity
 */
public class Collider {
    /**
     * offset compared to the parent object, top left position
     */
    public Vector2 offset;
    /**
     * size of the diagonal, ie we can have the four points of the rectangle from it
     */
    public Vector2 size;

    public Collider()
    {
        offset = new Vector2();
        size = new Vector2();
    }
}
