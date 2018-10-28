package com.snake.gui;

import com.snake.logic.World;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class UpdateLoop {

    private BufferedImage img = null;

    World world;
    Renderer renderer;
    MainFrame frame;

    public UpdateLoop(World world, Renderer renderer, MainFrame frame){
        this.world = world;
        this.renderer = renderer;
        this.frame = frame;
    }

    public void start() {
        while (true) {
            boolean gameOver = world.updateWorld();
            if (gameOver) {
                renderer.over(world.snake.snakeParts.size());
                break;
            }

            if (img == null || img.getWidth() != frame.getHeight() || img.getHeight() != frame.getHeight()) {
                img = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
            renderer.render(pixels, img.getWidth(), img.getHeight());

            frame.paint(img);
        }
    }
}
