package GUI.Components;

import java.awt.*;
import java.io.IOException;

public abstract class ClickableComponent extends Component {

    private boolean hovered;

    public ClickableComponent(int x, int y, int actionOnClick){
        super(x, y, actionOnClick);
        this.hovered = false;
    }

    ///  METHODS
    // Par défaut pas de système de hover et la méthode draw() reste abstraite
    public abstract void draw(Graphics2D g2d) throws IOException;
    public abstract void setHovered(int x, int y);
    public void setHovered(boolean b){this.hovered = b;}

    /// GETTERS
    @Override public boolean isHovered(){return hovered;}

}
