package com.imjustdoom.stopdropnroll;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(StopDropNRoll.MOD_ID)
public class StopDropNRollForge {

    public StopDropNRollForge() {
        EventBuses.registerModEventBus(StopDropNRoll.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        StopDropNRoll.init();
    }
}
