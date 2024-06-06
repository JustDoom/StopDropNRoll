package com.imjustdoom.stopdropnroll.config;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class Config {

    private static Path FILE_PATH;
    private static Properties PROPERTIES;

    public static float CHANCE;
    public static float FIRE_TICK_REMOVE_PERCENTAGE;

    public static void init() throws IOException {
        PROPERTIES = new Properties();
        FILE_PATH = Path.of(FMLPaths.CONFIGDIR.get() + "/stop-drop-n-roll.properties");
        if (!FILE_PATH.toFile().exists()) {
            new File(FILE_PATH.toString()).createNewFile();
        }
        PROPERTIES.load(new FileInputStream(FILE_PATH.toFile()));

        CHANCE = getFloat("chance", "0.5");
        FIRE_TICK_REMOVE_PERCENTAGE = getFloat("fire-tick-remove-percentage", "0.2");

        save();
    }

    private static String getString(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return value;
    }

    private static int getInt(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return Integer.parseInt(value);
    }

    private static float getFloat(final String setting, final String defaultValue) {
        String value = PROPERTIES.getProperty(setting);
        if (value == null) {
            PROPERTIES.setProperty(setting, defaultValue);
            value = defaultValue;
        }
        return Float.parseFloat(value);
    }

    public static void save() throws IOException {
        PROPERTIES.store(new FileWriter(FILE_PATH.toFile()),
                "Config for StopDropNRoll\n" +
                        "'chance' is on a scale of 0-1. 0 is 0%, 1 is 100% and 0.32 is 32%\n" +
                        "'chance' configures the chance for the remaining fire ticks to go down when you \"stop drop n roll\"\n" +
                        "'fire-tick-remove-percentage' is on a scale of 0-1. 0 is 0%, 1 is 100% and 0.32 is 32%\n" +
                        "'fire-tick-remove-percentage' configures how much of the fire ticks are removed. If it's set to 0.1,\n" +
                        "the remaining time will go down by 10%.\n");
    }
}