package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;

public class PortalCreationTweak {

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.PlaceEvent event) {
        if (event.placedBlock == Blocks.portal || event.placedBlock == Blocks.end_portal) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onMultiBlockPlace(BlockEvent.MultiPlaceEvent event) {
        if (event.placedBlock == Blocks.portal || event.placedBlock == Blocks.end_portal) {
            event.setCanceled(true);
        }
    }
}
