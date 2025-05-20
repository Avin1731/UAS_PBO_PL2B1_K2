package main.java.entities;

import java.awt.*;

public final class Wall implements GameObject {
    private final Position pos;

    public Wall(int x, int y) {
        this.pos = new Position(x, y);
    }

    @Override
    public Position position() {
        return pos;
    }

    @Override
    public void update() {
        // Tidak bergerak
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(pos.x() * 32, pos.y() * 32, 32, 32);
    }
}
