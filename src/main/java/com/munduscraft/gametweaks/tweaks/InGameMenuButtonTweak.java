package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.Iterator;

@SideOnly(Side.CLIENT)
public class InGameMenuButtonTweak {

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiGameOver) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    if (mc.thePlayer != null && mc.currentScreen instanceof GuiGameOver) {
                        mc.thePlayer.respawnPlayer();
                    }
                } catch (InterruptedException e) {
                }
            }).start();
            return;
        }

        if (event.gui instanceof GuiIngameMenu) {
            Iterator<GuiButton> iterator = event.buttonList.iterator();
            while (iterator.hasNext()) {
                GuiButton button = iterator.next();

                // Remove buttons by ID
                if (button.id == 4 || // Back to Game
                    button.id == 5 || // Achievements
                    button.id == 6 || // Statistics
                    button.id == 7 || // Open to LAN
                    button.id == 12) { // Mod Options...
                    iterator.remove();
                }
            }
            repositionRemainingButtons(event.buttonList, event.gui.width, event.gui.height);
        }
    }

    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (event.gui instanceof GuiIngameMenu) {
            event.setCanceled(true);
            GuiIngameMenu menu = (GuiIngameMenu) event.gui;
            menu.drawDefaultBackground();
            try {
                java.lang.reflect.Field buttonListField = menu.getClass().getSuperclass().getDeclaredField("buttonList");
                buttonListField.setAccessible(true);
                @SuppressWarnings("unchecked")
                java.util.List<GuiButton> buttons = (java.util.List<GuiButton>) buttonListField.get(menu);

                for (GuiButton button : buttons) {
                    button.drawButton(mc, event.mouseX, event.mouseY);
                }
            } catch (Exception e) {
                event.setCanceled(false);
            }
        }
    }

    private void repositionRemainingButtons(java.util.List<GuiButton> buttonList, int screenWidth, int screenHeight) {
        int centerX = screenWidth / 2;
        int startY = screenHeight / 4 + 48;
        int buttonSpacing = 24;

        int currentY = startY;
        for (GuiButton button : buttonList) {
            button.xPosition = centerX - 100;
            button.yPosition = currentY;
            button.width = 200;
            currentY += buttonSpacing;
        }
    }
}