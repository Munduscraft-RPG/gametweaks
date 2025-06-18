package com.munduscraft.clienttweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class DebugTweaks {

    private Minecraft mc = Minecraft.getMinecraft();

    /**
     * Hide coordinates from F3 debug menu - keep only the first line (FPS/version info)
     */
    @SubscribeEvent
    public void onRenderGameOverlayDebug(RenderGameOverlayEvent.Text event) {
        if (mc.gameSettings.showDebugInfo && !event.left.isEmpty()) {
            // Keep only the first line (Minecraft version/FPS), remove everything else
            String firstLine = event.left.get(0);
            event.left.clear();
            if (firstLine != null) {
                event.left.add(firstLine);
            }
        }
    }
}