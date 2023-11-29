package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("SERVER: Connessione Stabilita");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        try {
            System.out.print("SERVER: Messaggio ricevuto:  ");
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String message) {
        out.println(message);
        System.out.println("SERVER: Messaggio inviato");
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("SERVER: Connessione Terminata");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

