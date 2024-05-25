package Gameplay;

import Kernel.Entity;
import Kernel.EntityFactory;

/**
 * Factory for the creation of PointObjects.
 */
public class PointObjectFactory implements EntityFactory {
	
	@Override
	public Entity createEntity(){

		return new PointObject();
	}
}
