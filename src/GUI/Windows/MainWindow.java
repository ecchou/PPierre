package GUI.Windows;

import GUI.Scenes.*;
import GUI.Components.Component;
import Game.Decks.DeckManager;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MainWindow extends Window{

    private DeckEditor_Selector deckEditor_selector = new DeckEditor_Selector();
    private DeckEditor deckEditor = new DeckEditor();
    private ServerTest servTest = new ServerTest();

    // scènes constantes
    private List<Scene> scenes = List.of(
            new MainMenu(),                 // Scène 0 : Menu Princiapl
            new PierresMenu(),              // Scène 1 : Menu Pierres
            deckEditor_selector,             // Scène 2 : Editeur de Deck (selecteur)
            deckEditor,                     // Scène 3 : Editeur de Deck
            servTest                        // Scène 4 : Server Test
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
            case 4 -> action_servTest(action);
        }

    }

    private void action_mainMenu(int action){

        // Voir la scène pour les actions

        switch(action){
            case 2 -> switchScene(4);
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

    private void action_deckEditorSelect(int action) throws IOException {

        if  (action == -1)
            switchScene(0);
        else if (action == -4){
            deckEditor.editDeck(null);
            switchScene(3);
        }
        else if (action == -5){
            deckEditor.editDeck(DeckManager.getDeck(deckEditor_selector.getSelected()));
            switchScene(3);
        }
        else if (action != 0)
            scenes.get(currentScene).handleAction(action);

    }

    private void action_deckEditor(int action) throws IOException {

        if (action == -1)
            switchScene(2);
        else if (action != 0) {
            scenes.get(currentScene).handleAction(action);
            updateScene();
        }

    }

    private void action_servTest(int action) throws IOException{



    }

}
