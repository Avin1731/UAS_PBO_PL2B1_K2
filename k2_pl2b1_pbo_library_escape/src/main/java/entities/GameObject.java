package main.java.entities;

import java.awt.*;

public sealed interface GameObject permits Book, Player, Wall {
    Position position();
    void update();
    void render(Graphics2D g);

    record Position(int x, int y) {}

}
