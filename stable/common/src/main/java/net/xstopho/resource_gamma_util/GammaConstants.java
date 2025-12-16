package net.xstopho.resource_gamma_util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.xstopho.resource_gamma_util.service.GammaService;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaConstants {
    public static final String MOD_ID = "resource_gamma_util";
    public static final String MOD_NAME = "Resource Gamma Util";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static final String CATEGORY = MOD_ID + ".key.category";
    public static final KeyMapping TOGGLE = new KeyMapping(MOD_ID + ".key.toggle", GLFW.GLFW_KEY_H, CATEGORY);

    public static final Double customGamma = 1500D;
    public static boolean gammaEnabled = false;

    public static void useHotkey(Minecraft client) {
        if (TOGGLE.consumeClick()) {
            boolean shader = GammaService.isShaderActive();
            LocalPlayer p = client.player;

            if (shader && gammaEnabled) {
                gammaEnabled = false;
                p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
            }

            if (shader) {
                if (p.hasEffect(MobEffects.NIGHT_VISION)) p.removeEffect(MobEffects.NIGHT_VISION);
                else p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
            } else gammaEnabled = !gammaEnabled;
        }
    }
}
