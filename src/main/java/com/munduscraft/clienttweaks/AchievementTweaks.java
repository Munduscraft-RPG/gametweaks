package com.munduscraft.clienttweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.event.entity.player.AchievementEvent;

@SideOnly(Side.CLIENT)
public class AchievementTweaks {

    /**
     * Block all achievement notifications and chat messages
     */
    @SubscribeEvent
    public void onAchievement(AchievementEvent event) {
        // Cancel all achievement events to prevent popups and chat messages
        event.setCanceled(true);
    }
}