package Kernel;

import Utils.Vector2;

public class Transform {
    /**
     * position of the entity
     */
    public Vector2 position;

    /**
     * rotation of the entity
     */
    public float rotation;
    /**
     * scale of the entity
     */
    public Vector2 scale;

    public Transform()
    {
        position = new Vector2();
        scale = new Vector2(1,1);
    }

}
