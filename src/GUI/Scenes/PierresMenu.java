package GUI.Scenes;

import GUI.Components.Buttons.Button;
import GUI.Components.Buttons.GrayableButton;
import GUI.Components.Component;
import GUI.Components.Pictures.ClickablePicture;
import GUI.Components.Pictures.Picture;
import GUI.Components.Texts.MultilineText;
import GUI.Components.Texts.Text;
import Game.Pierres.Pierre;
import Game.Pierres.PierreGEN;

import javax.imageio.ImageIO;
import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PierresMenu extends Scene {

    // Action -4 : Rien, spécifique aux Infos d'une pierre (à droite)
    // Action -3 : Bouton Page Précédente
    // Action -2 : Bouton Page Suivante
    // Action -1 : Retour au Menu Principal
    // Action 0 : Rien
    // Action N : Accéder aux infos de la Pierre ayant N comme ID

    private Image bg = ImageIO.read(new File("img/gui/bg/pierresMenuBG.png"));

    private int page;
    private final int pageMax;
    private GrayableButton buttonPrev;
    private GrayableButton buttonNext;
    private Text pageDisplay = new Text(222, 542, 30, "att", Text.Padding.CENTERED, Color.BLACK);

    final int CARDS_GAP = 35;
    final int CARDS_PER_ROW = 3;
    final int CARDS_PER_COL = 2;
    final int CARDS_X = 35;
    final int CARDS_Y = 105;
    final int CARDS_WIDTH = 100;
    final int CARDS_HEIGHT = 150;

    private Pierre pierreInfo;

    public PierresMenu() throws IOException {

        super(new ArrayList<Component>());

        ///  BOUTONS
        final int CLOSEBUTTON_SIZE = 50;
        int GAP = 20;
        addComponent(new Button(CLOSEBUTTON_SIZE, CLOSEBUTTON_SIZE, WIDTH-GAP-CLOSEBUTTON_SIZE, GAP, -1, null, sprCloseButton, sprCloseButtonHovered));
        buttonPrev = new GrayableButton(150, 75, GAP, HEIGHT-GAP-75, -3, "prev");
        buttonNext = new GrayableButton(150, 75, 445-GAP-150, HEIGHT-GAP-75, -2, "next");
        addComponent(buttonPrev);
        addComponent(buttonNext);

        ///  CARTES
        pageMax = (int) Math.ceil((double) PierreGEN.getMaxID() / (CARDS_PER_ROW*CARDS_PER_COL));
        setComponents(goToPage(1));

        ///  TEXTE
        addComponent(pageDisplay);


        grayButtons(); // griser dynamiquement les boutons prev/next

    }

    @Override public void drawBG(Graphics2D g2d){
        g2d.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
    }

    private void grayButtons(){
        buttonPrev.setGrayed(false);
        buttonNext.setGrayed(false);
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
            else if (action > 0)
                setComponents(infoPierre(action));

            grayButtons();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Component> infoPierre(int ID) throws IOException {

        List<Component> bufferedComponents;

        int pagePierre = (int) Math.ceil((double) ID / (CARDS_PER_COL*CARDS_PER_ROW));
        if (page != pagePierre)
            bufferedComponents = goToPage(pagePierre);
        else
            bufferedComponents = new ArrayList<>(getComponents());

        List<Component> toRemove = new ArrayList<>();
        for (Component c : bufferedComponents) {
            if (c.getAction() == -4)
                toRemove.add(c);
        }
        for (Component c : toRemove) {
            bufferedComponents.remove(c);
        }

        pierreInfo = PierreGEN.genererPierre(ID, 0);
        bufferedComponents.add(new Picture(455+70, 100, 175, 175, -4, pierreInfo.getSprite()));
        bufferedComponents.add(new MultilineText(
                455,
                300,
                16,
                pierreInfo.getDescription(),
                Text.Padding.LEFT,
                Color.BLACK,
                315,
                5,
                -4
        ));

        return bufferedComponents;

    }

    public List<Component> goToPage(int n) throws IOException {
        this.page = n;
        pageDisplay.setText(page + "/" + pageMax);

        List<Component> bufferedComponents = new ArrayList<>(getComponents());
        List<Component> toRemove = new ArrayList<>();
        for (Component c : bufferedComponents) {
            if (c.getAction() > 0)
                toRemove.add(c);
        }
        for (Component c : toRemove) {
            bufferedComponents.remove(c);
        }

        for (int i = 0; i < CARDS_PER_ROW*CARDS_PER_COL; i++){    // big mathématicien mdr
            if ((i+1)+(CARDS_PER_COL*CARDS_PER_ROW*(page-1)) > PierreGEN.getMaxID())
                break;
            bufferedComponents.add(new ClickablePicture(
                    CARDS_X+i*(CARDS_WIDTH+CARDS_GAP)-(i/CARDS_PER_ROW)*CARDS_PER_ROW*(CARDS_WIDTH+CARDS_GAP),
                    CARDS_Y+(i/CARDS_PER_ROW)*(CARDS_HEIGHT+CARDS_GAP),
                    CARDS_WIDTH,
                    CARDS_HEIGHT,
                    (i+1)+(CARDS_PER_COL*CARDS_PER_ROW*(page-1)),
                    PierreGEN.genererPierre((i+1)+(CARDS_PER_COL*CARDS_PER_ROW*(page-1)), 0).getCardSprite())
            );
        }

        return bufferedComponents;

    }

}
