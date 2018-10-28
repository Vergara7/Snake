package com.snake.gui;

import com.snake.logic.Snake;
import com.snake.logic.TilePosition;
import com.snake.logic.World;

public class Renderer {

    World world;
    int tileSize;

    public Renderer(World world, int tileSize){
        this.world = world;
        this.tileSize = tileSize;
    }

    long last = -100;

    public void render(int[] pixels, int width, int height){
        if (System.currentTimeMillis() - last > 100) {
            renderBackground(world.getwidth(), world.getHeight(), pixels, width, height);
            renderSnake(world.getsnake(), pixels, width);
            renderApple(world.getapple(), pixels, width);
            last = System.currentTimeMillis();
        }
    }

    int black = color(0, 0, 0);
    int white = color(255, 255, 255);

    public void renderBackground(int Worldwidth, int Worldheight, int[] pixels, int width, int height) {
        for (int i = 0; i < width * height; i++)
            pixels[i] = black;
        for (int y = 0; y < Worldwidth; y++){
           for (int x = 0; x < Worldheight; x++){
                for (int pixelY = y * tileSize; pixelY < (y + 1) * tileSize - 1; pixelY++){
                    for (int pixelX = x * tileSize; pixelX < (x + 1) * tileSize - 1; pixelX++){
                        pixels[(pixelX + 23) * width + pixelY + 5] = white;
                    }
                }
            }
        }
    }

    public void renderSnake(Snake snake, int[] pixels, int width) {
        for (int i = 0; i < snake.snakeParts.size(); i++){
            for (int x = snake.snakeParts.get(i).x * tileSize; x < (snake.snakeParts.get(i).x + 1) * tileSize - 1; x++){
                for (int y = snake.snakeParts.get(i).y * tileSize; y < (snake.snakeParts.get(i).y + 1) * tileSize - 1; y++){
                    if (i == 0)
                        pixels[(x + 23) * width + y + 5] = color(0, 0, 255);
                    else
                        pixels[(x + 23) * width + y + 5] = color(255, 0, 0);
                }
            }
        }
    }

    public void renderApple(TilePosition apple, int[]pixels, int width) {
         for (int x = apple.x * tileSize; x < (apple.x + 1) * tileSize - 1; x++){
             for (int y = apple.y * tileSize; y < (apple.y + 1) * tileSize - 1; y++){
                pixels[(x + 23) * width + y + 5] = color(0, 255, 0);
             }
         }
    }

    private static int color(int r, int g, int b){
        return color(r, g, b, 255);
    }

    private static int color(int r, int g, int b, int a){
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public void over(int result){
        System.out.println("Game over");
        System.out.println("Your result " + result + "!!!");
    }

}
