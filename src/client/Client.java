package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public void connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("CLIENT: Connessione Stabilita");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        try {
            System.out.print("CLIENT: Messaggio  ");
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String message) {
        out.println(message);
        System.out.println("CLIENT: Messaggio inviato: " + message);
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("CLIENT: Connessione Terminata");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
