package GUI.Components;

import GUI.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrayableButton extends Button{

    private Image grayedSprite;
    private boolean grayed;

    // Image par défaut / Images spécifiées
    public GrayableButton(int width, int height, int x, int y, int action, String txt) throws IOException {
        super(width, height, x, y, action, txt);
        this.grayedSprite = ImageIO.read(new File(getDefaultSpritePath()));
        init();
    }
    public GrayableButton(int width, int height, int x, int y, int action, String txt, Image sprite, Image spriteHover){
        super(width, height, x, y, action, txt, sprite, spriteHover);
        this.grayedSprite = sprite;
        init();
    }
    private void init(){
        this.grayed = false;
        this.grayedSprite = ImageUtils.toGrayScale(this.grayedSprite);
        //this.grayedSprite = ImageUtils.convertToARGB((BufferedImage) this.grayedSprite);
        //this.grayedSprite = ImageUtils.adjustBrightness((BufferedImage) this.grayedSprite, 1.2f);
    }

    ///  SETTERS
    public void setGrayed(boolean b){
        this.grayed = b;
        if (!b)
            this.setHovered(false);
    }

    @Override
    public void setHovered(int x, int y){
        if (!grayed)
            super.setHovered(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (!grayed)
            super.draw(g2d);
        else{
            g2d.drawImage(grayedSprite, getX(), getY(), width, height, null);
            drawText(g2d, Color.DARK_GRAY);
        }
    }

}
