package Game;

import Game.Decks.Deck;
import Game.Pierres.Pierre;

import java.util.List;

public class Player {

    private Deck deck;
    private String pseudo;

    private List<Pierre> main;
    private List<Pierre> discard;

    public Player(String pseudo){
        this.pseudo = pseudo;
    }

    ///  SETTERS
    public void setDeck(Deck deck){this.deck = deck;}

    ///  GETTERS
    public Deck getDeck(){return this.deck;}
    public String getPseudo(){return this.pseudo;}
    public List<Pierre> getMain(){return this.main;}
    public List<Pierre> getDiscard(){return this.discard;}

}
