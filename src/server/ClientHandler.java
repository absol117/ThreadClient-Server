package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Server server;
    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream());
            String riga = bufferedReader.readLine();
            String[] s = riga.split(" ");
            System.out.println("SERVER -> Messaggio Ricevuto : " + riga);
            if (s[0].equalsIgnoreCase("Ciao")) {
                printWriter.println("Ciao anche a te client " + Thread.currentThread().getName());
                printWriter.flush();
                System.out.println("SERVER -> Messaggio inviato : Ciao anche a te client " + Thread.currentThread().getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            server.close(socket);
        }
    }
}
