package com.munduscraft.gametweaks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

@Mod(modid = GameTweaks.MODID, name = GameTweaks.NAME, version = GameTweaks.VERSION)
public class GameTweaks {

    public static final String MODID = "gametweaks";
    public static final String NAME = "Game Tweaks";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            registerClientEvents();
        }
    }

    private void registerClientEvents() {
        GameEventHandler eventHandler = new GameEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }
}
