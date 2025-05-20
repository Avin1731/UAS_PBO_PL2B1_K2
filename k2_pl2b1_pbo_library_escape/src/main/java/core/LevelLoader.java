package main.java.core;

import main.java.entities.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public final class LevelLoader {
    public static List<GameObject> load(String filename) throws IOException {
        System.out.println("Mencoba load: " + filename); // DEBUG

        // Coba 3 lokasi berbeda
        Path[] possiblePaths = {
                Paths.get("src/main/resources/levels/" + filename),
                Paths.get("resources/levels/" + filename),
                Paths.get(System.getProperty("user.dir") + "/src/main/resources/levels/" + filename)
        };

        for (Path path : possiblePaths) {
            System.out.println("Cek path: " + path.toAbsolutePath()); // DEBUG
            if (Files.exists(path)) {
                System.out.println("File ditemukan di: " + path); // DEBUG
                return loadFromPath(path);
            }
        }

        throw new IOException("❌ File tidak ditemukan di:\n" +
                "1. " + possiblePaths[0].toAbsolutePath() + "\n" +
                "2. " + possiblePaths[1].toAbsolutePath() + "\n" +
                "3. " + possiblePaths[2].toAbsolutePath());
    }

    private static List<GameObject> loadFromPath(Path path) throws IOException {
        // Implementasi parsing file
        List<String> lines = Files.readAllLines(path);
        List<GameObject> objects = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            char[] tiles = lines.get(y).toCharArray();
            for (int x = 0; x < tiles.length; x++) {
                GameObject obj = switch (tiles[x]) {
                    case 'P' -> new Player(x, y);
                    case 'W', '#' -> new Wall(x, y);
                    case 'B' -> new Book(x, y);
                    default -> null;
                };
                if (obj != null) objects.add(obj);
            }
        }
        return objects;
    }
}