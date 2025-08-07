package GUI.Scenes;

import GUI.Components.Buttons.Button;

import java.io.IOException;

public class DeckEditor_Selector extends DeckSelector{

    // Action -4 : Créer un Deck
    // Action -3 : Page Précédente
    // Aciton -2 : Page Suivante
    // Action -1 : Retour au Menu Principal
    // Action 0 : Rien
    // Action N : Selectionner le Deck N

    public DeckEditor_Selector() throws IOException {
        super();

        int GAP = 10;
        addComponent(new Button(100, 50, 650+GAP, 100, -4, "creer"));


    }

}
