package com.superpixel.common.network;

public class TileChangePacket {
    private int x;
    private int y;
    private String tileType;

    public TileChangePacket() {
    }

    public TileChangePacket(int x, int y, String tileType) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }
}
