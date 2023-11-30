package main;

import client.ClientTask;

public class MainClient {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ClientTask(i)).start();
        }
    }
}
