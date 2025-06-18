package com.munduscraft.clienttweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

@SideOnly(Side.CLIENT)
public class RenderingTweaks {

    /**
     * Disable the block selection outline
     */
    @SubscribeEvent
    public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
        // Cancel the block highlight rendering
        event.setCanceled(true);
    }

    /**
     * Hide player nametags
     */
    @SubscribeEvent
    public void onRenderPlayerNameTag(RenderPlayerEvent.Specials.Pre event) {
        // Cancel the nametag rendering for all players
        event.setCanceled(true);
    }
}