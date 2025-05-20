package main.java.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    private static final int WIDTH = 19;
    private static final int HEIGHT = 13;
    private static final Random random = new Random();
    private static final String LEVELS_DIR = "src/main/resources/levels/";

    public static void main(String[] args) {
        try {
            // Create levels directory if it doesn't exist
            Files.createDirectories(Paths.get(LEVELS_DIR));

            // Generate and save 4 random maps
            for (int i = 1; i <= 4; i++) {
                String[] map = generateRandomMap();
                String filename = "level" + i + ".map";
                saveMapToFile(map, filename);
                System.out.println("Generated " + filename);
            }
        } catch (IOException e) {
            System.err.println("Error generating maps: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String[] generateRandomMap() {
        String[] map = new String[HEIGHT];

        // Top border
        map[0] = "#".repeat(WIDTH);

        // Generate middle rows
        for (int y = 1; y < HEIGHT - 1; y++) {
            StringBuilder row = new StringBuilder("#");

            for (int x = 1; x < WIDTH - 1; x++) {
                char tile = getRandomTile();
                row.append(tile);
            }

            row.append("#");
            map[y] = row.toString();
        }

        // Bottom border
        map[HEIGHT - 1] = "#".repeat(WIDTH);

        // Place player at random position
        placePlayer(map);

        return map;
    }

    private static void saveMapToFile(String[] map, String filename) throws IOException {
        Path path = Paths.get(LEVELS_DIR + filename);
        Files.write(path, List.of(map));
    }

    private static char getRandomTile() {
        double rand = random.nextDouble();
        if (rand < 0.05) return 'W';  // 5% chance for wall
        if (rand < 0.15) return 'B';  // 10% chance for book
        return ' ';                   // 85% chance for empty space
    }

    private static void placePlayer(String[] map) {
        int x, y;
        do {
            x = 1 + random.nextInt(WIDTH - 2);
            y = 1 + random.nextInt(HEIGHT - 2);
        } while (map[y].charAt(x) != ' ');

        // Replace empty space with player
        char[] row = map[y].toCharArray();
        row[x] = 'P';
        map[y] = new String(row);
    }

    public static void printMap(String[] map) {
        for (String row : map) {
            System.out.println(row);
        }
    }
}