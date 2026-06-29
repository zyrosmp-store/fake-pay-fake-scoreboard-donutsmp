package com.donutsmp.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
    private static final File CONFIG_FILE = CONFIG_DIR.resolve("donutsmp_scoreboard.json").toFile();
    
    public static String title = "§6SCOREBOARD";
    public static List<String> lines = new ArrayList<>();
    public static boolean enabled = true;
    public static int x = 10;
    public static int y = 10;
    public static float scale = 1.0f;

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ScoreboardConfigData data = GSON.fromJson(reader, ScoreboardConfigData.class);
                if (data != null) {
                    title = data.title != null ? data.title : title;
                    lines = data.lines != null ? data.lines : lines;
                    enabled = data.enabled;
                    x = data.x;
                    y = data.y;
                    scale = data.scale;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Create default config
            lines.add("§eJoueur1: §6100€");
            lines.add("§eJoueur2: §650€");
            lines.add("§eJoueur3: §625€");
            save();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_DIR);
            ScoreboardConfigData data = new ScoreboardConfigData();
            data.title = title;
            data.lines = lines;
            data.enabled = enabled;
            data.x = x;
            data.y = y;
            data.scale = scale;
            
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(data, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ScoreboardConfigData {
        public String title;
        public List<String> lines;
        public boolean enabled;
        public int x;
        public int y;
        public float scale;
    }
}