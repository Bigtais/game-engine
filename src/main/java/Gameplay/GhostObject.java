package Gameplay;

import AI.MovingStrategy;
import Kernel.Entity;
import Kernel.Kernel;
import PhysicsEngine.PhysicsEngine;
import Utils.Vector2;
import sound.Sound;

public final class GhostObject extends Entity {

    Kernel kernel = GameplayMain.kernel;
    PhysicsEngine physics = kernel.getPhysicEngine();
    MovingStrategy movingStrategy;

    public GhostObject(MovingStrategy movingStrategy) {
        transform.position = new Vector2(288, 256);
        sprite.scale = new Vector2(1, 1);
        //le path est a changer
        sprite.source = "/Sprites/ghost.png";
        sprite.visible = true;
        collider.size = new Vector2(30, 30);
        collider.offset = new Vector2(1,1);
        this.movingStrategy = movingStrategy;
        movingStrategy.attach(this);
    }
    @Override
    public void update() {
        if (physics.isMeetingEntity(this, GameplayMain.player)){
            if(!GameplayMain.player.isInvicible())
            {
                System.out.println("GameOver");
                kernel.getSoundEngine().playSound(new Sound("/Sounds/pacman_death.mp3"));
                GameplayMain.startLevel();
            }
            else {
                ScoreManager.getInstance().addPoints(100);
                System.out.println("ate a ghost");
                kernel.getSoundEngine().playSound(new Sound("/Sounds/pacman_eatghost.mp3"));
                respawn();
                movingStrategy.reset();
            }
        }

        movingStrategy.move();
    }

    public void moveTo(Vector2 target) {
        Vector2 newPos = target.sub(transform.position);
        newPos = newPos.normalize();
        transform.position.x += Math.round(newPos.x) * 2;
        transform.position.y += Math.round(newPos.y) * 2;
    }
}
