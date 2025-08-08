package GUI.Scenes;

import GUI.Components.Buttons.Button;
import GUI.Components.Buttons.GrayableButton;
import GUI.Components.Buttons.SelectorButton;
import GUI.Components.Component;
import GUI.Components.Texts.Text;
import Game.Decks.DeckManager;

import javax.imageio.ImageIO;
import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class DeckSelector extends Scene {

    private static final int SB_WIDTH = 500;
    private static final int SB_HEIGHT = 30;
    private static final Point DECK_LIST_START = new Point(150, 100);
    private static final int DECKS_PER_PAGE = 5;

    private Image bg = ImageIO.read(new File("img/gui/bg/deckSelectMenu.png"));

    private int page;
    private int pageMax;
    private GrayableButton buttonPrev;
    private GrayableButton buttonNext;
    private Text pageDisplay = new Text(400, 498, 30, "att", Text.Padding.CENTERED, Color.BLACK);

    private int selectedDeck = 0;

    public DeckSelector() throws IOException {

        super(new ArrayList<Component>());

        pageMax = (int) Math.ceil((double) DeckManager.getDeckCount() / DECKS_PER_PAGE);

        ///  BUTTONS
        final int CLOSEBUTTON_SIZE = 50;
        int GAP = 20;
        addComponent(new Button(CLOSEBUTTON_SIZE, CLOSEBUTTON_SIZE, WIDTH-GAP-CLOSEBUTTON_SIZE, GAP, -1, null, sprCloseButton, sprCloseButtonHovered));
        buttonPrev = new GrayableButton(100, 75, 150, 460, -3, "prev");
        buttonNext = new GrayableButton(100, 75, 650-100, 460, -2, "next");
        addComponent(buttonPrev);
        addComponent(buttonNext);

        ///  TEXT
        addComponent(pageDisplay);

        goToPage(1);
        grayButtons();

    }

    // cette fonction update également tout au cas où y'a un nouveau deck
    private List<Component> goToPage(int page) throws IOException {
        this.page = page;
        this.pageMax = (int) Math.ceil((double) DeckManager.getDeckCount() / DECKS_PER_PAGE);
        if (DeckManager.getDeckCount() == 0)
            this.pageMax = 1;
        this.pageDisplay.setText(page + "/" +  pageMax);

        List<Component> bufferedComponents = getComponents();
        List<Component> toRemove = new ArrayList<>();

        for (Component c : bufferedComponents){
            if (c.getAction() > 0)
                toRemove.add(c);
        }
        for (Component c : toRemove){
            bufferedComponents.remove(c);
        }

        for (int i = 0; i<DECKS_PER_PAGE; i++){
            int indexDeck = i+((page-1)*DECKS_PER_PAGE)+1;
            if (indexDeck > DeckManager.getDeckCount())
                break;

            bufferedComponents.add(new SelectorButton(
                    DECK_LIST_START.x,
                    DECK_LIST_START.y+i*SB_HEIGHT,
                    SB_WIDTH,
                    SB_HEIGHT,
                    indexDeck,
                    Integer.toString(indexDeck),
                    DeckManager.getDeck(indexDeck).getName(),
                    Integer.toString(DeckManager.getDeck(indexDeck).getMaxLevel())
            ));
        }

        return bufferedComponents;

    }

    private void grayButtons(){

        if (page == 1)
            buttonPrev.setGrayed(true);
        if (page == pageMax)
            buttonNext.setGrayed(true);

    }

    @Override
    public void handleAction(int action) {
        try {

            if (action == -3)
                setComponents(goToPage(page-1));
            else if (action == -2)
                setComponents(goToPage(page+1));

            grayButtons();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (action > 0){
            selectedDeck = action;
        }

    }

    @Override public void drawBG(Graphics2D g2d){
        g2d.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
    }

    public int getSelected(){
        return selectedDeck;
    }

    @Override
    public void switchedScene() throws IOException {
        setComponents(goToPage(1));
    }
}
