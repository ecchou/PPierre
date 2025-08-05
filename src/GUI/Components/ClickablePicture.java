package GUI.Components;

import GUI.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClickablePicture extends ClickableComponent{

    private int width;
    private int height;

    private Image image;
    private Image imageHovered;

    // Par défaut filtre blanc
    public ClickablePicture(int x, int y, int width, int height, int action, Image image){
        super(x, y, action);
        this.width = width;
        this.height = height;
        this.image = image;
        imageHovered = ImageUtils.adjustBrightness((BufferedImage) image, 1.5f);
    }

    @Override
    public void setHovered(int x, int y){
        //System.out.println(x + " " + y + " " + getX() + " " + getY() + " " + getWidth() + " " + getHeight());
        if (x >= getX() && x <= getX() + width)
            if (y >= getY() && y <= getY() + height) {
                setHovered(true);
                return;
            }
        setHovered(false);
    }

    @Override
    public void draw(Graphics2D g2d) {

        g2d.drawImage(isHovered() ? imageHovered : image, getX(), getY(), width, height, null);

    }

}
