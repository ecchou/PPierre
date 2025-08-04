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

    Image bg = ImageIO.read(new File("img/gui/pierresMenuBG.png"));

    public PierresMenu() throws IOException {

        super(new ArrayList<Component>());

        ///  BOUTONS
        final int MAINBUTTON_WIDTH = 300;
        final int MAINBUTTON_HEIGHT = 80;
        final int CLOSEBUTTON_SIZE = 50;
        addComponent(new Button(CLOSEBUTTON_SIZE, CLOSEBUTTON_SIZE, WIDTH-20-CLOSEBUTTON_SIZE, 20, -1, null, sprCloseButton, sprCloseButtonHovered));

    }

    @Override
    public void drawBG(Graphics2D g2d){
        g2d.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
    }

}
