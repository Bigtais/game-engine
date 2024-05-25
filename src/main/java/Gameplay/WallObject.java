package Gameplay;

import InputEngine.InputEngine;
import Kernel.Entity;
import Kernel.Kernel;
import PhysicsEngine.PhysicsEngine;
import Utils.Vector2;

import java.awt.event.KeyEvent;

public final class WallObject extends Entity {
    public WallObject() {
        transform.position = new Vector2(200, 60);
        sprite.scale = new Vector2(1, 1);
        //le path est a changer
        sprite.source = "/Sprites/mur.png";
        sprite.visible = true;
        collider.size = new Vector2(32, 32);
        GameplayMain.collisions.add(this);
    }
    @Override
    public void update() {

    }
}
