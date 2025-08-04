package Online;// === Fichier : Server.java ===
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();

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

class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int id;

    public ClientHandler(Socket socket, int id) throws IOException {
        this.socket = socket;
        this.id = id;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        out.println("OK JOIN");
    }

    public int getId() {
        return id;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void run() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client " + id + " : " + msg);
                if (msg.startsWith("MOVE")) {
                    Server.sendToOther(id, msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Client déconnecté.");
        }
    }
}