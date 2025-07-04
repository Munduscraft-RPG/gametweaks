package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class ExperienceBarTweak {

    public ExperienceBarTweak() {
        GuiIngameForge.renderExperiance = false;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            GuiIngameForge.left_height -= 6;
        }

        if (event.type == RenderGameOverlayEvent.ElementType.FOOD) {
            GuiIngameForge.right_height -= 6;
        }
    }
}