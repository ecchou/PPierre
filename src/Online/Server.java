package Online;// === Fichier : Server.java ===
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Serveur lancé sur le port 5000...");

        while (clients.size() < 2) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(clientSocket, clients.size());
            clients.add(handler);
            new Thread(handler).start();
        }
    }

    public static void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public static void sendToOther(int senderId, String message) {
        for (ClientHandler client : clients) {
            if (client.getId() != senderId) {
                client.sendMessage(message);
            }
        }
    }
}

