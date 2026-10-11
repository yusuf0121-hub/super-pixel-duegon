package com.superpixel.common.network;

import com.superpixel.common.model.PlayerState;
import java.util.ArrayList;
import java.util.List;

public class WorldUpdatePacket {
    private String message;
    private List<PlayerState> players = new ArrayList<>();

    public WorldUpdatePacket() {
    }

    public WorldUpdatePacket(String message, List<PlayerState> players) {
        this.message = message;
        this.players = players;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PlayerState> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerState> players) {
        this.players = players;
    }
}
