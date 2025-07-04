package com.munduscraft.gametweaks;

import com.munduscraft.gametweaks.tweaks.ExperienceTweak;
import com.munduscraft.gametweaks.tweaks.PortalCreationTweak;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PortalCreationTweak());
        MinecraftForge.EVENT_BUS.register(new ExperienceTweak());
    }
}
