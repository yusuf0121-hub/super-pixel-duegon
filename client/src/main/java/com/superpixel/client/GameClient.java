package com.superpixel.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.KryoSerialization;
import com.esotericsoftware.kryo.Kryo;
import com.superpixel.common.model.PlayerState;
import com.superpixel.common.network.ChatPacket;
import com.superpixel.common.network.LoginPacket;
import com.superpixel.common.network.TileChangePacket;
import com.superpixel.common.network.WorldUpdatePacket;
import java.io.IOException;
import java.util.ArrayList;

public class GameClient {
    private final Client client;
    private final String playerName;

    public GameClient(String playerName) {
        this.playerName = playerName;
        this.client = new Client();
        registerPackets();
        configureListener();
    }

    private void registerPackets() {
        Kryo kryo = client.getKryo();
        kryo.register(LoginPacket.class);
        kryo.register(ChatPacket.class);
        kryo.register(TileChangePacket.class);
        kryo.register(WorldUpdatePacket.class);
        kryo.register(PlayerState.class);
        kryo.register(ArrayList.class);
    }

    private void configureListener() {
        client.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                System.out.println("Sunucuya bağlandı: " + connection.getRemoteAddressTCP());
                sendLogin();
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof WorldUpdatePacket packet) {
                    System.out.println("[World Update] " + packet.getMessage());
                    for (PlayerState player : packet.getPlayers()) {
                        System.out.println("- " + player.getName() + " @ (" + player.getX() + ", " + player.getY() + ") gold=" + player.getGold());
                    }
                } else if (object instanceof ChatPacket packet) {
                    System.out.println("[Chat] " + packet.getSender() + ": " + packet.getMessage());
                } else if (object instanceof TileChangePacket packet) {
                    System.out.println("[Tile Change] x=" + packet.getX() + ", y=" + packet.getY() + ", type=" + packet.getTileType());
                }
            }
        });
    }

    public void connect(String host, int port) throws IOException {
        client.start();
        client.connect(5000, host, port);
    }

    public void sendLogin() {
        client.sendTCP(new LoginPacket(playerName));
    }

    public void sendChat(String message) {
        client.sendTCP(new ChatPacket(playerName, message));
    }

    public void sendTileChange(int x, int y, String tileType) {
        client.sendTCP(new TileChangePacket(x, y, tileType));
    }

    public void close() {
        client.stop();
    }
}
