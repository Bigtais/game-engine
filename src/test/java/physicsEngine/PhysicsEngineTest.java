package physicsEngine;

import Kernel.Collider;
import Kernel.Entity;
import Kernel.Sprite;
import Kernel.Transform;
import PhysicsEngine.PhysicsEngine;
import Utils.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhysicsEngineTest {

    Entity entityA, entityB;

    PhysicsEngine engine;

    Vector2 transformVector = new Vector2(0,0);
    Vector2 colliderOffsetVector = new Vector2(3,3);
    Vector2 colliderSizeVector = new Vector2(5,5);

    @BeforeEach
    void init(){
        engine = new PhysicsEngine();

        Transform transformEntityA = new Transform();
        transformEntityA.position = transformVector;

        Transform transformEntityB = new Transform();
        transformEntityB.position = transformVector;

        Collider collider = new Collider();
        collider.offset = colliderOffsetVector;
        collider.size = colliderSizeVector;

        Sprite sprite = new Sprite();

        entityA = new Entity(transformEntityA, collider, sprite);

        entityB = new Entity(transformEntityB, collider, sprite);
    }

    @Test
    void move() {
        Vector2 movement = new Vector2(3,2);

        Vector2 originalTransformVector = new Vector2(transformVector);

        engine.move(entityA, movement);

        assertEquals(originalTransformVector.x + movement.x, entityA.transform.position.x);
        assertEquals(originalTransformVector.y + movement.y, entityA.transform.position.y);

    }

    @Test
    void setPosition() {
        Vector2 newPosition = new Vector2(3,2);

        engine.setPosition(entityA, newPosition);

        assertEquals(newPosition.x, entityA.transform.position.x);
        assertEquals(newPosition.y, entityA.transform.position.y);

    }

    @Test
    void isPointMeetingEntity() {
        Vector2 point = new Vector2(4,4);

        assertEquals(true, engine.isPointMeetingEntity(entityA, point));

        point = new Vector2(1,1);
        assertEquals(false, engine.isPointMeetingEntity(entityA, point));

    }

    @Test
    void isMeetingEntity() {

        // Complete overlap
        assertEquals(true, engine.isMeetingEntity(entityA, entityB));


        // Upper left point of A overlaps
        Vector2 newPosition = new Vector2(4, 4);
        engine.setPosition(entityA, newPosition);
        assertEquals(true, engine.isMeetingEntity(entityA, entityB));


        // lower right point of A overlaps
        newPosition = new Vector2(-3, -3);
        engine.setPosition(entityA, newPosition);
        assertEquals(true, engine.isMeetingEntity(entityA, entityB));


        // lower left point of A overlaps
        newPosition = new Vector2(4,-3);
        engine.setPosition(entityA, newPosition);
        assertEquals(true, engine.isMeetingEntity(entityA, entityB));


        // Upper right point of A overlaps
        newPosition = new Vector2(-3, 4);
        engine.setPosition(entityA, newPosition);
        assertEquals(true, engine.isMeetingEntity(entityA, entityB));


        // No overlap
        newPosition = new Vector2(8, 8);
        engine.setPosition(entityA, newPosition);
        assertEquals(false, engine.isMeetingEntity(entityA, entityB));
    }

    @Test
    void setColliderSize() {
        Vector2 newSize = new Vector2(1, 1);

        engine.setColliderSize(entityA.collider, newSize);

        assertEquals(entityA.collider.size.x, newSize.x);
        assertEquals(entityA.collider.size.y, newSize.y);
    }

    @Test
    void setColliderOffset() {
        Vector2 newOffset = new Vector2(1, 1);

        engine.setColliderOffset(entityA.collider, newOffset);

        assertEquals(entityA.collider.offset.x, newOffset.x);
        assertEquals(entityA.collider.offset.y, newOffset.y);
    }
}