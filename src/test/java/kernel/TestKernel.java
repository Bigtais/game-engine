package kernel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Kernel.Kernel;
import Kernel.Entity;

public class TestKernel {
    private Kernel kernel;

    /**
     * Before each test, creates a new instance of {@code Kernel} class
     */

    @BeforeEach
    public void setUp() {
        kernel = Kernel.getKernel();
    }

    /**
     * Tests the {@code createInstance} method
     * This test verify two things :
     * A instance is successfully created and is not null
     * The created instance is correctly added to the kernels list of instances
     */
    @Test
    public void testCreateInstance() {
        Entity instance = kernel.createInstance();
        assertNotNull(instance, "Instance should not be null after creation");
        assertTrue(kernel.getInstancesToCreate().contains(instance), "Created instance should be in the instance list");
    }

    /**
     * Tests the {@code createInstance(Entity baseEntity)} method
     * This test verify two things :
     * A instance is successfully created from an entity and is not null
     * The created instance is correctly added to the kernels list of instances
     */
    @Test
    public void testCreateInstanceFromEntity() {
        Entity entity = new Entity();
        Entity instanceFromEntity = kernel.createInstance(entity);
        assertNotNull(instanceFromEntity, "Instance from entity should not be null");
        assertTrue(kernel.getInstancesToCreate().contains(entity), "Created instance from entity should be in the instance list");
    }

    /**
     * Tests the {@code destroyInstance} method
     * This test verify one thing:
     * The instance given is destroyed so is no longer in the kernel's list of instances
     */
    @Test
    public void testDestroyInstance() {
        Entity entity = new Entity();
        kernel.createInstance(entity);
        kernel.destroyInstance(entity);
        assertFalse(kernel.getInstances().contains(entity), "Destroyed instance should not be in the instance list");
    }














}