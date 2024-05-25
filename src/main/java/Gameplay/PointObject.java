package Gameplay;

import Kernel.*;
import PhysicsEngine.PhysicsEngine;
import Utils.Vector2;
import sound.Sound;
import sound.SoundEngine;

/**
 * Collectable points in the game.
 */
public class PointObject extends Entity {
    private static int soundPoint = 0;

    /**
     * Creates a new collectable point.
     */
    PointObject(){
        transform.position = new Vector2(200, 60);
        sprite.scale = new Vector2(1f, 1f);
        //le path est a changer
        sprite.source = "/Sprites/point.png";
        sprite.visible = true;
        collider.size = new Vector2(4, 4);
        sprite.offset = new Vector2(14,14);
        collider.offset = new Vector2(14,14);
    }

    /**
     * Plays the according point sound when the point is eaten.
     */
    private void playSoundPoint(){
        if (soundPoint > 0)
            Kernel.getKernel().getSoundEngine().playSound(new Sound("/Sounds/pacman_chomp01.mp3"));
        else
            Kernel.getKernel().getSoundEngine().playSound(new Sound("/Sounds/pacman_chomp02.mp3"));
        soundPoint= 1 - soundPoint;
    }

    @Override
    public void update() {
        super.update();


        if (Kernel.getKernel().getPhysicEngine().isMeetingEntity(this,GameplayMain.player)){
            //Kernel.getKernel().destroyInstance(this);
            playSoundPoint();
            ScoreManager.getInstance().addPoints(10); //Points normaux
            destroy();
        }

    }
}
