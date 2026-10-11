package com.superpixel.common.network;

public class LoginPacket {
    private String playerName;

    public LoginPacket() {
    }

    public LoginPacket(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
