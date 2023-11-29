package main;

import server.Server;

public class MainServer {
    public static void main(String[] args) {

        Server server = new Server();
        server.startServer(8080);
        String read = server.read();
        System.out.println(read);

        if (read.equalsIgnoreCase("Ciao")) {
            server.write("ciao anche a te Client");
        }

        String read1 = server.read();
        System.out.println(read1);

        server.close();
    }
}
