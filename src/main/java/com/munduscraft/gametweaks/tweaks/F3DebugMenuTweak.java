package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class F3DebugMenuTweak {

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlayDebug(RenderGameOverlayEvent.Text event) {
        if (mc.gameSettings.showDebugInfo && !event.left.isEmpty()) {
            String firstLine = event.left.get(0);
            event.left.clear();
            if (firstLine != null) {
                event.left.add(firstLine);
            }
        }
    }
}
