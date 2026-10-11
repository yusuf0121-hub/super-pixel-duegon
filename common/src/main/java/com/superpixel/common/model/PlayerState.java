package com.superpixel.common.model;

public class PlayerState {
    private String name;
    private int x;
    private int y;
    private int gold;
    private boolean protectedSpawn;

    public PlayerState() {
    }

    public PlayerState(String name, int x, int y, int gold, boolean protectedSpawn) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.gold = gold;
        this.protectedSpawn = protectedSpawn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isProtectedSpawn() {
        return protectedSpawn;
    }

    public void setProtectedSpawn(boolean protectedSpawn) {
        this.protectedSpawn = protectedSpawn;
    }
}
