package Gameplay;

import Kernel.Entity;
import Kernel.Kernel;
import Utils.Vector2;
import InputEngine.*;
import PhysicsEngine.*;
import sound.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * The player class
 */
public final class Player extends Entity {


    InputEngine input = Kernel.getKernel().getInputEngine();
    PhysicsEngine physics = Kernel.getKernel().getPhysicEngine();

    Vector2 dir = new Vector2();
    int dirInt=0;

    float speed = 2f;

    int invicibilityTimer=0, defaultInvicibilityDuration=60*8;

    /**
     * Move the player by adding a given vector to it's transform.position
     * @param v
     */
    private void move(Vector2 v){
        transform.position.x += v.x;
        transform.position.y += v.y;
    }

    /**
     * Create a new Player object
     */
    Player() {
        transform.position = new Vector2(50, 55);
        sprite.scale = new Vector2(1, 1);
        //le path est a changer
        sprite.source = "/Sprites/pacman.png";
        sprite.visible = true;
        sprite.rotationOffset = 0;
        collider.size = new Vector2(30, 30);
        collider.offset = new Vector2(1,1);
        GameplayMain.player = this;
    }

    public boolean isInvicible()
    {
        return invicibilityTimer>0;
    }

    public void setInvincible()
    {
        invicibilityTimer = defaultInvicibilityDuration;
    }

    static float h=0;
    @Override
    public void update() {
        invicibilityTimer = (invicibilityTimer > 0 ) ? (--invicibilityTimer) : 0;
        //h +=0.005f;
        //Color color = new Color(Color.HSBtoRGB(h,1,1));

        //Kernel.getKernel().setBackgroundColor(color);

        if(input.isDown(KeyEvent.VK_LEFT)) {
            if(!physics.isMeetingEntities(this,GameplayMain.collisions,new Vector2(-speed,0))){
                dir = new Vector2(-speed, 0);
                dirInt = 180;
                sprite.rotationOffset = dirInt;
            }
        }
        if(input.isDown(KeyEvent.VK_RIGHT)) {
            if(!physics.isMeetingEntities(this,GameplayMain.collisions,new Vector2(speed,0))) {
                dir = new Vector2(speed, 0);
                dirInt = 0;
                sprite.rotationOffset = dirInt;
            }
        }
        if(input.isDown(KeyEvent.VK_UP)) {
            if(!physics.isMeetingEntities(this,GameplayMain.collisions,new Vector2(0,-speed))) {
                dir = new Vector2(0, -speed);
                dirInt = 90;
                sprite.rotationOffset = dirInt;
            }
        }
        if(input.isDown(KeyEvent.VK_DOWN)) {
            if(!physics.isMeetingEntities(this,GameplayMain.collisions,new Vector2(0,speed))) {
                dir = new Vector2(0, speed);
                dirInt = 270;
                sprite.rotationOffset = dirInt;
            }
        }

        if(transform.position.x<-32){
            transform.position.x=20*32;
        }

        if(transform.position.x>20*32){
            transform.position.x=-32;
        }


        if(!physics.isMeetingEntities(this,GameplayMain.collisions,dir)){
            move(dir);
        }else{
            physics.moveToContact(this,GameplayMain.collisions,dirInt,(int)speed);
        }


    }


}
