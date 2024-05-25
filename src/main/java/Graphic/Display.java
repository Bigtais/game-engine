package Graphic;

import Gameplay.ScoreManager;
import Kernel.Entity;
import Kernel.Kernel;
import Kernel.Sprite;
import Kernel.Text;
import Utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Implementation of a graphics engine.
 */
public class Display extends JPanel {



    /**
     * Returns the absolute position of the entity's sprite.
     * @param e The entity that has a drawable sprite.
     * @return The sprite's absolute position.
     */
    private Vector2 getSpriteAbsolutePosition(Entity e){
        return new Vector2(e.transform.position).add(e.sprite.offset);
    }

    /**
     * Returns a rotated instance of the image.
     * @param image The image to rotate.
     * @param rotation The rotation angle in degrees.
     * @return The rotated image.
     */
    private Image getRotatedImage(Image image, float rotation){
        BufferedImage bufferedImage = new BufferedImage(image.getHeight(null), image.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.rotate(Math.toRadians(-rotation), (double) image.getHeight(null) /2, (double) image.getWidth(null) /2);
        graphics2D.drawImage(image, 0, 0, null);

        return bufferedImage;
    }

    /**
     * Returns a scaled instance of an image according to the scale.
     * @param image The image to rescale.
     * @param scale The scale of the image.
     * @return The scaled image of this sprite.
     */
    private Image getScaledImage(Image image, Vector2 scale){
        return image.getScaledInstance(
                (int) (scale.x * image.getWidth(null)),
                (int) (scale.y * image.getHeight(null)),
                Image.SCALE_FAST
        );
    }

    /**
     * Returns the processed image related to the sprite after scaling and rotation.
     * @param sprite The sprite to draw.
     * @return The processed image related to the sprite.
     */
    private Image getProcessedImage(Sprite sprite){
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(sprite.source));

        return getScaledImage(
                getRotatedImage(imageIcon.getImage(), sprite.rotationOffset),
                sprite.scale);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        List<Entity> entities = Kernel.getKernel().getInstances();
        synchronized (entities) {
            for (Entity entity : entities) {
                graphics2D.rotate(0);
                if (entity.sprite.visible) {
                    graphics2D.drawImage(
                            getProcessedImage(entity.sprite),
                            (int) getSpriteAbsolutePosition(entity).x,
                            (int) getSpriteAbsolutePosition(entity).y,
                            null
                    );
                }
            }
        }

        List<Text> texts = Kernel.getKernel().getTexts();
        synchronized (texts) {
            for (Text text : texts) {
                graphics2D.setFont(text.getFont());
                graphics2D.setColor(text.getColor());
                graphics2D.drawString(text.getText(), text.getPosition().x, text.getPosition().y);
            }
        }
    }


}
