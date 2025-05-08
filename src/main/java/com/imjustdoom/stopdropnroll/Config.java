package com.imjustdoom.stopdropnroll;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static double CHANCE = 0.5;
    public static double REMOVE_PERCENTAGE = 0.2;

    public static void init() {
        Main.get().saveDefaultConfig();
        Main.get().reloadConfig();
        FileConfiguration fileConfiguration = Main.get().getConfig();
        CHANCE = fileConfiguration.getDouble("chance", CHANCE);
        REMOVE_PERCENTAGE = fileConfiguration.getDouble("fire-tick-decrease-percentage", REMOVE_PERCENTAGE);
    }
}
