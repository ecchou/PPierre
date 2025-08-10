package Game.Decks;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckManager {

    private static final int MAX_COUNT = 100;

    private static int deckCount = 0;
    private static Map<Integer, Deck> decks = new HashMap<>();


    ///  GETTERS
    public static int getDeckCount(){return deckCount;}
    public static Deck getDeck(int id){return decks.get(id);}

    ///  SETTERS
    public static void addDeck(Deck d){
        if (deckCount>=MAX_COUNT)
            return;
        deckCount++;
        decks.put(deckCount, d);
    }

    public static void removeDeck(Integer index){
        if (index < 1 || index > deckCount)
            return;

        decks.remove(index);
        for (int i = index; i < deckCount; i++){
            decks.put(i, decks.get(i+1));
        }

        deckCount--;
    }

    private static void clearDecks(){
        decks.clear();
        deckCount = 0;
    }

    public static boolean saveDecks(){
        if (decks.isEmpty())
            return false;

        StringBuilder sb = new StringBuilder();
        for (Deck d : decks.values()){
            sb.append(d.exportDeck());
            sb.append("\n");
        }
        sb.append(";"); // signifie la fin de la lecture

        return saveFile(sb.toString());
    }

    public static boolean loadDecks() throws IOException {

        String codesRaw = loadFile();
        List<String> codes;
        List<Deck> decks = new ArrayList<>();

        if (codesRaw == null)
            return false;

        codes = List.of(codesRaw.split("\\R"));

        for (String code : codes){

            if (code.startsWith(";"))
                break;

            Deck d = new Deck();
            boolean succes;
            succes = d.importDeck(code);

            if (!succes)
                return false;

            decks.add(d);

        }

        if (decks.isEmpty())
            return false;

        // Si ici, c'est good
        clearDecks();
        for (Deck d : decks){
            addDeck(d);
        }

        return true;

    }

    private static boolean saveFile(String content) {
        // Créer une instance de JFileChooser pour choisir l'emplacement du fichier
        JFileChooser fileChooser = new JFileChooser();

        // Définir l'extension par défaut à "txt", mais l'utilisateur peut modifier ou ne pas mettre d'extension
        fileChooser.setSelectedFile(new File("decks.sav"));

        // Ouvrir la fenêtre de dialogue pour choisir un emplacement de sauvegarde
        int userChoice = fileChooser.showSaveDialog(null);

        // Vérifier si l'utilisateur a appuyé sur "Sauvegarder"
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            // Obtenir le fichier sélectionné
            File selectedFile = fileChooser.getSelectedFile();

            // Sauvegarder le contenu dans le fichier choisi
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(content);
                System.out.println("Sauvegarde réussie dans : " + selectedFile.getAbsolutePath());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de la sauvegarde du fichier.");
                return false;
            }
        } else {
            System.out.println("Sauvegarde annulée.");
            return false;
        }
    }

    private static String loadFile() {
        // Créer une instance de JFileChooser pour choisir un fichier existant
        JFileChooser fileChooser = new JFileChooser();

        // Ouvrir la fenêtre de dialogue pour sélectionner un fichier
        int userChoice = fileChooser.showOpenDialog(null);

        // Vérifier si l'utilisateur a appuyé sur "Ouvrir"
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            // Obtenir le fichier sélectionné
            File selectedFile = fileChooser.getSelectedFile();

            try {
                return Files.readString(selectedFile.toPath());
            } catch (IOException e) {
                return null;
            }


        } else {
            System.out.println("Chargement annulé.");
            return null;  // Si l'utilisateur annule, retourner null
        }
    }

}
