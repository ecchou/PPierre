package Game.Pierres;

import Game.Pierres.Niveau1.*;

public class PierreGEN {

    public Pierre genererPierre(int ID, int joueur){

        return switch (ID) {
            case 1 -> new PierreGrise(joueur);
            case 2 -> new Prisme(joueur);
            default -> null;
        };

    }

}
