package GUI.Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Button extends Component{

    private Image btnSprite;
    private Image btnSpriteHovered;

    public int width;
    public int height;
    public boolean hovered = false;

    private String text;
    private Text textElement;

    private Color textColor;
    private Color textColorHovered;

    // Image par défaut / Images spécifiées
    public Button(int width, int height, int x, int y, int action, String txt) throws IOException {
        super(x, y, action);

        File Fbtn = new File("img/gui/button.png");
        File FbtnH = new File("img/gui/buttonHovered.png");
        init(width, height, txt, ImageIO.read(Fbtn), ImageIO.read(FbtnH));
    }
    public Button(int width, int height, int x, int y, int action, String txt, Image sprite, Image spriteHover){
        super(x, y, action);
        init(width, height, txt, sprite, spriteHover);
    }

    private void init(int w, int h, String txt, Image btnSprite, Image btnSpriteHovered){
        this.width = w;
        this.height = h;
        this.btnSprite = btnSprite;
        this.btnSpriteHovered = btnSpriteHovered;
        this.textColor = new Color(10, 100, 25);
        this.textColorHovered = new Color(10, 200, 25);

        this.textElement = null;
        if (txt != null && !txt.isBlank()){
            this.textElement = new Text(getX()+w/2, getY()+h/2, h/2, txt, Text.Padding.CENTERED, new Color(10, 100, 25));
        }

    }

    public boolean getHovered(){return this.hovered;}

    // Set Hover soit en forçant soit avec des coordonnées de souris.
    public void setHovered(boolean hovered) {this.hovered = hovered;}
    public void setHovered(int x, int y){
        this.hovered = false;
        if (x >= getX() && x <= getX() + this.width)
            if (y >= getY() && y <= getY() + this.height) {
                this.hovered = true;
            }
    }

    // Dessiner le bouton
    public void draw(Graphics2D g2d) {

        g2d.drawImage(hovered ? btnSpriteHovered : btnSprite, getX(), getY(), width, height, null);

        if (this.textElement != null){
            textElement.setColor(hovered ? textColorHovered : textColor);
            textElement.draw(g2d);
        }

    }

}
