package net.xstopho.resource_gamma_util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.xstopho.resource_gamma_util.service.GammaService;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaConstants {
    public static final String MOD_ID = "resource_gamma_util";
    public static final String MOD_NAME = "Resource Gamma Util";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(MOD_ID, "main"));
    public static final KeyMapping TOGGLE = new KeyMapping(MOD_ID + ".key.toggle", GLFW.GLFW_KEY_H, CATEGORY);

    public static void useHotkey(Minecraft client) {
        boolean shader = GammaService.isShaderActive();

        if (TOGGLE.consumeClick()) {
            if (!shader) client.options.gamma().set(1500.00);
            else {
                LocalPlayer p = client.player;
                if (p == null) return;

                if (p.hasEffect(MobEffects.NIGHT_VISION)) {
                    p.removeEffect(MobEffects.NIGHT_VISION);
                } else {
                    MobEffectInstance effect = new MobEffectInstance(MobEffects.NIGHT_VISION, -1, 10);
                    p.addEffect(effect);
                }
            }
        }
    }
}
