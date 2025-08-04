package Game.Pierres.Niveau1;

import Game.Pierres.Pierre;

import java.io.IOException;

public class Prisme extends Pierre {

    private final static int ID = 2;

    private final static int LEVEL = 1;
    private final static double INITIAL_PRICE = 3;
    private final static String NAME = "Prisme";
    private final static String SPRITE = "img/game/pierres/prisme.png";

    public Prisme(int joueur) throws IOException {
        super(joueur, INITIAL_PRICE);
    }

    public int getID(){return ID;}
    public int getLevel(){return LEVEL;}
    public double getInitialPrice(){return INITIAL_PRICE;}
    public String getName(){return NAME;}
    public String getImagePath(){return SPRITE;}

    ///  GAEMEPLAY

    // Immunisé au gel
    @Override
    public boolean freeze(){
        return false;
    }

}
