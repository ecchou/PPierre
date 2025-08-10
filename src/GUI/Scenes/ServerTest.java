package GUI.Scenes;

import GUI.Components.Buttons.GrayableButton;
import GUI.Components.Component;
import GUI.Components.Buttons.Button;
import GUI.Components.Texts.Text;
import Online.ClientHandler;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Online.Server;

public class ServerTest extends Scene {

    // Action 0 : Rien
    // Action 1 : Creer
    // Action 2 : Envoyer msg
    // Action 3 : fermer partie

    private final Color bgGradient1 = new Color(50, 160, 160);
    private final Color bgGradient2 = new Color(0, 200, 200);

    private final GrayableButton btnSend;
    private final GrayableButton btnEnd;
    private final Text statusText;

    private PrintWriter out;
    private ServerSocket chaussette;

    public ServerTest() throws IOException {

        super(new ArrayList<Component>());

        ///  BOUTONS
        addComponent(new Button(300, 200, 10, 10, 1, "creer"));
        btnSend = new GrayableButton(300, 200, 10, 10+200+10, 2, "envoyer");
        btnEnd = new GrayableButton(300, 200, 10, (10+200)*2+10, 3, "fermer");
        addComponent(btnSend);
        btnSend.setGrayed(true);

        ///  TXT
        statusText = new Text(400, (10+200)*2+10, 25, "Pas de partie", Text.Padding.CENTERED, Color.BLACK);
        addComponent(statusText);


    }

    @Override
    public void drawBG(Graphics2D g2d){

        GradientPaint gradient = new GradientPaint(0, 0, bgGradient1, 0, HEIGHT, bgGradient2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

    }

    @Override
    public void handleAction(int action){

        // création de partie
        if (action == 1){

            if (chaussette != null && !chaussette.isClosed()) {
                System.out.println("Une partie est déjà en cours !");
                return;
            }

            new Thread(() -> {
                try {
                    chaussette = new ServerSocket(5000);
                    statusText.setText("serv lancé!");
                    System.out.println("Serveur en attente de joueurs...");
                    btnEnd.setGrayed(false);

                    while (!chaussette.isClosed()) {
                        Socket clientSocket = chaussette.accept();

                        // un joueur vient de se co
                        statusText.setText("Qqn est co");
                        btnSend.setGrayed(false);

                        ClientHandler handler = new ClientHandler(clientSocket, Server.clients.size());
                        Server.clients.add(handler);
                        new Thread(handler).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        // envoi de message
        if (action == 2){
            out.println("MOVE 5 5");
        }

    }

}
