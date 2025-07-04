package com.munduscraft.gametweaks.tweaks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;

@SideOnly(Side.CLIENT)
public class WindowTitleTweak {

    public WindowTitleTweak() {
        setCustomWindowProperties();
    }

    private void setCustomWindowProperties() {
        try {
            Display.setTitle("Munduscraft");

            InputStream iconStream = getClass().getResourceAsStream("/window_icon.png");
            if (iconStream != null) {
                BufferedImage iconImage = ImageIO.read(iconStream);

                ByteBuffer icon16 = convertImageToByteBuffer(resizeImage(iconImage, 16));
                ByteBuffer icon32 = convertImageToByteBuffer(resizeImage(iconImage, 32));

                Display.setIcon(new ByteBuffer[]{icon16, icon32});
                iconStream.close();
            }
        } catch (Exception e) {
            System.err.println("[GameTweaks] Failed to set custom window properties: " + e.getMessage());
        }
    }

    private BufferedImage resizeImage(BufferedImage original, int size) {
        BufferedImage resized = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = resized.createGraphics();
        g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, size, size, null);
        g.dispose();
        return resized;
    }

    private ByteBuffer convertImageToByteBuffer(BufferedImage image) {
        byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];
        int counter = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int colorSpace = image.getRGB(x, y);
                buffer[counter + 0] = (byte) ((colorSpace << 8) >> 24);
                buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
                buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
                buffer[counter + 3] = (byte) (colorSpace >> 24);
                counter += 4;
            }
        }
        return ByteBuffer.wrap(buffer);
    }
}
