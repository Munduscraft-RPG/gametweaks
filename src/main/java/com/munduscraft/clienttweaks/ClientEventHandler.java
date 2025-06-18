package com.munduscraft.clienttweaks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

    /**
     * Initialize and register all client-side tweaks
     */
    public ClientEventHandler() {
        // Register all tweak handlers
        registerEventHandlers();

        // Initialize window tweaks (constructor sets title/icon)
        new WindowTweaks();
    }

    /**
     * Register all event handlers for the various tweaks
     */
    private void registerEventHandlers() {
        // HUD-related tweaks (experience bar, crosshair, health/hunger positioning)
        HudTweaks hudTweaks = new HudTweaks();
        MinecraftForge.EVENT_BUS.register(hudTweaks);

        // Debug menu tweaks (F3 coordinate hiding)
        DebugTweaks debugTweaks = new DebugTweaks();
        MinecraftForge.EVENT_BUS.register(debugTweaks);

        // Rendering tweaks (block highlights, nametags)
        RenderingTweaks renderingTweaks = new RenderingTweaks();
        MinecraftForge.EVENT_BUS.register(renderingTweaks);

        // GUI tweaks (menu modifications, death screen)
        GuiTweaks guiTweaks = new GuiTweaks();
        MinecraftForge.EVENT_BUS.register(guiTweaks);

        // Achievement tweaks (disable notifications)
        AchievementTweaks achievementTweaks = new AchievementTweaks();
        FMLCommonHandler.instance().bus().register(achievementTweaks);
    }
}