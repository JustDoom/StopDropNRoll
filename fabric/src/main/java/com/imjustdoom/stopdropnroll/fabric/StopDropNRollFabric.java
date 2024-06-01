package com.imjustdoom.stopdropnroll.fabric;

import com.imjustdoom.stopdropnroll.StopDropNRoll;
import net.fabricmc.api.ModInitializer;

public class StopDropNRollFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        StopDropNRoll.init();
    }
}
