package Kernel;

import Utils.Vector2;

import java.awt.*;

/**
 * A text is a string that is displayed on the screen the same way as an entity.
 * It is different in that the text is a simple string with a color and font style and size with no collidier or sprite.
 */
public class Text {

    private Vector2 position;
    private Font font;
    private Color color;
    private String text;

    /**
     * Creates a text object with the specified position, font style, color and string value.
     * @param position The position of the text relative to the origin point of the window.
     * @param font The font style of the text.
     * @param color The color of the text.
     * @param text The String value of the text.
     */
    public Text(Vector2 position, Font font, Color color, String text) {
        this.position = position;
        this.font = font;
        this.color = color;
        this.text = text;
    }

    /**
     * Creates a text object with the specified font style and string value, with the default position being the origin
     * point of the window and the default color being black.
     * @param font The font style of this text.
     * @param text The String value of this text.
     */
    public Text(Font font, String text) {
        this.position = new Vector2(0,0);
        this.font = font;
        this.color = new Color(0,0,0);
        this.text = text;
    }

    /**
     * Returns the current position of the text relative to the origin point of the window.
     * @return The current position of the text.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the position of the text relative to the origin point of the window with the indicated vector.
     * @param position The future position of the text as a vector.
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * Sets the position of the text relative to the origin point of the window with the indicated x and y coordinates.
     * @param x The x coordinate of the text.
     * @param y The y coordinate of the text.
     */
    public void setPosition(float x, float y){
        this.position = new Vector2(x, y);
    }

    /**
     * Returns the current font style of the text.
     * @return The current font style of the text.
     */
    public Font getFont() {
        return font;
    }

    /**
     * Sets the font style of this text to the specified font.
     * @param font The font style of the text.
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * Returns the color of this text.
     * @return The color of the text.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this text to the specified color.
     * @param color The new color of the text.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the string value of the text.
     * @return The String associated to this text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the String associated to this text.
     * @param text The String to associate to the text.
     */
    public void setText(String text) {
        this.text = text;
    }
}
