package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket accept;
    private int port;
    private boolean isClosed = false;


    private long lastConnectionTime;

    public Server(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket startConnection() {
        try {
            this.accept = serverSocket.accept();
            System.out.println("Connessione Stabilita");
            this.lastConnectionTime = System.currentTimeMillis();
            return accept;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isIdle() {
        return System.currentTimeMillis() - lastConnectionTime > 10000;
    }

    public void close(Socket accept) {
        try {
            accept.close();
            System.out.println("Connessione Terminata");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeServer() {
        try {
            serverSocket.close();
            isClosed = true;
            System.out.println("Server chiuso");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isClosed() {
        return isClosed;
    }
}

