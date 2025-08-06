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
    public Integer getMaxLevel(){
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

        StringBuilder sb = new StringBuilder();
        for (Pierre pierre : pierres){
            sb.append(pierre.getID());
            sb.append(",");
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
        if (this.pierres.contains(pierre)){
            this.pierres.remove(pierre);
            return true;
        }
        return false;
    }

    public boolean importDeck(String code) throws IOException {

        String regex = "^[0-9]+(,[0-9]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);

        if (!matcher.matches())
            return false;

        List<String> strPierres = List.of(code.split(","));
        List<Pierre> pierres = new ArrayList<>();

        for (String strPierre : strPierres) {
            int ID = Integer.parseInt(strPierre);
            pierres.add(PierreGEN.genererPierre(ID, 0));
        }

        this.pierres = pierres;
        return true;

    }


}
