package GUI.Windows;

import GUI.Scenes.*;
import GUI.Components.Component;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MainWindow extends Window{

    // scènes constantes
    private List<Scene> scenes = List.of(
            new MainMenu(),                 // Scène 0 : Menu Princiapl
            new PierresMenu(),              // Scène 1 : Menu Pierres
            new DeckEditor_Selector(),      // Scène 2 : Editeur de Deck (selecteur)
            new DeckEditor()                // Scène 3 : Editeur de Deck
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

        try{
            scenes.get(index).switchedScene();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void updateScene(){
        setComponents(scenes.get(currentScene).getComponents());

        // rechecker les hover instant pour le spam dans le deck editor par exemple
        for (Component c : scenes.get(currentScene).getComponents()) {
            c.setHovered(mouseX, mouseY);
        }
    }

    // peut être plutôt à déléguer à la scène
    @Override
    protected void drawBG(Graphics2D g2d){
        scenes.get(currentScene).drawBG(g2d);
    }

    public void action(int action) throws IOException {

        System.out.println("Action" + currentScene + ": " + action);

        switch (currentScene){
            case 0 -> action_mainMenu(action);
            case 1 -> action_pierresMenu(action);
            case 2 -> action_deckEditorSelect(action);
            case 3 -> action_deckEditor(action);
        }

    }

    private void action_mainMenu(int action){

        // Voir la scène pour les actions

        switch(action){
            case 3 -> switchScene(2);
            case 4 -> switchScene(1);
        }

    }

    private void action_pierresMenu(int action) throws IOException {

        if (action == -1)
            switchScene(0);
        else if (action != 0){
            scenes.get(currentScene).handleAction(action);
            updateScene();
        }

    }

    private void action_deckEditorSelect(int action) {

        if  (action == -1)
            switchScene(0);
        else if (action == -4){
            switchScene(3);
        }

    }

    private void action_deckEditor(int action) throws IOException {

        if (action == -1)
            switchScene(2);
        else if (action != 0) {
            scenes.get(currentScene).handleAction(action);
            updateScene();
        }

    }

}
