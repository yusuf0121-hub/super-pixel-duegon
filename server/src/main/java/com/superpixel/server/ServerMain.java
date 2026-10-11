package com.superpixel.server;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        int port = 54555;
        GameServer server = new GameServer(port);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop();
            System.out.println("Sunucu kapatıldı.");
        }));

        while (true) {
            Thread.sleep(1000L);
        }
    }
}
