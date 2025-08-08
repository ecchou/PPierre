package GUI.Components.Texts;

import java.awt.*;

public class Message extends Text {

    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
    private final static int GAP = 20;

    private int alpha;
    private int framesLeft;

    public Message(boolean centered, String txt, Color color){

        super(centered ? WIDTH/2 : GAP, centered ? HEIGHT/2 : GAP, 25, txt, centered ? Padding.CENTERED : Padding.LEFT, color);
        this.alpha = 0;

    }

    @Override
    public void draw(Graphics2D g2d){

        if (alpha <= 0)
            return;

        FontMetrics metrics = g2d.getFontMetrics(getFont());
        int textWidth = metrics.stringWidth(getText());
        int textHeight = metrics.getHeight();

        g2d.setColor(new Color(0, 0, 0, (int) Math.round(alpha*0.8)));
        if (getPadding() == Padding.LEFT)
            g2d.fill(new Rectangle(0, 0, textWidth+GAP*2, textHeight+GAP/2));
        else
            g2d.fill(new Rectangle(getX()-textWidth/2-GAP/2, getY()-textHeight/2-GAP/2, textWidth+GAP, textHeight+GAP));

        setColor(new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), alpha));

        super.draw(g2d);

        if (framesLeft <= 0)
            alpha += framesLeft;

        framesLeft--;
    }

    public void show(int time){
        this.alpha = 255;
        this.framesLeft = (int) time * 60;
    }

}
