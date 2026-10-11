package com.superpixel.client;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        String playerName = (args.length > 0) ? args[0] : "YeniOyuncu";
        GameClient client = new GameClient(playerName);

        try {
            client.connect("localhost", 54555);

            Scanner scanner = new Scanner(System.in);
            System.out.println("İstemci hazır. Mesaj gönderin. 'quit' ile çık.");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }

                if (line.startsWith("tile:")) {
                    String[] parts = line.split(":");
                    if (parts.length >= 4) {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        client.sendTileChange(x, y, parts[3]);
                    }
                } else {
                    client.sendChat(line);
                }
            }
        } finally {
            client.close();
        }
    }
}
