package Online;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Thread de lecture
        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Serveur : " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Entrée utilisateur
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            out.println(input);
        }
    }
}