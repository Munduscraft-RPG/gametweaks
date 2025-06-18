package com.munduscraft.clienttweaks;

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
public class GuiTweaks {

    private Minecraft mc = Minecraft.getMinecraft();

    /**
     * Modify GUI menus to remove unwanted buttons and handle death screen
     */
    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        // Handle death screen - automatically respawn
        if (event.gui instanceof GuiGameOver) {
            // Use a delayed respawn to avoid event loop
            new Thread(() -> {
                try {
                    Thread.sleep(100); // Small delay to avoid infinite loop
                    if (mc.thePlayer != null && mc.currentScreen instanceof GuiGameOver) {
                        mc.thePlayer.respawnPlayer();
                    }
                } catch (InterruptedException e) {
                    // Ignore
                }
            }).start();
            return;
        }

        if (event.gui instanceof GuiIngameMenu) {
            // Remove unwanted buttons from escape menu
            Iterator<GuiButton> iterator = event.buttonList.iterator();
            while (iterator.hasNext()) {
                GuiButton button = iterator.next();
                String buttonText = button.displayString;

                // Remove these specific buttons
                if (buttonText.equals("Back to Game") ||
                        buttonText.equals("Achievements") ||
                        buttonText.equals("Statistics") ||
                        buttonText.equals("Open to LAN") ||
                        buttonText.equals("Mod Options...")) {
                    iterator.remove();
                }
            }

            // Re-position remaining buttons to center them properly
            repositionRemainingButtons(event.buttonList, event.gui.width, event.gui.height);
        }

        // Handle Options menu (GuiOptions)
        if (event.gui.getClass().getSimpleName().equals("GuiOptions")) {
            // Remove unwanted buttons from options menu
            Iterator<GuiButton> iterator = event.buttonList.iterator();
            while (iterator.hasNext()) {
                GuiButton button = iterator.next();
                String buttonText = button.displayString;

                // Remove these specific buttons from options menu
                if (buttonText.equals("Difficulty:") ||
                        buttonText.contains("Difficulty:") ||
                        buttonText.equals("Super Secret Settings...") ||
                        buttonText.equals("Broadcast Settings...") ||
                        buttonText.equals("Snooper Settings...") ||
                        buttonText.equals("Resource Packs...")) {
                    iterator.remove();
                }
            }

            // Re-organize remaining buttons in the options menu
            repositionOptionsButtons(event.buttonList, event.gui.width, event.gui.height);
        }
    }

    /**
     * Hide the "Game Menu" title text from the escape menu
     */
    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (event.gui instanceof GuiIngameMenu) {
            // Cancel the default drawing which includes the "Game Menu" text
            event.setCanceled(true);

            // Manually draw only the buttons without the title
            GuiIngameMenu menu = (GuiIngameMenu) event.gui;

            // Draw the dark background
            menu.drawDefaultBackground();

            // Draw only the buttons, skip the title text
            // Use reflection to access the protected buttonList field
            try {
                java.lang.reflect.Field buttonListField = menu.getClass().getSuperclass().getDeclaredField("buttonList");
                buttonListField.setAccessible(true);
                @SuppressWarnings("unchecked")
                java.util.List<GuiButton> buttons = (java.util.List<GuiButton>) buttonListField.get(menu);

                for (GuiButton button : buttons) {
                    button.drawButton(mc, event.mouseX, event.mouseY);
                }
            } catch (Exception e) {
                // If reflection fails, fall back to default drawing
                event.setCanceled(false);
            }
        }
    }

    /**
     * Re-position the remaining buttons to look centered and organized
     */
    private void repositionRemainingButtons(java.util.List<GuiButton> buttonList, int screenWidth, int screenHeight) {
        int centerX = screenWidth / 2;
        int startY = screenHeight / 4 + 48;
        int buttonSpacing = 24;

        int currentY = startY;
        for (GuiButton button : buttonList) {
            // Set consistent button size and position
            button.xPosition = centerX - 100; // Center horizontally
            button.yPosition = currentY;
            button.width = 200; // Ensure all buttons have the same width
            currentY += buttonSpacing;
        }
    }

    /**
     * Re-organize buttons in the options menu after removing unwanted ones
     */
    private void repositionOptionsButtons(java.util.List<GuiButton> buttonList, int screenWidth, int screenHeight) {
        // Use a grid layout for options buttons
        int buttonsPerRow = 2;
        int buttonWidth = 150;
        int buttonHeight = 20;
        int spacingX = 160;
        int spacingY = 24;
        int startX = screenWidth / 2 - spacingX / 2;
        int startY = screenHeight / 6;

        for (int i = 0; i < buttonList.size(); i++) {
            GuiButton button = buttonList.get(i);
            int row = i / buttonsPerRow;
            int col = i % buttonsPerRow;

            button.xPosition = startX + (col * spacingX) - buttonWidth / 2;
            button.yPosition = startY + (row * spacingY);
            button.width = buttonWidth;
            button.height = buttonHeight;
        }
    }
}