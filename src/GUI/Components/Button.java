package GUI.Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Button extends ClickableComponent{

    private Image btnSprite;
    private Image btnSpriteHovered;

    public int width;
    public int height;

    private String text;
    private Text textElement;

    private Color textColor;
    private Color textColorHovered;

    // Image par défaut / Images spécifiées
    public Button(int width, int height, int x, int y, int action, String txt) throws IOException {
        super(x, y, action);

        File Fbtn = new File(getDefaultSpritePath());
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

    // Set Hover soit en forçant soit avec des coordonnées de souris.
    public void setHovered(int x, int y){
        //System.out.println("Test : " + getX() + " " + getY() + " " + x + " " + y + " " + this.width + " " + this.height);
        if (x >= getX() && x <= getX() + this.width)
            if (y >= getY() && y <= getY() + this.height) {
                setHovered(true);
                return;
            }
        setHovered(false);
    }

    // Dessiner le bouton
    public void draw(Graphics2D g2d) {

        g2d.drawImage(isHovered() ? btnSpriteHovered : btnSprite, getX(), getY(), width, height, null);
        drawText(g2d);

    }

    protected void drawText(Graphics2D g2d){
        if (this.textElement != null){
            textElement.setColor(isHovered() ? textColorHovered : textColor);
            textElement.draw(g2d);
        }
    }
    protected void drawText(Graphics2D g2d, Color c){
        if (this.textElement != null){
            textElement.setColor(c);
            textElement.draw(g2d);
        }
    }

    protected String getDefaultSpritePath(){
        return "img/gui/button.png";
    }

}
