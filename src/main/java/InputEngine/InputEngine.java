package InputEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
/**
    InputEngine implementation
 */
public class InputEngine implements KeyListener{
    private final Map<Integer, Boolean> buttonState;

    /**
     * InputEngine Constructor
     */
    public InputEngine() {
        buttonState = new HashMap<>();
    }

    /**
     * Listen on events from components
     * @param component
     */
    public void attach(Component component) {
        component.addKeyListener(this);
    }

    /**
     * This method is irrelevant
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Updates the state of a button to true
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        buttonState.put(e.getKeyCode(), true);
    }

    /**
     * Updates the state of a button to false
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        buttonState.put(e.getKeyCode(), false);
    }

    /**
     * Returns the state of the button given its keyCode.
     * @param keyCode
     * @return boolean
     */
    public boolean isDown(int keyCode) {
        if (!buttonState.containsKey(keyCode)) buttonState.put(keyCode, false);
        return buttonState.get(keyCode);
    }
}
