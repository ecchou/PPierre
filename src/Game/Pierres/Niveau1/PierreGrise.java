package Game.Pierres.Niveau1;

import Game.Pierres.Pierre;

public class PierreGrise extends Pierre {

    private final static int ID = 1;

    private final static int NIVEAU = 1;
    private final static double PRIX_INITIAL = 1;
    private final static String NOM = "Pierre Grise";
    private final static String SPRITE = "img/game/pierres/grise.png";

    public PierreGrise(int joueur){
        super(joueur, PRIX_INITIAL, false);
    }

    public int getID(){return ID;}

    public int getNiveau(){return NIVEAU;}
    public double getPrixInitial(){return PRIX_INITIAL;}
    public String getNom(){return NOM;}
    public String getCheminImage(){return SPRITE;}

}
