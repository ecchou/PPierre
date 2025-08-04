package GUI.Scenes;

import GUI.Components.Button;
import GUI.Components.Component;
import GUI.Components.Picture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Scene {

    public MainMenu() throws IOException {

        super(new ArrayList<Component>());

        final int WIDTH = 800;
        //final int HEIGHT = 600;

        ///  BOUTONS
        final int MAINBUTTON_WIDTH = 300;
        addComponent(new Button(MAINBUTTON_WIDTH, 80, WIDTH/2-MAINBUTTON_WIDTH-20, 300, 1, "Rejoindre"));
        addComponent(new Button(MAINBUTTON_WIDTH, 80, WIDTH/2-MAINBUTTON_WIDTH-20, 400, 2, "Créer"));
        addComponent(new Button(MAINBUTTON_WIDTH, 80, WIDTH/2+20, 300, 3, "Deck"));
        addComponent(new Button(MAINBUTTON_WIDTH, 80, WIDTH/2+20, 400, 4, "Pierres"));

        ///  IMAGES
        addComponent(new Picture(WIDTH/2-500/2, 50, 500, 200, 0, "img/logo.png"));

    }

}
