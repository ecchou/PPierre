package GUI.Components.Buttons;

import GUI.Components.Texts.Text;

import java.awt.*;
import java.io.IOException;

public class SelectorButton extends Button {

    ///  Bouton pour les menus de selection

    private static final Color DEFAULT_COLOR = new Color(125, 125, 125, 125);
    private static final Color DEFAULT_COLOR_HOVERED = new Color(212, 212, 212, 125);
    private static final Color DEFAULT_COLOR_SELECTED = new Color(255, 255, 255, 220);
    private static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_GAP = 15;
    private static final int DEFAULT_FONT_SIZE = 20;

    private final Color color;
    private final Color colorHovered;
    private final Color colorSelected;

    private Text txtL;
    private Text txtM;
    private Text txtR;

    private boolean selected;

    public SelectorButton(int x, int y, int width, int height, int action, String txtLeft, String txtMid, String txtRight) throws IOException {
        super(width, height, x, y, action, null);

        if (txtLeft != null && !txtLeft.isBlank())
            this.txtL = new Text(x+DEFAULT_GAP, y+height/2, DEFAULT_FONT_SIZE, txtLeft, Text.Padding.LEFT, DEFAULT_TEXT_COLOR);
        if (txtMid != null && !txtMid.isBlank())
            this.txtM = new Text(x+width/2, y+height/2, DEFAULT_FONT_SIZE, txtMid, Text.Padding.CENTERED, DEFAULT_TEXT_COLOR);
        if (txtRight != null && !txtRight.isBlank())
            this.txtR = new Text(x+width-DEFAULT_GAP, y+height/2, DEFAULT_FONT_SIZE, txtRight, Text.Padding.RIGHT, DEFAULT_TEXT_COLOR);

        color = DEFAULT_COLOR;
        colorHovered = DEFAULT_COLOR_HOVERED;
        colorSelected = DEFAULT_COLOR_SELECTED;
    }

    @Override
    public void draw(Graphics2D g2d){

        // Dessin case de fond
        if (selected)
            g2d.setColor(colorSelected);
        else if (isHovered())
            g2d.setColor(colorHovered);
        else
            g2d.setColor(color);

        g2d.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));

        drawText(g2d, txtL);
        drawText(g2d, txtM);
        drawText(g2d, txtR);

    }

    public void select(){selected = true;}
    public void deselect(){selected = false;}

}
