package Game.Pierres.Niveau2;

import Game.Pierres.Pierre;

import java.io.IOException;

public class Bombe extends Pierre {

    private final static int ID = 8;

    private final static int LEVEL = 2;
    private final static double INITIAL_PRICE = 10;
    private final static String NAME = "Bombe";
    private final static String SPRITE = "img/game/pierres/zap.png";

    public Bombe(int joueur) throws IOException {
        super(joueur, INITIAL_PRICE);
    }

    ///  GETTERS
    public int getID(){return ID;}
    public int getLevel(){return LEVEL;}
    public double getInitialPrice(){return INITIAL_PRICE;}
    public String getName(){return NAME;}
    public String getImagePath(){return SPRITE;}

}
