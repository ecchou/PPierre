package GUI.Scenes;

import GUI.Components.Buttons.GrayableButton;
import GUI.Components.Component;
import GUI.Components.Buttons.Button;
import GUI.Components.Texts.Text;
import Online.ClientHandler;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Online.Server;

public class ClientTest extends Scene {

    // Action 0 : Rien
    // Action 1 : Rejoindre
    // Action 2 : Envoyer msg

    private final Color bgGradient1 = new Color(107, 98, 255);
    private final Color bgGradient2 = new Color(45, 0, 255);

    private final GrayableButton btnSend;
    private final Text statusText;

    private BufferedReader in;
    private PrintWriter out;
    private ServerSocket chaussette;

    public ClientTest() throws IOException {

        super(new ArrayList<Component>());

        ///  BOUTONS
        addComponent(new Button(150, 100, 10, 10, 1, "rejoindre"));
        btnSend = new GrayableButton(150, 100, 10, 10+100+10, 2, "envoyer");
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

        // rejoindre de partie
        if (action == 1){

            if (out != null) {
                System.out.println("Une partie est déjà en cours !");
                return;
            }

            new Thread(() -> {
                try {
                    // 1️⃣ Connexion au serveur (ici localhost)
                    Socket socket = new Socket("localhost", 5000);

                    // 2️⃣ Préparer lecture / écriture
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);

                    // 3️⃣ Thread pour écouter les messages du serveur
                    new Thread(() -> {
                        try {
                            String line;
                            while ((line = in.readLine()) != null) {
                                System.out.println("Serveur : " + line);
                                statusText.setText(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                    System.out.println("Connecté au serveur !");
                    statusText.setText("Connecté !");
                    btnSend.setGrayed(false);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        // envoi de message
        if (action == 2) {
            out.println("coucou jsuis le rejoigneur");
        }

    }

}
