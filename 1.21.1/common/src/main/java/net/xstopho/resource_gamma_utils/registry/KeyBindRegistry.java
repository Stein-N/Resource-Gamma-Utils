package net.xstopho.resource_gamma_utils.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.xstopho.resource_gamma_utils.GammaConstants;
import org.lwjgl.glfw.GLFW;

public class KeyBindRegistry {

    private static final String CATEGORY = "key.categories." + GammaConstants.MOD_ID;
    private static final String BASE_KEY = "key." + GammaConstants.MOD_ID + ".";

    public static final KeyMapping GAMMA_TOGGLE = create("gammaToggle", GLFW.GLFW_KEY_G);
    public static final KeyMapping INCREASE_GAMMA = create("increaseGamma", GLFW.GLFW_KEY_UP);
    public static final KeyMapping DECREASE_GAMMA = create("decreaseGamma", GLFW.GLFW_KEY_DOWN);
    public static final KeyMapping MAX_GAMMA = create("maxGamma", GLFW.GLFW_KEY_UNKNOWN);
    public static final KeyMapping MIN_GAMMA = create("minGamma", GLFW.GLFW_KEY_UNKNOWN);
    public static final KeyMapping NIGHT_VISION_TOGGLE = create("nightVisionToggle", GLFW.GLFW_KEY_H);
    public static final KeyMapping INCREASE_NIGHT_VISION = create("increaseNightVision", GLFW.GLFW_KEY_RIGHT);
    public static final KeyMapping DECREASE_NIGHT_VISION = create("decreaseNightVision", GLFW.GLFW_KEY_LEFT);

    private static KeyMapping create(String key, int keyCode) {
        return new KeyMapping(BASE_KEY + key, InputConstants.Type.KEYSYM, keyCode, CATEGORY);
    }
}
