package GUI.Windows;

import GUI.Scenes.MainMenu;
import GUI.Scenes.PierresMenu;
import GUI.Scenes.Scene;
import GUI.Components.Component;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Window{

    private List<Scene> scenes = List.of(
            new MainMenu(),     // Scène 0 : Menu Princiapl
            new PierresMenu()   // Scène 1 : Menu Pierres
    );
    private int currentScene;

    public MainWindow() throws IOException {
        super("PPierre", 800, 600);
        switchScene(0);
    }

    public void switchScene(int index){
        setComponents(scenes.get(index).getComponents());
        for (Component c : scenes.get(index).getComponents()) {
            c.setHovered(false);
        }
        this.currentScene = index;
    }

    // peut être plutôt à déléguer à la scène
    @Override
    protected void drawBG(Graphics2D g2d){
        scenes.get(currentScene).drawBG(g2d);
    }

    public void action(int action){

        System.out.println("Action" + currentScene + ": " + action);

        switch (currentScene){
            case 0 -> action_mainMenu(action);
            case 1 -> action_pierresMenu(action);
        }

    }

    private void action_mainMenu(int action){

        // Voir la scène pour les actions

        switch(action){
            case 4 -> switchScene(1);
        }

    }

    private void action_pierresMenu(int action){

        switch(action){
            case -1 -> switchScene(0);
        }

    }

}
