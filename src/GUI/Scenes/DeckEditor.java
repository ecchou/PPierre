package GUI.Scenes;

import GUI.Components.Pictures.ClickablePicture;
import Game.Decks.Deck;
import Game.Pierres.PierreGEN;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DeckEditor extends Scene {

    private Image bg = ImageIO.read(new File("img/gui/bg/deckEditorBG.png"));

    private static int DECKBOX_X = 50;
    private static int DECKBOX_Y = 50;
    private static int DECKBOX_WIDTH = 300;
    private static int DECKBOX_HEIGHT = 500;

    private int page;

    private Deck deck;

    public DeckEditor() throws IOException {

        super(new ArrayList<>());

        this.deck = new Deck();

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
                            DECKBOX_Y+(row*(GAP2+CARD_HEIGHT)),
                            CARD_WIDTH,
                            CARD_HEIGHT,
                            cardIndex,
                            PierreGEN.genererPierre(cardIndex, 0).getCardSprite()
                    ));
                    cardIndex++;
                }
            }
        }



    }

    @Override
    public void drawBG(Graphics2D g2d){
        System.out.println("Eh oh");
        g2d.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
    }

}
