package com.munduscraft.gametweaks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = GameTweaks.MODID, name = GameTweaks.NAME, version = GameTweaks.VERSION)
public class GameTweaks {

    public static final String MODID = "gametweaks";
    public static final String NAME = "Game Tweaks";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "com.munduscraft.gametweaks.ClientProxy", serverSide = "com.munduscraft.gametweaks.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
