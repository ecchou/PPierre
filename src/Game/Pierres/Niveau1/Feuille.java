package Game.Pierres.Niveau1;

import Game.Pierres.Pierre;

import java.io.IOException;

public class Feuille extends Pierre {

    private final static int ID = 5;

    private final static int LEVEL = 1;
    private final static double INITIAL_PRICE = 5;
    private final static String NAME = "Feuille";
    private final static String SPRITE = "img/game/pierres/feuille.png";

    public Feuille(int joueur) throws IOException {
        super(joueur, INITIAL_PRICE);
    }

    ///  GETTERS
    public int getID(){return ID;}
    public int getLevel(){return LEVEL;}
    public double getInitialPrice(){return INITIAL_PRICE;}
    public String getName(){return NAME;}
    public String getImagePath(){return SPRITE;}

}
