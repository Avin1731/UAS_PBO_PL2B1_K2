package main.java.entities;

import main.java.core.GameWorld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public final class Player implements GameObject {
    private Position pos;
    private final List<Book> inventory = new ArrayList<>();

    public Player(int x, int y) {
        this.pos = new Position(x, y);
    }

    @Override
    public Position position() {
        return pos;
    }

    public void moveTo(int newX, int newY) {
        this.pos = new Position(newX, newY);
    }

    @Override
    public void update() {
        // Player update logic if needed
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(pos.x() * 32, pos.y() * 32, 32, 32);
    }

    public void handleInput(int keyCode, GameWorld world) {
        int newX = pos.x();
        int newY = pos.y();

        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                newY--;
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                newY++;
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                newX--;
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                newX++;
                break;
        }

        if (world.canMoveTo(newX, newY)) {
            moveTo(newX, newY);
            world.checkBookCollection();
        }
    }

    public void addBook(Book book) {
        inventory.add(book);
        System.out.println("Buku diambil! Total buku: " + inventory.size());
    }

    public List<Book> getInventory() {
        return inventory;
    }
}