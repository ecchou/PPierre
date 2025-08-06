package GUI.Components.Texts;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultilineText extends Text {
    private int maxWidth;
    private int lineSpacing;

    private static final String COLOR_PATTERN = "<<color=(#[0-9a-fA-F]{6}|[a-zA-Z]+)>>"; // Exemple de balise de couleur

    // Constructeur
    public MultilineText(int x, int y, int fontSize, String text, Padding padding, Color color, int maxWidth, int lineSpacing, int action) {
        super(x, y, fontSize, text, padding, color, action);
        this.maxWidth = maxWidth;
        this.lineSpacing = lineSpacing;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (g2d.getFont() != getFont())
            g2d.setFont(getFont());
        g2d.setColor(getColor());

        FontMetrics metrics = g2d.getFontMetrics(getFont());
        List<String> lines = breakTextIntoLines(g2d, getText(), metrics, maxWidth);

        int drawY = getY();
        for (String line : lines) {
            int drawX = calculateX(g2d, line, metrics);
            drawStyledText(g2d, line, drawX, drawY); // Dessiner le texte avec formatage
            drawY += metrics.getHeight() + lineSpacing; // Espacement entre les lignes
        }
    }

    // Découpe le texte en plusieurs lignes sans supprimer les balises
    private List<String> breakTextIntoLines(Graphics2D g2d, String text, FontMetrics metrics, int maxWidth) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        // On découpe le texte en mots en tenant compte des balises
        String[] words = text.split(" ");  // Séparer le texte en mots

        // On parcourt chaque mot et on calcule sa largeur
        for (String word : words) {
            // Si le mot dépasse la largeur max, on l'ajoute à une nouvelle ligne
            if (metrics.stringWidth(currentLine.toString() + " " + word) > maxWidth && currentLine.length() > 0) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);  // Nouveau mot sur une nouvelle ligne
            } else {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            }
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    // Dessiner du texte en prenant en compte le formatage (comme la couleur)
    private void drawStyledText(Graphics2D g2d, String text, int x, int y) {
        int lastIndex = 0;
        Color currentColor = getColor(); // La couleur par défaut

        Matcher matcher = Pattern.compile(COLOR_PATTERN).matcher(text);

        // Parcourir chaque balise pour appliquer la couleur correspondante
        while (matcher.find()) {
            // Partie avant la balise : dessin avec la couleur actuelle
            String beforeTag = text.substring(lastIndex, matcher.start());
            if (!beforeTag.isEmpty()) {
                g2d.setColor(currentColor); // Appliquer la couleur actuelle
                g2d.drawString(beforeTag, x, y);
                x += g2d.getFontMetrics().stringWidth(beforeTag); // Décalage en fonction du texte dessiné
            }

            // Partie de la balise : changer de couleur
            String colorTag = matcher.group(); // Exemple : <<color=red>> ou <<color=#ff0000>>
            currentColor = getColorForTag(colorTag); // Mettre à jour la couleur en fonction de la balise

            lastIndex = matcher.end(); // Mise à jour de l'index pour les prochaines étapes
        }

        // Dessiner le texte restant après la dernière balise
        String remainingText = text.substring(lastIndex);
        if (!remainingText.isEmpty()) {
            g2d.setColor(currentColor); // Appliquer la couleur actuelle
            g2d.drawString(remainingText, x, y);
        }
    }

    // Obtenir la couleur correspondant à la balise
    private Color getColorForTag(String tag) {
        String colorCode = tag.replaceAll("<<color=|>>", "").trim();

        try {
            // Essayer de créer une couleur à partir d'un code hexadécimal
            if (colorCode.startsWith("#")) {
                return Color.decode(colorCode);
            } else {
                // Sinon, utiliser un nom de couleur standard (en minuscule pour éviter les erreurs de casse)
                return (Color) Color.class.getField(colorCode.toLowerCase()).get(null);
            }
        } catch (Exception e) {
            return Color.BLACK;  // Retourner la couleur par défaut si l'analyse échoue
        }
    }

    // Calcule l'X pour chaque ligne
    private int calculateX(Graphics2D g2d, String line, FontMetrics metrics) {
        int lineWidth = metrics.stringWidth(line);
        int drawX = getX();

        if (getPadding() == Padding.CENTERED) {
            drawX -= lineWidth / 2;
        } else if (getPadding() == Padding.LEFT) {
            drawX = getX();
        } else if (getPadding() == Padding.RIGHT) {
            drawX -= lineWidth;
        }

        return drawX;
    }

    // Getters et setters
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }
}