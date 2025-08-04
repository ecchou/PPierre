package GUI.Windows;

import GUI.Components.Button;
import GUI.Components.Text;
import GUI.Scenes.MainMenu;
import GUI.Scenes.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Window{

    private List<Scene> scenes;
    private int currentScene;

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

    public void action(int action){
        System.out.println("Bouton " + action + " cliqué!");
    }

}
