package com.imjustdoom.stopdropnroll;

import com.imjustdoom.stopdropnroll.config.Config;

import java.io.IOException;

public class StopDropNRoll {
    public static final String MOD_ID = "stopdropnroll";
    
    public static void init() {
        try {
            Config.init();
        } catch (IOException exception) {
            System.err.println("There was an error setting up or saving the config file for Stop Stop N Roll :(");
            exception.printStackTrace();
        }
    }
}
