package com.snake.logic;

import java.util.LinkedList;
import java.util.Random;

public class World {
    int height, width;
    public Snake snake;
    TilePosition apple;
    boolean snakeIsSlepping = false;

    Random r = new Random(239);

    public World(int w, int h){
        this.width = w;
        this.height = h;
    }

    public Snake getsnake() { return snake; }

    public TilePosition getapple() { return apple; }

    public int getwidth() { return width; }

    public int getHeight(){
        return height;
    }

    public void generateApple(){
        boolean[][] field = new boolean[height][];
        for (int i = 0; i < height; i++) {
            field[i] = new boolean[width];
        }

        for (int i = 0; i < snake.snakeParts.size(); i++){
            TilePosition point = snake.snakeParts.get(i);
            field[point.x][point.y] = true;
        }
        LinkedList<TilePosition> free = new LinkedList<>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (!field[i][j])
                    free.addLast(new TilePosition(i, j));
            }
        }

        apple = free.get(r.nextInt(free.size()));
    }

    public boolean checkApple(){
        return apple.x == snake.snakeParts.peekFirst().x && apple.y == snake.snakeParts.peekFirst().y;
    }

    long last = -100;

    public boolean updateWorld(){

        if (snake == null){
            LinkedList<TilePosition> head = new LinkedList<>();
            head.push(new TilePosition(7, 7));
            TilePosition direction = new TilePosition(0, 1);
            snake = new Snake(head, direction);
        }

        if (apple == null){
            generateApple();;
        }

        if (System.currentTimeMillis() - last > 100) {

            snake.makeStep();

            if (snake.snakeParts.peekFirst().x == -1) {
                snake.snakeParts.set(0, new TilePosition(height - 1, snake.snakeParts.get(0).y));
            }

            if (snake.snakeParts.peekFirst().x == height) {
                snake.snakeParts.set(0, new TilePosition(0, snake.snakeParts.get(0).y));
            }

            if (snake.snakeParts.peekFirst().y == -1) {
                snake.snakeParts.set(0, new TilePosition(snake.snakeParts.get(0).x, width - 1));
            }

            if (snake.snakeParts.peekFirst().y == width) {
                snake.snakeParts.set(0, new TilePosition(snake.snakeParts.get(0).x, 0));
            }

            if (checkApple()) {
                snake.eatApple();
                generateApple();
            }


            for (int i = 1; i < snake.snakeParts.size(); i++) {
                if (snake.snakeParts.get(i).x == snake.snakeParts.peekFirst().x && snake.snakeParts.get(i).y == snake.snakeParts.peekFirst().y && snake.snakeParts.size() > 2) {
                    snakeIsSlepping = true;
                }
            }
            
            last = System.currentTimeMillis();
        }
        return snakeIsSlepping;
    }

}
