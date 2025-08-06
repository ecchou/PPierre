package GUI.Scenes;

import GUI.Components.Buttons.Button;
import GUI.Components.Component;
import GUI.Components.Pictures.Picture;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends Scene {

    // Action 0 : Rien
    // Action 1 :
    // Action 2 :
    // Action 3 :
    // Action 4 : Infos Pierres

    private final Color bgGradient1 = new Color(50, 160, 160);
    private final Color bgGradient2 = new Color(0, 200, 200);

    public MainMenu() throws IOException {

        super(new ArrayList<Component>());

        ///  BOUTONS
        final int MAINBUTTON_WIDTH = 300;
        final int MAINBUTTON_HEIGHT = 80;
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2-MAINBUTTON_WIDTH-20, 300, 1, "Rejoindre"));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2-MAINBUTTON_WIDTH-20, 400, 2, "Créer"));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2+20, 300, 3, "Deck"));
        addComponent(new Button(MAINBUTTON_WIDTH, MAINBUTTON_HEIGHT, WIDTH/2+20, 400, 4, "Pierres"));

        ///  IMAGES
        addComponent(new Picture(WIDTH/2-500/2, 50, 500, 200, 0, "img/logo.png"));

    }

    @Override
    public void drawBG(Graphics2D g2d){
        GradientPaint gradient = new GradientPaint(0, 0, bgGradient1, 0, HEIGHT, bgGradient2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
