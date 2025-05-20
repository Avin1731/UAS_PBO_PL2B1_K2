package main.java.entities;

import java.awt.*;

public non-sealed class Book implements GameObject {
    private final Position position;
    private static final int TILE_SIZE = 32; // Assuming a standard tile size

    public Book(int x, int y) {
        this.position = new Position(x, y); // You'll need a Position class
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public void update() {
        // If not dynamic, leave empty
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(position.x() * TILE_SIZE, position.y() * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Optional: Add book details
        g.setColor(Color.BLACK);
        g.drawString("B", position.x() * TILE_SIZE + TILE_SIZE/2, position.y() * TILE_SIZE + TILE_SIZE/2);
    }
}