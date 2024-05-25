package Gameplay;

import Kernel.Entity;
import Kernel.Kernel;
import Utils.Vector2;
import sound.Sound;

public class SuperGumObject extends Entity {

    float a = 0;

    //TODO: changer tout ça pour correspondre à ce que supergum doit vraiment être
    public SuperGumObject()
    {
        transform.position = new Vector2(200, 60);
        sprite.scale = new Vector2(1f, 1f);
        //le path est a changer
        sprite.source = "/Sprites/pacGum.png";
        sprite.visible = true;
        collider.size = new Vector2(16, 16);
        collider.offset = new Vector2(8,8);
        sprite.offset = new Vector2(8,8);
    }

    //TODO: Should chnage this to notify that a super gum has been eaten, how would i do it though ?
    @Override
    public void update() {
        super.update();


        if (Kernel.getKernel().getPhysicEngine().isMeetingEntity(this,GameplayMain.player)){
            ScoreManager.getInstance().addPoints(50);
            Kernel.getKernel().getSoundEngine().playSound(new Sound("/Sounds/pacman_eatfruit.mp3"));
            GameplayMain.player.setInvincible();
            destroy();
        }

        a+=40;

        sprite.scale=new Vector2((float)(1+0.5f*Math.cos(a*Math.PI/180f)),(float)(1+0.5f*Math.cos(a*Math.PI/180f)));
        sprite.offset = new Vector2((float)(8-4*Math.cos(a*Math.PI/180f)),(float)(8-4*Math.cos(a*Math.PI/180f)));

    }
}
