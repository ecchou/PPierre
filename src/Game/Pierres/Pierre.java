package Game.Pierres;

public abstract class Pierre {

    //private final static double PRIX_INITIAL;

    private int joueur;
    private double prix;
    private final boolean invulnerable;
    private boolean gele;

    public Pierre(int joueur, double prix, boolean invulnerable){
        this.joueur = joueur;
        this.prix = prix;
        this.invulnerable = invulnerable;
    }

    public abstract int getID();

    public abstract int getNiveau();
    public abstract double getPrixInitial();
    public abstract String getNom();
    public abstract String getCheminImage();

    public int getJoueur(){
        return joueur;
    }

    public double getPrix(){
        return prix;
    }

    public boolean estInvulnerable(){
        return invulnerable;
    }

    public boolean estGele(){
        return gele;
    }

    public boolean setGel(boolean gel){

        if (estInvulnerable())
            return false;

        this.gele = gel;
        return true;
    }

    public void setPrix(double prix){
        this.prix = prix;
    }

}
