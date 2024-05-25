package Kernel;

import Utils.Vector2;

/**
 * Object containing everything linked to the representation of the entity
 */
public class Sprite {
    private static int id_count = 0;
    public int id;

    public Sprite()
    {
        id =id_count;
        id_count++;
        offset = new Vector2();
        scale = new Vector2(1,1);
        horizontal_slice = new Vector2();
        visible = true;
    }

    public Sprite(String source, Vector2 offset, int depth, Vector2 scale, float rotationOffset, boolean visible) {
        id =id_count;
        id_count++;
        this.source = source;
        this.offset = offset;
        this.depth = depth;
        this.scale = scale;
        this.rotationOffset = rotationOffset;
        this.visible = visible;
    }

    public Sprite(String source, Vector2 offset, int depth, float animationSpeed, Vector2 scale, float rotationOffset, Vector2 horizontal_slice, boolean visible) {
        id =id_count;
        id_count++;
        this.source = source;
        this.offset = offset;
        this.depth = depth;
        this.animationSpeed = animationSpeed;
        this.scale = scale;
        this.rotationOffset = rotationOffset;
        this.horizontal_slice = horizontal_slice;
        this.visible = visible;
    }

    /**
     * path to the png of the sprite
     */
    public String source;

    /**
     * offside of the sprite compared to the entity transform
     */
    public Vector2 offset;
    /**
     * z index of the sprite, ie the depth define the order sprite are displayed on the sceen
     */
    public int depth;
    /**
     * speed of the animation
     */
    public float animationSpeed;
    /**
     * scale of the sprite, ie allow us to stretch it horizontally/vertically
     */
    public Vector2 scale;
    /**
     * rotation of the entity compared to the parent
     */
    public float rotationOffset;
    /**
     * how the png should be sliced for the animations, ie we can slice it in 16 different part of one animation
     */
    public Vector2 horizontal_slice;
    /**
     * whether the entity is currently visible
     */
    public boolean visible;

}
