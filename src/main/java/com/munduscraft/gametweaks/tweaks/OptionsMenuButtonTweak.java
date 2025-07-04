package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.Iterator;

@SideOnly(Side.CLIENT)
public class OptionsMenuButtonTweak {

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui.getClass().getSimpleName().equals("GuiOptions")) {
            Iterator<GuiButton> iterator = event.buttonList.iterator();
            while (iterator.hasNext()) {
                GuiButton button = iterator.next();

                // Remove these specific buttons from options menu by ID
                if (button.id == 11 || // Difficulty
                        button.id == 8675309 || // Super Secret Settings...
                        button.id == 107 || // Broadcast Settings...
                        button.id == 104 || // Snooper Settings...
                        button.id == 105) { // Resource Packs...
                    iterator.remove();
                }
            }
            repositionOptionsButtons(event.buttonList, event.gui.width, event.gui.height);
        }
    }

    private void repositionOptionsButtons(java.util.List<GuiButton> buttonList, int screenWidth, int screenHeight) {
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
