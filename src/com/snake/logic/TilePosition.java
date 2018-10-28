package com.snake.logic;

public class TilePosition {
    public int x, y;

    public TilePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() { return "Tile[" + x + ", " + y + "]\n"; }
}
