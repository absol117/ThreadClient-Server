package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTask implements Runnable{

    private int id;
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ClientTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket("localhost",8080);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(),true);

            String mexInv = "Ciao , sono l'utente numero: " + id;
            printWriter.println(mexInv);
            System.out.println("CLIENT -> Messaggio inviato: " + mexInv);

            String mexRic = bufferedReader.readLine();
            System.out.println("CLIENT -> Messaggio ricevuto " + mexRic);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

