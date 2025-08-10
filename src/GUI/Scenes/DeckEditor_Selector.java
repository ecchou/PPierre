package GUI.Scenes;

import GUI.Components.Buttons.Button;
import GUI.Components.Buttons.GrayableButton;
import Game.Decks.DeckManager;

import java.io.IOException;

public class DeckEditor_Selector extends DeckSelector{

    // Action -6 : Supprimer le Deck Selectionné
    // Action -5 : Modifier le Deck Selectionné
    // Action -4 : Créer un Deck
    // Action -3 : Page Précédente
    // Aciton -2 : Page Suivante
    // Action -1 : Retour au Menu Principal
    // Action 0 : Rien
    // Action N : Selectionner le Deck N

    private final GrayableButton btnEdit;
    private final GrayableButton btnDelete;

    public DeckEditor_Selector() throws IOException {
        super();

        ///  BUTTONS
        int GAP = 10;
        addComponent(new Button(120, 50, 650+GAP, 100, -4, "creer"));
        btnEdit = new GrayableButton(120, 50, 650+GAP, 100+50+GAP, -5, "modifier");
        btnDelete = new GrayableButton(120, 50, 650+GAP, 100+2*(50+GAP), -6, "supprimer");
        grayButtons(true);
        addComponent(btnEdit);
        addComponent(btnDelete);

        addComponent(new Button(120, 50, 150-GAP-120, 100, -7, "sauvegarder"));
        addComponent(new Button(120, 50, 150-GAP-120, 100+50+GAP, -8, "charger"));


    }

    @Override
    public void handleAction(int action){

        if (action == -6){
            DeckManager.removeDeck(getSelected());
            try{
                setComponents(goToPage(page));
            } catch (IOException e) {
                e.printStackTrace();
            }
            select(0);
        }
        else if (action == -7){
            DeckManager.saveDecks();
        }
        else if (action == -8){
            try{
                DeckManager.loadDecks();
                setComponents(goToPage(1));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            super.handleAction(action);
        }

    }

    @Override
    public void select(int indexDeck){

        super.select(indexDeck);
        if (getSelected() != 0)
            grayButtons(false);
        else
            grayButtons(true);

    }

    private void grayButtons(boolean grayed){

        btnEdit.setGrayed(grayed);
        btnDelete.setGrayed(grayed);

    }

}
