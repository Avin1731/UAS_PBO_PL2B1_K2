package main.java;

import main.java.core.LevelLoader;
import main.java.core.MapGenerator;
import main.java.entities.GameObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GameInitializer {
    public static void main(String[] args) {
        try {
            // Pastikan direktori exists
            Path levelsDir = Paths.get("src/main/resources/levels");
            if (!Files.exists(levelsDir)) {
                Files.createDirectories(levelsDir);
            }

            // Generate 4 random maps
            for (int i = 1; i <= 4; i++) {
                String[] map = MapGenerator.generateRandomMap();
                String filename = "random_level_" + i + ".txt";
                Path filePath = levelsDir.resolve(filename);

                // Save to file
                Files.write(filePath, List.of(map));

                // Load from file
                List<GameObject> objects = LevelLoader.load("/levels/" + filename);

                System.out.println("Level " + i + " loaded with " + objects.size() + " objects");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}