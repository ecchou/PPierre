package Game.Pierres;

import Game.Pierres.Niveau1.*;
import Game.Pierres.Niveau2.*;

import java.io.IOException;
import java.util.Set;

public class PierreGEN {

    private final static int MAX_ID = 8;

    public static Integer getMaxID(){
        return MAX_ID;
    }

    public static Pierre genererPierre(int ID, int joueur) throws IOException {

        return switch (ID) {
            case 1 -> new PierreGrise(joueur);
            case 2 -> new Prisme(joueur);
            case 3 -> new Torche(joueur);
            case 4 -> new Glace(joueur);
            case 5 -> new Feuille(joueur);
            case 6 -> new Zap(joueur);
            case 7 -> new Copieur(joueur);
            case 8 -> new Bombe(joueur);
            default -> null;
        };

    }

}
