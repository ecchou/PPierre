package GUI.Windows;

import GUI.Scenes.MainMenu;
import GUI.Scenes.Scene;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Window{

    private List<Scene> scenes = List.of(
            new MainMenu()
    );
    private int currentScene;

    private final Color bgGradient1 = new Color(50, 160, 160);
    private final Color bgGradient2 = new Color(0, 200, 200);

    public MainWindow() throws IOException {
        super("Test", 800, 600);
        switchScene(0);
    }

    public void switchScene(int index){
        setComponents(scenes.get(index).getComponents());
        this.currentScene = index;
    }

    // peut être plutôt à déléguer à la scène
    @Override
    protected void drawBG(Graphics2D g2d){
        GradientPaint gradient = new GradientPaint(0, 0, bgGradient1, 0, getHeight(), bgGradient2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public void action(int action){

        if (currentScene == 0){
            action_mainMenu(action);
        }

    }

    private void action_mainMenu(int action){

        if (action == -1){
            System.exit(0);
            return;
        }

        System.out.println("Bouton " + action + " cliqué!");

    }

}
