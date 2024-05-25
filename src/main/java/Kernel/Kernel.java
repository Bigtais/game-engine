package Kernel;

import AI.AIEngine;
import Graphic.Display;
import InputEngine.InputEngine;
import PhysicsEngine.PhysicsEngine;
import sound.SoundEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Kernel {

    /**
     * list of all the instances (ie what is actually in the game)
     */
    private final List<Entity> instances = Collections.synchronizedList(new ArrayList<>());
    private final List<Entity> instancesToCreate = Collections.synchronizedList(new ArrayList<>());
    private final List<Entity> instancesToDestroy = Collections.synchronizedList(new ArrayList<>());
    private final List<Text> texts = Collections.synchronizedList(new ArrayList<>());
    private final PhysicsEngine physicsEngine;
    private final SoundEngine soundEngine;
    private final Display graphicsEngine;
    private final InputEngine inputEngine;
    private final AIEngine aiEngine;

    private static Kernel kernel;

    private Kernel()
    {
        physicsEngine = new PhysicsEngine();
        soundEngine = new SoundEngine();
        graphicsEngine = new Display();
        inputEngine = new InputEngine();
        aiEngine = new AIEngine("/Level/Level");
    }

    public static Kernel getKernel(){
        if (kernel == null)
            kernel = new Kernel();

        return kernel;
    }

    public List<Entity> getInstances() {
        return instances;
    }

    public List<Text> getTexts() {
        return texts;
    }

    /**
     * Sets the background color of the main window.
     * @param color The new color of the background.
     */
    public void setBackgroundColor(Color color){
        graphicsEngine.setBackground(color);
    }

    /**
     * create instance with base values
     * @return id of created instance
     */
    public Entity createInstance()
    {
        Entity e  = new Entity();
        instancesToCreate.add(e);
        return e;
    }

    /**
     * create an instance using an entity
     * @param baseEntity entity to derive parameter from
     * @return The instanced entity.
     */
    public Entity createInstance(Entity baseEntity)
    {
        instancesToCreate.add(baseEntity);
        return baseEntity;
    }

    /**
     * Destroys the selected instance.
     * The instance isn't destroyed immediately but is scheduled to be destroyed as soon as possible.
     * @param e e the instance to be destroyed
     */
    public void destroyInstance(Entity e)
    {
        instancesToDestroy.add(e);
    }

    /**
     * Adds a text object displayed on the window.
     * @param text The text object to display.
     * @return This same text object.
     */
    public Text addText(Text text)
    {
        texts.add(text);
        return text;
    }

    /**
     * Removes the text object displayed on the window if it exists.
     * @param text The text to remove.
     */
    public void removeText(Text text)
    {
        texts.remove(text);
    }

    /**
     * Returns the kernel's physicsEngine
     * @return the physics engine
     */
    public PhysicsEngine getPhysicEngine(){
        return physicsEngine;
    }


    /**
     * Returns the kernel's sound engine.
     * @return The Sound engine
     */
    public SoundEngine getSoundEngine(){
        return soundEngine;
    }
    
    /**
     * Returns the input engine associated to the kernel.
     * @return The InputEngine
     */
    public InputEngine getInputEngine() {
        return inputEngine;
    }

    public AIEngine getAiEngine() {
        return aiEngine;
    }

    public List<Entity> getInstancesToCreate() {
        return instancesToCreate;
    }

    /**
     * Launches the kernel.
     */
    public void launchKernel() {
        JFrame frame = new JFrame();
        frame.add(graphicsEngine);
        frame.setResizable(false);
        frame.setSize(20*32-16,22*32);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        inputEngine.attach(frame);

        while (true){
            for (Entity entity : instances) {
                entity.update();
            }
            if (!instancesToCreate.isEmpty()) {
                instances.addAll(instancesToCreate);
                instancesToCreate.clear();
            }
            if (!instancesToDestroy.isEmpty()) {
                for (Entity entity : instancesToDestroy){
                    instances.remove(entity);
                    entity = null;
                }
                instancesToDestroy.clear();
            }
            graphicsEngine.repaint();
            try {
                Thread.sleep(1000/60);
            }
            catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
