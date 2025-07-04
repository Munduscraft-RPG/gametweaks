package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.event.entity.player.AchievementEvent;

@SideOnly(Side.CLIENT)
public class AchievementTweak {

    @SubscribeEvent
    public void onAchievement(AchievementEvent event) {
        event.setCanceled(true);
    }
}
