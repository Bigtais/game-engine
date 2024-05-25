package Gameplay;

import Kernel.Entity;
import Kernel.EntityFactory;

public class PlayerFactory implements EntityFactory {

	@Override
	public Entity createEntity(){
		return new Player();
	}
}
