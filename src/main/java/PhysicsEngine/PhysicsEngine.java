package PhysicsEngine;

import Kernel.Entity;
import Kernel.Collider;
import Utils.Vector2;

import java.util.ArrayList;

/**
 * Implementation of a physics engine.
 */
public class PhysicsEngine implements IPhysicsEngine {

    /**
     * Returns the collider's absolute position associated to the entity.
     * @param e The entity associated to the collider.
     * @return The collider's absolute position.
     */
    private Vector2 getColliderAbsolutePosition(Entity e){
        return new Vector2(e.transform.position).add(e.collider.offset);
    }

    /**
     * Verifies if the entity A's associated collider has points inside entity B's associated collider.
     * An offset can be provided as a third argument. This will calculate the collision with an offset applied on the entity a
     * @param a The entity from which the points are taken.
     * @param b The entity to which we verify if the points are inside its collider.
     * @return true if the collider has points inside, false otherwise.
     */
    private boolean hasPointsColliding(Entity a, Entity b) {
        if (isPointMeetingEntity(b, getColliderAbsolutePosition(a))) return true;
        if (isPointMeetingEntity(b, getColliderAbsolutePosition(a).add(a.collider.size))) return true;
        if (isPointMeetingEntity(b, getColliderAbsolutePosition(a).add(new Vector2(a.collider.size.x,0))))
            return true;
        if (isPointMeetingEntity(b,getColliderAbsolutePosition(a).add(new Vector2(0,a.collider.size.y))))
            return true;
        return false;
    }

    private boolean hasPointsColliding(Entity a, Entity b, Vector2 offset) {

        if (isPointMeetingEntity(b, getColliderAbsolutePosition(a).add(offset))) return true;
        if (isPointMeetingEntity(b, getColliderAbsolutePosition(a).add(a.collider.size).add(offset))) return true;
        if (isPointMeetingEntity(b, getColliderAbsolutePosition(a).add(new Vector2(a.collider.size.x,0)).add(offset)))
            return true;
        if (isPointMeetingEntity(b,getColliderAbsolutePosition(a).add(new Vector2(0,a.collider.size.y)).add(offset)))
            return true;
        return false;
    }

    /**
     * Move the entity by adding the Vector2 v to its transform.position
     * @param e The entity to move.
     * @param v The movement vector
     */
    public static void move(Entity e, Vector2 v) {
        e.transform.position = e.transform.position.add(v);
    }


    /**
     * Move the entity by adding a Vector2 calculated by a given distance and direction
     * @param e The entity to move.
     * @param distance The distance of the movement
     * @param direction The direction of the movement (0 is right, 90 is top, 180 is left, 270 is down)
     */
    public static void moveDirection(Entity e, int distance, int direction){

        Vector2 movement = new Vector2(
                (float)Math.cos(Math.toRadians(direction)),
                -(float)Math.sin(Math.toRadians(direction))
        );

        move(e, new Vector2(movement.mult(new Vector2(distance, distance))));


    }

    /**
     * Set the position of an Entity to a given Vector2
     * @param e The entity to move.
     * @param v The new entity's position.
     */
    public void setPosition(Entity e, Vector2 v){
        e.transform.position = v;
    }

    /**
     * Check if a given coordinate (provided as a Vector2) is inside a given entity's collider
     * @param e The entity to move.
     * @param location the point to be checked.
     */
    public Boolean isPointMeetingEntity(Entity e, Vector2 location){
        return (getColliderAbsolutePosition(e).x <= location.x
                && getColliderAbsolutePosition(e).x+e.collider.size.x >= location.x
                && getColliderAbsolutePosition(e).y <= location.y
                && getColliderAbsolutePosition(e).y+e.collider.size.y >= location.y);
    }

    /**
     * Check if two entity's colliders are overlapping
     * @param a The first entity.
     * @param b The second entity.
     */
    public Boolean isMeetingEntity(Entity a, Entity b){
        if (hasPointsColliding(a, b)) return true;
        if (hasPointsColliding(b, a)) return true;
        return false;
    }

    /**
     * Check if two entity's colliders are overlapping with a given offset applied to entity a
     * @param a The first entity.
     * @param b The second entity.
     * @param offset the offset that will be applied to entity's a collider during the checking
     */
    public Boolean isMeetingEntity(Entity a, Entity b,Vector2 offset){
        if (hasPointsColliding(a, b, offset)) return true;
        if (hasPointsColliding(b, a, new Vector2(offset.mult(new Vector2(-1,-1))))) return true;
        offset.mult(new Vector2(-1,-1));//can be removed when Vector2 Class will be fixed
        return false;
    }


    /**
     * Check if The entity e's collider is overlapping with an instance'collider from the arrayList entities
     * @param e The first entity.
     * @param entities The second entity.
     */
    public Boolean isMeetingEntities(Entity e, ArrayList<Entity> entities){
        for(int i=0; i < entities.size();i++){
            if(isMeetingEntity(e,entities.get(i))){
                return true;
            }
        }
        return false;
    }


    /**
     * Check if The entity e's collider with a given offset is overlapping with an instance'collider from the arrayList entities
     * @param e The first entity.
     * @param entities The second entity.
     * @param offset the offset that will be applied to entity's a collider during the checking
     */
    public Boolean isMeetingEntities(Entity e, ArrayList<Entity> entities, Vector2 offset){
        for(int i=0; i < entities.size();i++){
            if(isMeetingEntity(e,entities.get(i), offset)){
                return true;
            }
        }
        return false;
    }

    /**
     * Move the entity a in a given direction up to a given maximum distance
     * until it contacts with the Entity b
     * @param a The first entity.
     * @param b The second entity to get in contact with.
     * @param direction The direction in which should be moved the first entity
     * @param maxDistance The maximum distance the first entity can be moved away
     */
    public void moveToContact(Entity a, Entity b, int direction, int maxDistance){
        if(maxDistance>0){
            for (int i=0;i<maxDistance;i++){

                moveDirection(a,1,direction);
                if(isMeetingEntity(a,b)){
                    moveDirection(a,-1,direction);
                    break;
                }
            }
        }
    }


    /**
     * Move the entity e in a given direction up to a given maximum distance
     * until it contacts with an instance from the arrayList entities
     * @param e The first entity.
     * @param entities The arrayList of entities to get in contact with.
     * @param direction The direction in which should be moved the first entity
     * @param maxDistance The maximum distance the first entity can be moved away
     */
    public void moveToContact(Entity e, ArrayList<Entity> entities, int direction, int maxDistance){
        if(maxDistance>0){
            for (int i=0;i<maxDistance;i++){
                moveDirection(e,1,direction);
                if(isMeetingEntities(e,entities)){
                    moveDirection(e,-1,direction);
                    break;
                }
            }
        }
    }


    /**
     * Change the size of a given collider
     * @param c The collider to be changed.
     * @param newSize The new size the collider c will be given.
     */
    public void setColliderSize(Collider c, Vector2 newSize){
        c.size = newSize;
    }


    /**
     * Change the offset of a given collider
     * @param c The collider to be changed.
     * @param newOffset The new offset the collider c will be given.
     */
    public void setColliderOffset(Collider c, Vector2 newOffset){
        c.offset = newOffset;
    }
}
