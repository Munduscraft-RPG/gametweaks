package com.munduscraft.gametweaks;

import com.munduscraft.gametweaks.tweaks.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

@SideOnly(Side.CLIENT)
public class GameEventHandler {

    public GameEventHandler() {
        registerEventHandlers();
        new WindowTitleTweak();
    }

    private void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new ExperienceBarTweak());
        MinecraftForge.EVENT_BUS.register(new CrosshairTweak());
        
        MinecraftForge.EVENT_BUS.register(new F3DebugMenuTweak());
        MinecraftForge.EVENT_BUS.register(new BlockHighlightTweak());
        MinecraftForge.EVENT_BUS.register(new NametagTweak());
        MinecraftForge.EVENT_BUS.register(new InGameMenuButtonTweak());
        MinecraftForge.EVENT_BUS.register(new OptionsMenuButtonTweak());
        MinecraftForge.EVENT_BUS.register(new PortalCreationTweak());
        MinecraftForge.EVENT_BUS.register(new AchievementTweak());
    }
}
