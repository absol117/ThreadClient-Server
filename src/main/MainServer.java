package main;

import server.ClientHandler;
import server.Server;

import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(8080);




        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                    if (server.isIdle()) {
                        System.out.println("SERVER: Nessuna nuova connessione per 10 secondi.");
                        server.closeServer();
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        timerThread.start();



        while (!server.isClosed()) {
            Socket socket = server.startConnection();
            if (socket != null) {
                Thread clientHandlerThread = new Thread(new ClientHandler(socket, server));
                clientHandlerThread.start();
            }
        }



    }
}
