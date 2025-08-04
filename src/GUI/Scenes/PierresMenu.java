package GUI.Scenes;

import GUI.Components.Button;
import GUI.Components.Component;
import GUI.Components.Picture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PierresMenu extends Scene {

    public PierresMenu() throws IOException {

        super(new ArrayList<Component>());

        final int WIDTH = 800;
        //final int HEIGHT = 600;

        ///  BOUTONS
        Image sprCloseButton = ImageIO.read(new File("img/gui/buttonClose.png"));
        Image sprCloseButtonHovered = ImageIO.read(new File("img/gui/buttonCloseHovered.png"));
        final int MAINBUTTON_WIDTH = 300;
        final int MAINBUTTON_HEIGHT = 80;
        final int CLOSEBUTTON_SIZE = 50;
        addComponent(new Button(CLOSEBUTTON_SIZE, CLOSEBUTTON_SIZE, WIDTH-20-CLOSEBUTTON_SIZE, 20, -1, null, sprCloseButton, sprCloseButtonHovered));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2-MAINBUTTON_WIDTH-20, 300, 1, "Rejoindre"));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2-MAINBUTTON_WIDTH-20, 400, 2, "Créer"));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2+20, 300, 3, "Deck"));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2+20, 400, 4, "Pierres"));

        ///  IMAGES
        addComponent(new Picture(WIDTH/2-500/2, 50, 500, 200, 0, "img/logo.png"));

    }

}
