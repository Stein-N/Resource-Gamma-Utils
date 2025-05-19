package net.xstopho.resource_gamma_util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaConstants {
    public static final String MOD_ID = "resource_gamma_util";
    public static final String MOD_NAME = "Resource Gamma Util";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static final String CATEGORY = MOD_ID + ".key.category";
    public static final KeyMapping TOGGLE = new KeyMapping(MOD_ID + ".key.toggle", GLFW.GLFW_KEY_H, CATEGORY);

    public static void useHotkey(Minecraft client) {
        if (TOGGLE.consumeClick()) client.options.gamma().set(1500.00);
    }
}
