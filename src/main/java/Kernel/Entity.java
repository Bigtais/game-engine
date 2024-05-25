package Kernel;

import Utils.Vector2;

/**
 * Entity represent every kind of object that could be used in the game, they have different attribute
 * to represent their possible values
 */
public class Entity {

    /**
     * transform of the entity
     */
    public Transform transform;
    /**
     * Collider of the entity
     */
    public Collider collider;
    /**
     * sprite used by the entity
     */
    public Sprite sprite;
    /**
     * Spawn location
     */
    public Vector2 spawnLocation;

    /**
     * Constructs an entity with the assigned parameters.
     * @param transform The transform of the entity.
     * @param collider The collidier of the entity.
     * @param sprite The sprite of the entity.
     */
    public Entity(Transform transform, Collider collider, Sprite sprite)
    {
        this.transform = transform;
        this.collider = collider;
        this.sprite = sprite;
    }

    /**
     * Creates an entity from an existing entity.
     * @param entity The entity to clone.
     */
    public Entity(Entity entity)
    {
        this.transform = entity.transform;
        this.collider = entity.collider;
        this.sprite = entity.sprite;
    }

    public Entity()
    {
        this.transform = new Transform();
        this.collider = new Collider();
        this.sprite = new Sprite();
    }

    /**
     * Action performed on each frame.
     * This method is called approximately 60 times per second,
     * or once every 1/60 seconds.
     * By default, the method does nothing. It must be overridden everytime an entity subclass is created.
     */
    public void update(){
        // Do nothing by default
    }

    /**
     * Destroys this instance of this entity.
     */
    public void destroy(){
        Kernel.getKernel().destroyInstance(this);
    }

    public void setSpawnLocation(Vector2 spawnLocation) {
        this.spawnLocation = spawnLocation;
        transform.position = new Vector2(spawnLocation.x, spawnLocation.y);
    }

    public void respawn() {
        transform.position = new Vector2(spawnLocation.x, spawnLocation.y);
    }
}
