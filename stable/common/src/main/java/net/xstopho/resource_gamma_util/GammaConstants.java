package net.xstopho.resource_gamma_util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.xstopho.resource_gamma_util.service.GammaService;
import org.lwjgl.glfw.GLFW;

public class GammaConstants {
    public static final String MOD_ID = "resource_gamma_util";

    private static final String CATEGORY = MOD_ID + ".key.category";
    public static final KeyMapping TOGGLE = new KeyMapping(MOD_ID + ".key.toggle", GLFW.GLFW_KEY_H, CATEGORY);

    public static void useHotkey(Minecraft client) {
        boolean shader = GammaService.isShaderActive();

        if (TOGGLE.consumeClick()) {
            updateVision(client, shader);
        }
    }

    private static void updateVision(Minecraft client, boolean shader) {
        if (!shader || client.options.gamma().get() == 1500) {
            client.options.gamma().set(1500.0);
        }

        if (client.player == null) return;
        LocalPlayer p = client.player;

        if (p.hasEffect(MobEffects.NIGHT_VISION)) p.removeEffect(MobEffects.NIGHT_VISION);
        else if (shader) p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
    }
}
