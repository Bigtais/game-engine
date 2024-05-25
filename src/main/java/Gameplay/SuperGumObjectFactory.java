package Gameplay;

import Kernel.Entity;
import Kernel.EntityFactory;

public class SuperGumObjectFactory implements EntityFactory {
    @Override
    public Entity createEntity() {
        return new SuperGumObject();
    }
}
