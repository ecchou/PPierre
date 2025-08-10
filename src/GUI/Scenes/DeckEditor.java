package GUI.Scenes;

import GUI.Components.Buttons.Button;
import GUI.Components.Component;
import GUI.Components.Pictures.ClickablePicture;
import GUI.Components.Texts.Message;
import GUI.Components.Texts.Text;
import Game.Decks.Deck;
import Game.Decks.DeckManager;
import Game.Pierres.Pierre;
import Game.Pierres.PierreGEN;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DeckEditor extends Scene {

    // Action -3 : Renommer le Deck
    // Action -2 : Sauvegarder le Deck (retourne au Selecteur)
    // Action -1 : Retour au Selecteur de Deck
    // Action 0 : Rien
    // Action N (<100) : Mettre la Pierre d'ID N dans le Deck
    // Action 100+N : Enlever la Nième Pierre du Deck

    private Image bg = ImageIO.read(new File("img/gui/bg/deckEditorBG.png"));

    private static int DECKBOX_X = 50;
    private static int DECKBOX_Y = 50;
    private static int DECKBOX_WIDTH = 300;
    private static int DECKBOX_HEIGHT = 500;

    private final Text nomDeck;

    private final Message msgSaved;
    private final Message msgVide;
    private final Message msgUnnamed;
    private final Message msgLength;

    private int page;

    private Deck deck;

    private Deck deckToModify;
    private boolean editing;

    public DeckEditor() throws IOException {

        super(new ArrayList<>());

        this.deck = new Deck();
        this.editing = false;

        ///  MESSAGES
        msgSaved = new Message(true, "Deck Sauvegardé", Color.WHITE);
        msgVide = new Message(true, "Deck Vide", Color.RED);
        msgUnnamed = new Message(true, "Deck Sans Nom", Color.RED);
        msgLength = new Message(true, "Nom trop Long/Court", Color.RED);

        /// TEXTE
        nomDeck = new Text((DECKBOX_X+DECKBOX_WIDTH+WIDTH)/2, DECKBOX_Y, 30, "", Text.Padding.CENTERED, Color.BLACK);
        addComponent(nomDeck);

        ///  BOUTONS
        addComponent(new Button(70, 70, 300+380+20, 480, -1, null, sprCloseButton, sprCloseButtonHovered));
        addComponent(new Button(135, 550-480, 380, 480, -3, "Renommer", 20));
        addComponent(new Button(150, 550-480, 380+150, 480, -2, "Sauvegarder", 20));

        int GAP = 50;
        int GAP2 = 25;
        int CARD_WIDTH = 100;
        int CARD_HEIGHT = 150;
        int CARDS_PER_ROW = 3;
        int CARDS_PER_COL = 3;

        int cardIndex = 1;
        boolean done = false;
        for (int row = 0; row < CARDS_PER_ROW; row++) {
            for (int col = 0; col < CARDS_PER_COL; col++) {
                if (cardIndex > PierreGEN.getMaxLV1_ID())
                    done = true;
                if (!done) {
                    addComponent(new ClickablePicture(
                            DECKBOX_X+DECKBOX_WIDTH+GAP+(col*(GAP2+CARD_WIDTH)),
                            DECKBOX_Y+(row*(GAP2+CARD_HEIGHT))+GAP,
                            CARD_WIDTH,
                            CARD_HEIGHT,
                            cardIndex,
                            PierreGEN.genererPierre(cardIndex, 0).getCardSprite()
                    ));
                    cardIndex++;
                }
            }
        }

        // Priorité d'affichage
        addComponent(msgSaved);
        addComponent(msgVide);
        addComponent(msgUnnamed);
        addComponent(msgLength);

    }

    @Override
    public void handleAction(int action) throws IOException {

        if (action > 0 && action < 100){
            deck.addPierre(PierreGEN.genererPierre(action, 0));
            setComponents(drawDeck());
        }
        else if (action >= 100){
            deck.removePierre(action-100);
            setComponents(drawDeck());
        }
        else if (action == -2){
            saveDeck();
        }
        else if (action == -3){
            renameDeck();
        }

    }

    private List<Component> drawDeck() throws IOException {

        List<Component> bufferedComponents = new ArrayList<>();
        for (Component c : getComponents())
            if (c.getAction() < 100)
                bufferedComponents.add(c);

        List<Pierre> pierres = deck.getPierres();
        int CARDS_PER_ROW = 3;
        int CARDS_PER_COL = 5;
        int CARD_WIDTH = DECKBOX_WIDTH/CARDS_PER_ROW;
        int CARD_HEIGHT = DECKBOX_HEIGHT/CARDS_PER_COL;
        int i = 0;
        boolean done = false;
        for (int col = 0; col < CARDS_PER_ROW; col++) {
            for (int row = 0; row < CARDS_PER_COL; row++) {
                if (i >= pierres.size())
                    done = true;
                if (done) break;
                bufferedComponents.add(new ClickablePicture(
                        DECKBOX_X+(col*CARD_WIDTH),
                        DECKBOX_Y+(row*CARD_HEIGHT),
                        CARD_WIDTH,
                        CARD_HEIGHT,
                        100+i,
                        pierres.get(i).getCardSprite()
                ));
                i++;
            }
        }

        return bufferedComponents;

    }

    private void renameDeck(){

        String name = JOptionPane.showInputDialog(null, "Le nom du Deck");
        if (name == null || name.isBlank() || name.length()>20){
            msgLength.show(1);
            return;
        }

        deck.setName(name);
        nomDeck.setText(name);

    }

    private void saveDeck() throws IOException {

         if (deck.getPierres().isEmpty())
             msgVide.show(1);
         else if (deck.getName() == null || deck.getName().isBlank())
             msgUnnamed.show(1);
         else{

             if (!editing) {
                 DeckManager.addDeck(deck);
                 this.deck = new Deck();
                 setComponents(drawDeck());
             }
             else{
                 // On recopie de l'autre sens (pas par référence cette fois !!)
                 this.deckToModify.setPierres(deck.getPierres());
                 this.deckToModify.setName(deck.getName());
             }

             msgSaved.show(1);

         }

    }

    public void editDeck(Deck d) throws IOException {

        if (d == null){
            this.editing = false;
            nomDeck.setText("");
            this.deck = new Deck();
        }
        else{

            this.editing = true;

            // On retient le Deck à modifier par référence
            this.deckToModify = d;

            // Clonage du Deck à modifier
            this.deck = new Deck();
            this.deck.setPierres(d.getPierres());
            this.deck.setName(d.getName());

            // Affichage du nom du Deck
            nomDeck.setText(d.getName());

        }

        setComponents(drawDeck());

    }

    @Override
    public void drawBG(Graphics2D g2d){
        g2d.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
    }

}
