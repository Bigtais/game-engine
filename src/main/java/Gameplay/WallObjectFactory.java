package Gameplay;

import Kernel.Entity;
import Kernel.EntityFactory;

public class WallObjectFactory implements EntityFactory {
	
	@Override
	public Entity createEntity(){

		return new WallObject();
	}
}
