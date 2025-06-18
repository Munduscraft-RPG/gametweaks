package com.munduscraft.clienttweaks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ClientTweaks.MODID, name = ClientTweaks.NAME, version = ClientTweaks.VERSION)
public class ClientTweaks {

    public static final String MODID = "clienttweaks";
    public static final String NAME = "Client Tweaks";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        // Register event handlers only on client side
        if (event.getSide().isClient()) {
            registerClientEvents();
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerClientEvents() {
        ClientEventHandler eventHandler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }
}