package main.java.core;

import javax.swing.*;
import java.awt.*;

public class Engine extends JPanel {
    private final GameWorld world;

    public Engine() {
        this.world = new GameWorld();
        setupWindow();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(world.getPlayerInput());
        startGameLoop();
    }

    private void setupWindow() {
        JFrame frame = new JFrame("Library Escape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    public void startGameLoop() {
        Thread.startVirtualThread(() -> {
            while (true) {
                world.update();
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.render((Graphics2D) g);
    }
}