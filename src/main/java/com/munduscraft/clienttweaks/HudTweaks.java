package com.munduscraft.clienttweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class HudTweaks {

    private Minecraft mc = Minecraft.getMinecraft();

    /**
     * Disable experience bar rendering
     */
    public HudTweaks() {
        // Disable experience bar rendering completely
        GuiIngameForge.renderExperiance = false;
    }

    /**
     * Adjust positioning before health/hunger bars render
     */
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        // Adjust positioning for health bar (left side)
        if (event.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            GuiIngameForge.left_height -= 6; // Move health down slightly
        }

        // Adjust positioning for food bar (right side)
        if (event.type == RenderGameOverlayEvent.ElementType.FOOD) {
            GuiIngameForge.right_height -= 6; // Move food down slightly
        }

        // Don't adjust air bar positioning - let it render in its normal position
        // relative to the food bar
    }

    /**
     * Hide the crosshair from rendering
     */
    @SubscribeEvent
    public void onRenderGameOverlayCrosshair(RenderGameOverlayEvent.Pre event) {
        // Hide crosshair
        if (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            event.setCanceled(true);
        }
    }
}