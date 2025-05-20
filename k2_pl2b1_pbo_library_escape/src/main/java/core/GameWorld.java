package main.java.core;

import main.java.entities.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class GameWorld {
    private final List<GameObject> objects;
    private Player player;

    public GameWorld() {
        try {
            this.objects = LevelLoader.load("level1.map");
            this.player = findPlayer();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load level", e);
        }
    }

    private Player findPlayer() {
        return (Player) objects.stream()
                .filter(obj -> obj instanceof Player)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player not found in level"));
    }

    public boolean canMoveTo(int newX, int newY) {
        return objects.stream()
                .filter(obj -> obj instanceof Wall)
                .noneMatch(wall -> wall.position().x() == newX &&
                        wall.position().y() == newY);
    }

    public void checkBookCollection() {
        objects.removeIf(obj -> {
            if (obj instanceof Book &&
                    obj.position().equals(player.position())) {
                player.addBook((Book) obj);
                return true;
            }
            return false;
        });
    }

    public void update() {
        objects.forEach(GameObject::update);
    }

    public void render(Graphics2D g) {
        objects.forEach(obj -> obj.render(g));
    }

    public KeyAdapter getPlayerInput() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.handleInput(e.getKeyCode(), GameWorld.this);
            }
        };
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public Player getPlayer() {
        return player;
    }
}