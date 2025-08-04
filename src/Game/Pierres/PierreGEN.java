package Game.Pierres;

import Game.Pierres.Niveau1.*;

import java.io.IOException;

public class PierreGEN {

    public Pierre genererPierre(int ID, int joueur) throws IOException {

        return switch (ID) {
            case 1 -> new PierreGrise(joueur);
            case 2 -> new Prisme(joueur);
            default -> null;
        };

    }

}
