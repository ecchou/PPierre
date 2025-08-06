package Game.Pierres.Niveau2;

import Game.Pierres.Pierre;

import java.io.IOException;

public class Bouclier extends Pierre {

    private final static int ID = 9;

    private final static int LEVEL = 2;
    private final static double INITIAL_PRICE = 10;
    private final static String NAME = "Bouclier";
    private final static String SPRITE = "img/game/pierres/grise.png";
    private final static String DESCRIPTION =
            "Immunisé à tous les effets offensifs (Gel, Feu, Zap, Explosion). " +
            "Donne également ce pouvoir aux 4 pierres autour (en forme de +).";

    public Bouclier(int joueur) throws IOException {
        super(joueur, INITIAL_PRICE);
    }

    ///  GETTERS
    public int getID(){return ID;}
    public int getLevel(){return LEVEL;}
    public double getInitialPrice(){return INITIAL_PRICE;}
    public String getName(){return NAME;}
    public String getImagePath(){return SPRITE;}
    public String getDescription(){
        return DESCRIPTION;
    }

}
