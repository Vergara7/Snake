package com.snake.logic;

import java.util.LinkedList;

public class Snake {
    public LinkedList<TilePosition> snakeParts;
    public TilePosition direction;

    public Snake(LinkedList<TilePosition> snakeParts, TilePosition direction){
        this.snakeParts = snakeParts;
        this.direction = direction;
    }

    public void eatApple(){
        snakeParts.addLast(snakeParts.peekLast());
    }

    public void makeStep(){
        snakeParts.add(0, new TilePosition(snakeParts.peekFirst().x + direction.x, snakeParts.peekFirst().y + direction.y));
        snakeParts.pollLast();
    }
}
