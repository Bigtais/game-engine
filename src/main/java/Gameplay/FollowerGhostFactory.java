package Gameplay;

import AI.FollowerMove;
import Kernel.Entity;
import Kernel.EntityFactory;

public class FollowerGhostFactory implements EntityFactory {
    @Override
    public Entity createEntity(){
        return new GhostObject(new FollowerMove());
    }
}
