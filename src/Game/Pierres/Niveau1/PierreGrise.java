package Game.Pierres.Niveau1;

import Game.Pierres.Pierre;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PierreGrise extends Pierre {

    private final static int ID = 1;

    private final static int LEVEL = 1;
    private final static double INITIAL_PRICE = 1;
    private final static String NAME = "Pierre Grise";
    private final static String SPRITE = "img/game/pierres/grise.png";

    public PierreGrise(int joueur) throws IOException {
        super(joueur, INITIAL_PRICE);
    }

    ///  GETTERS
    public int getID(){return ID;}
    public int getLevel(){return LEVEL;}
    public double getInitialPrice(){return INITIAL_PRICE;}
    public String getName(){return NAME;}
    public String getImagePath(){return SPRITE;}

    public String getDescription(){
        return "Ceci est une description de Pierre!";
    }

}
