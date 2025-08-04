package Game.Pierres.Niveau1;

import Game.Pierres.Pierre;

public class Prisme extends Pierre {

    private final static int ID = 2;

    private final static int NIVEAU = 1;
    private final static double PRIX_INITIAL = 3;
    private final static String NOM = "Prisme";
    private final static String SPRITE = "img/game/pierres/prisme.png";

    public Prisme(int joueur){
        super(joueur, PRIX_INITIAL, true);
    }

    public int getID(){return ID;}

    public int getNiveau(){return NIVEAU;}
    public double getPrixInitial(){return PRIX_INITIAL;}
    public String getNom(){return NOM;}
    public String getCheminImage(){return SPRITE;}

}
