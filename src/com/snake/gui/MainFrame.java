package com.snake.gui;

import com.snake.logic.Snake;
import com.snake.logic.TilePosition;
import com.snake.logic.World;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static java.lang.Math.min;

public class MainFrame extends JFrame implements KeyListener {

    World world;

    public MainFrame(World world){
        this.world = world;
        int width = 605;
        int height = 625;
        setPreferredSize(new Dimension(width, height));
        pack();
        createBufferStrategy(2);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        addKeyListener(this);
    }

    boolean start = false;

    public static void main(String[] args) {
        World world = new World(15, 15);
        MainFrame frame = new MainFrame(world);
        int tileSize = 40;
        Renderer renderer = new Renderer(world, tileSize);
        UpdateLoop loop = new UpdateLoop(world, renderer, frame);
        loop.start();
    }


    public void paint(BufferedImage image){
        BufferStrategy bs = getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g.dispose();

        bs.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            if (world.snake.direction.x != 1 && world.snake.direction.y != 0) {
                world.snake.direction = new TilePosition(-1, 0);
                start = true;
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            if (world.snake.direction.x != 0 && world.snake.direction.y != -1) {
                world.snake.direction = new TilePosition(0, 1);
                start = true;
            }
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            if (world.snake.direction.x != -1 && world.snake.direction.y != 0) {
                world.snake.direction = new TilePosition(1, 0);
                start = true;
            }
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            if (world.snake.direction.x != 0 && world.snake.direction.y != 1) {
                world.snake.direction = new TilePosition(0, -1);
                start = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
