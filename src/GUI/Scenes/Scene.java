package GUI.Scenes;

import GUI.Components.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Scene {

    protected final static int WIDTH = 800;
    protected final static int HEIGHT = 600;
    protected final static String BUTTON_CLOSE_PATH = "img/gui/buttonClose.png";
    protected final static String BUTTON_CLOSE_HOVERED_PATH = "img/gui/buttonCloseHovered.png";

    Image sprCloseButton = ImageIO.read(new File(BUTTON_CLOSE_PATH));
    Image sprCloseButtonHovered = ImageIO.read(new File(BUTTON_CLOSE_HOVERED_PATH));

    private List<Component> components;

    public Scene(List<Component> components) throws IOException {
        this.components = components;
    }

    public List<Component> getComponents(){return this.components;}

    public void addComponent(Component c){
        // Ajouter un Component (à faire à la création, pas une fois sur une fenêtre)
        components.add(c);
    }
    public void drawBG(Graphics2D g2d){}

}
