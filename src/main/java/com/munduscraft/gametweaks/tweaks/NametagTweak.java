package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderPlayerEvent;

@SideOnly(Side.CLIENT)
public class NametagTweak {

    @SubscribeEvent
    public void onRenderPlayerNameTag(RenderPlayerEvent.Specials.Pre event) {
        event.setCanceled(true);
    }
}
