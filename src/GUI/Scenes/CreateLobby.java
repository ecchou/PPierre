package GUI.Scenes;

import GUI.Components.Buttons.Button;
import GUI.Components.Component;
import GUI.Components.Texts.Message;
import GUI.Components.Texts.Text;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateLobby extends Scene {

    // Action -1 : Retour Main Menu
    // Action 0 : Rien
    // Action 1 : Set Username
    // Action 2 : Set Port
    // Action 3 : Lancer Partie


    private Image bg = ImageIO.read(new File("img/gui/bg/lobbyCreateBG.png"));

    private String pseudo;
    private int port;

    private final Text txtPseudo;
    private final Text txtPort;

    private final Message msgPasUnPort;
    private final Message msgPasUnPseudo;
    private final Message msgPseudoInvalide;
    private final Message msgPortInvalide;

    public CreateLobby() throws IOException {

        super(new ArrayList<Component>());

        final int BOX_X = 100;
        final int BOX_Y = 100;
        final int BOX_WIDTH = 600;
        final int BOX_HEIGHT = 400;

        ///  BOUTONS
        final int CLOSEBUTTON_SIZE = 50;
        int GAP = 20;
        addComponent(new Button(CLOSEBUTTON_SIZE, CLOSEBUTTON_SIZE, WIDTH-GAP-CLOSEBUTTON_SIZE, GAP, -1, null, sprCloseButton, sprCloseButtonHovered));
        addComponent(new Button(100, 50, BOX_X+BOX_WIDTH/2-(100+GAP), BOX_Y+50, 1, "Pseudo"));
        addComponent(new Button(100, 50, BOX_X+BOX_WIDTH/2-(100+GAP), BOX_Y+50+(50+GAP), 2, "Port"));

        ///  TEXTE
        txtPseudo = new Text(BOX_X+BOX_WIDTH/2+GAP, BOX_Y+50+50/2, 20, "Pseudo : ", Text.Padding.LEFT, Color.BLACK);
        txtPort = new Text(BOX_X+BOX_WIDTH/2+GAP, BOX_Y+50+(50+GAP)+50/2, 20, "Port : 5000", Text.Padding.LEFT, Color.BLACK);
        addComponent(txtPseudo);
        addComponent(txtPort);

        ///  MESSAGES
        msgPasUnPort = new Message(true, "Port Invalide (Range Valide : 1020-65535)", Color.RED);
        msgPortInvalide = new Message(true, "Port Manquant (Range Valide : 1020-65535)", Color.RED);
        msgPasUnPseudo = new Message(true, "Votre Pseudo doit faire 3-15 caractères.", Color.RED);
        msgPseudoInvalide = new Message(true, "Pseudo Invalide", Color.RED);
        addComponent(msgPasUnPort);
        addComponent(msgPseudoInvalide);
        addComponent(msgPortInvalide);

    }

    @Override
    public void handleAction(int action){

        if (action == 1)
            changeUsername();
        else if (action == 2)
            changePort();

    }

    private void changePort(){

        String strPort = JOptionPane.showInputDialog(null, "Votre Port");
        if (strPort == null || strPort.isBlank() || !strPort.matches("\\d+")){
            msgPasUnPort.show(1);
            return;
        }
        int port =  Integer.parseInt(strPort);
        if (port < 1020 || port > 65535){
            msgPasUnPort.show(1);
            return;
        }

        this.port = port;
        txtPort.setText("Port : " + this.port);

    }

    private void changeUsername(){

        String name = JOptionPane.showInputDialog(null, "Votre Pseudo");
        if (name == null || name.isBlank() || name.length()>20){
            msgPasUnPseudo.show(1);
            return;
        }

        this.pseudo = name;
        txtPseudo.setText("Pseudo : " + this.pseudo);

    }

    @Override
    public void drawBG(Graphics2D g2d){
        g2d.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
    }

}
