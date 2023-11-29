package main;

import client.Client;

public class MainClient {
    public static void main(String[] args) {

        Client client = new Client();
        client.connectToServer("192.168.1.108",8080);
        client.write("Ciao");

        String s = client.readLine();
        System.out.println(s);

        if (s.equalsIgnoreCase("ciao anche a te Client")) {
            client.write("bella bro");
        }

        client.close();

    }
}
