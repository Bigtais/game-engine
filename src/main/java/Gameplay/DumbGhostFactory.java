package Gameplay;

import AI.RandomMove;
import Kernel.Entity;
import Kernel.EntityFactory;

public class DumbGhostFactory implements EntityFactory {
    @Override
    public Entity createEntity(){
        return new GhostObject(new RandomMove());
    }
}
