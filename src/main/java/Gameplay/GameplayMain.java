package Gameplay;

import Kernel.*;
import LevelEditor.*;
import sound.SoundEngine;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


/**
 * GameplayMain class is used to define everything proper to the game (not the engine)
 */
public final class GameplayMain extends Gameplay {

    public static ArrayList<Entity> collisions = new ArrayList<Entity>();

    public static Player player;

    @Override
    public void launch(){
        super.launch();
        kernel.setBackgroundColor(Color.black);

        LevelEditor.interpreter.put("M", new WallObjectFactory());
        LevelEditor.interpreter.put("B",new PointObjectFactory());
        LevelEditor.interpreter.put("P", new PlayerFactory());
        LevelEditor.interpreter.put("F", new FollowerGhostFactory());
        LevelEditor.interpreter.put("D", new DumbGhostFactory());
        LevelEditor.interpreter.put("S", new SuperGumObjectFactory());

        startLevel();
    }

    public static void startLevel() {

        ScoreManager.getInstance().resetScore();

        int size = kernel.getInstances().size();
        for(int i = 0; i<size;i++){
            kernel.getInstances().get(i).destroy();
        }

        try {
            LevelEditor.startLevel(GameplayMain.class.getResourceAsStream("/Level/Level"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }



    }

    public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException {
        GameplayMain gameplayMain = new GameplayMain();
        gameplayMain.launch();
    }

}
