package com.superpixel.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryo.Kryo;
import com.superpixel.common.model.PlayerState;
import com.superpixel.common.network.ChatPacket;
import com.superpixel.common.network.LoginPacket;
import com.superpixel.common.network.TileChangePacket;
import com.superpixel.common.network.WorldUpdatePacket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServer {
    private final Server server;
    private final Map<String, PlayerState> players = new HashMap<>();
    private final int port;

    public GameServer(int port) {
        this.port = port;
        this.server = new Server();
        registerPackets();
        configureListener();
    }

    private void registerPackets() {
        Kryo kryo = server.getKryo();
        kryo.register(LoginPacket.class);
        kryo.register(ChatPacket.class);
        kryo.register(TileChangePacket.class);
        kryo.register(WorldUpdatePacket.class);
        kryo.register(PlayerState.class);
        kryo.register(ArrayList.class);
    }

    private void configureListener() {
        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                System.out.println("Yeni bağlantı: " + connection.getID());
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof LoginPacket packet) {
                    handleLogin(connection, packet);
                } else if (object instanceof ChatPacket packet) {
                    handleChat(packet);
                } else if (object instanceof TileChangePacket packet) {
                    handleTileChange(packet);
                }
            }

            @Override
            public void disconnected(Connection connection) {
                players.entrySet().removeIf(entry -> entry.getValue() == null);
                System.out.println("Bağlantı kesildi: " + connection.getID());
                broadcastWorldState();
            }
        });
    }

    private void handleLogin(Connection connection, LoginPacket packet) {
        String name = packet.getPlayerName() == null || packet.getPlayerName().isBlank()
                ? "Player-" + connection.getID()
                : packet.getPlayerName();

        PlayerState state = new PlayerState(name, 2, 2, 0, true);
        players.put(name, state);

        System.out.println("Oyunucudan giriş: " + name);
        broadcastWorldState();
    }

    private void handleChat(ChatPacket packet) {
        System.out.println("[Chat] " + packet.getSender() + ": " + packet.getMessage());
        server.sendToAllTCP(packet);
    }

    private void handleTileChange(TileChangePacket packet) {
        System.out.println("Tile değişti -> x=" + packet.getX() + ", y=" + packet.getY() + ", tile=" + packet.getTileType());
        server.sendToAllTCP(packet);
    }

    public void start() throws Exception {
        server.start();
        server.bind(port);
        System.out.println("Sunucu başlatıldı. Port: " + port);
        broadcastWorldState();
    }

    public void stop() {
        server.stop();
    }

    private void broadcastWorldState() {
        List<PlayerState> snapshot = new ArrayList<>(players.values());
        WorldUpdatePacket packet = new WorldUpdatePacket("Dünya güncellendi", snapshot);
        server.sendToAllTCP(packet);
    }
}
