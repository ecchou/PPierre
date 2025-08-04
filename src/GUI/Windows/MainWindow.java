package GUI.Windows;

import GUI.Scenes.MainMenu;
import GUI.Scenes.Scene;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Window{

    private List<Scene> scenes;
    private int currentScene;

    private final Color bgGradient1 = new Color(50, 160, 160);
    private final Color bgGradient2 = new Color(0, 200, 200);

    public MainWindow() throws IOException {
        super("Test", 800, 600);

        List<Scene> scenes = new ArrayList<>();
        scenes.add(new MainMenu());     // Menu Principal : ID 0

        this.scenes = scenes;
        switchScene(0);
    }

    public void switchScene(int index){
        setComponents(scenes.get(index).getComponents());
        this.currentScene = index;
    }

    @Override
    protected void drawBG(Graphics2D g2d){
        GradientPaint gradient = new GradientPaint(0, 0, bgGradient1, 0, getHeight(), bgGradient2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public void action(int action){
        System.out.println("Bouton " + action + " cliqué!");
    }

}
