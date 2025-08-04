package GUI.Components;

import java.awt.*;
import java.io.IOException;

public abstract class Component {

    private int x;
    private int y;
    private int action;

    public Component(int x, int y, int actionOnClick){
        this.x = x;
        this.y = y;
        this.action = actionOnClick;
    }

    ///  METHODS
    // Par défaut pas de système de hover et la méthode draw() reste abstraite
    public abstract void draw(Graphics2D g2d) throws IOException;
    public void setHovered(boolean b){}
    public void setHovered(int x, int y){}


    /// GETTERS
    public int getX(){return x;}
    public int getY(){return y;}
    public int getAction(){return action;}
    public boolean getHovered(){return false;}

    /// SETTERS
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setAction(int action){
        this.action = action;
    }
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }



}
