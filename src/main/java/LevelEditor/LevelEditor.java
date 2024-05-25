package LevelEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import Kernel.*;
import Gameplay.*;
import Utils.Vector2;

public final class LevelEditor {

    public static Vector2 levelScale = new Vector2(32,32);
    public static HashMap<String,EntityFactory> interpreter = new HashMap<>();


    public static void startLevel(InputStream levelFile) throws FileNotFoundException, InstantiationException, IllegalAccessException {

        Scanner scanner = new Scanner(levelFile);

        int y=0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            for (int i = 0; i < line.length(); i++) {
                String character = String.valueOf(line.charAt(i));
                if (interpreter.get(character) != null) {


                    Entity toCreate = interpreter.get(character).createEntity();
                    //Entity e = new toCreate();

                    Entity e = GameplayMain.kernel.createInstance(toCreate);
                    Vector2 spawnLocation = new Vector2(i * levelScale.x, y * levelScale.y);
                    e.setSpawnLocation(spawnLocation);
                }
            }

            y++;

        }

    }
}
