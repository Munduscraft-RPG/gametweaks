package com.munduscraft.gametweaks;

import com.munduscraft.gametweaks.tweaks.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        MinecraftForge.EVENT_BUS.register(new BlockHighlightTweak());
        MinecraftForge.EVENT_BUS.register(new CrosshairTweak());
        MinecraftForge.EVENT_BUS.register(new ExperienceBarTweak());
        MinecraftForge.EVENT_BUS.register(new F3DebugMenuTweak());
        MinecraftForge.EVENT_BUS.register(new InGameMenuButtonTweak());
        MinecraftForge.EVENT_BUS.register(new NametagTweak());
        MinecraftForge.EVENT_BUS.register(new OptionsMenuButtonTweak());

        MinecraftForge.EVENT_BUS.register(new AchievementTweak());

        new WindowTitleTweak();
    }
}
