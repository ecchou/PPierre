package GUI.Components;

import java.awt.*;

public class Text extends Component{

    public enum Padding{
        LEFT,
        RIGHT,
        CENTERED
    }

    private int fontSize;
    private String text;
    private Padding padding;

    private Font font;
    private Color color;

    ///  CONSTRUCTEURS
    public Text(int x, int y, int fontSize, String text, Padding padding, Color color){
        super(x, y, 0);
        this.fontSize = fontSize;
        this.text = text;
        this.padding = padding;
        this.color = color;

        this.font = new Font("Arial", Font.PLAIN, fontSize);
    }
    public Text(int x, int y, int fontSize, String text, Padding padding, Color color, int action){
        super(x, y, action);
        this.fontSize = fontSize;
        this.text = text;
        this.padding = padding;
        this.color = color;

        this.font = new Font("Arial", Font.PLAIN, fontSize);
    }

    ///  DESSIN
    public void setColor(Color c){
        this.color = c;
    }
    public void draw(Graphics2D g2d){
        if (g2d.getFont() != font)
            g2d.setFont(font);
        g2d.setColor(color);

        // Délires de centrage vertical & horizontal
        FontMetrics metrics = g2d.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        int drawX = 0;
        if (padding == Padding.CENTERED){
            drawX = getX() - textWidth / 2;
        }
        else if (padding == Padding.LEFT){
            drawX = getX();
        }
        else if (padding == Padding.RIGHT){
            drawX = getX() - textWidth;
        }
        int drawY = getY() - (textHeight / 2) + metrics.getAscent();

        g2d.drawString(text, drawX, drawY);
    }

}
