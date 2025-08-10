package Game.Decks;

import Game.Pierres.Pierre;
import Game.Pierres.PierreGEN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deck {

    private static int MAX_PIERRES = 15;
    private static int MAX_NAME_LENGTH = 20;

    private String name;
    private List<Pierre> pierres;

    ///  CONSTRUCTEURS
    public Deck(){
        pierres = new ArrayList<Pierre>();
    }
    public Deck(List<Pierre> pierres){
        this.pierres = pierres;
    }
    public Deck(String code){
        // import
    }

    ///  GETTERS
    public String getName(){return this.name;}
    public List<Pierre> getPierres(){return this.pierres;}
    public int getMaxLevel() {
        if (pierres.isEmpty())
            return 0;

        int max = 1;
        for (Pierre pierre : pierres)
            if (pierre.getLevel() > max)
                max = pierre.getLevel();
        return max;
    }

    public String exportDeck(){
        if (pierres.isEmpty())
            return null;
        if (name == null || name.isBlank())
            return null;

        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (Pierre pierre : pierres){
            sb.append(",");
            sb.append(pierre.getID());
        }

        return sb.toString();
    }


    ///  SETTERS
    // Renvoie Boolean succès
    public boolean setName(String name){
        if (name.length() > MAX_NAME_LENGTH)
            return false;
        this.name = name;
        return true;
    }
    public boolean setPierres(List<Pierre> pierres){
        if (pierres.size() <= MAX_PIERRES) {
            this.pierres = pierres;
            return true;
        }
        return false;
    }
    public boolean addPierre(Pierre pierre){
        if (this.pierres.size() < MAX_PIERRES) {
            this.pierres.add(pierre);
            return true;
        }
        return false;
    }
    public boolean removePierre(Pierre pierre){
        // Pierre donnée
        if (this.pierres.contains(pierre)){
            this.pierres.remove(pierre);
            return true;
        }
        return false;
    }
    public boolean removePierre(int index){
        // Index donné
        if (index < 0 || index >= getPierres().size())
            return false;
        this.pierres.remove(index);
        return true;
    }

    public boolean importDeck(String code) throws IOException {

        String regex = "^([^,]+)(?:,([0-9]+))+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);

        if (!matcher.matches())
            return false;


        String name = matcher.group(1);

        ArrayList<Pierre> pierres = new ArrayList<>();
        String[] parts = code.split(",", -1); // découper la chaîne
        for (int i = 1; i < parts.length; i++) { // i = 1 pour ignorer le premier bloc (nom)
            int ID = Integer.parseInt(parts[i]);
            pierres.add(PierreGEN.genererPierre(ID, 0));
        }

        this.name = name;
        this.pierres = pierres;
        return true;

    }


}
