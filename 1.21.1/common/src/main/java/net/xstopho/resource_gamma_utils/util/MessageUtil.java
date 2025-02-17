package net.xstopho.resource_gamma_utils.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.xstopho.resource_gamma_utils.config.GammaConfig;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import net.xstopho.resource_gamma_utils.manager.GammaManager;
import net.xstopho.resource_gamma_utils.manager.NightVisionManager;

public class MessageUtil {
    private static final Minecraft client = Minecraft.getInstance();
    private static final LocalPlayer player = Minecraft.getInstance().player;

    public static void sendMessage(Component message) {
        if (player == null) return;
        player.sendSystemMessage(message);
    }

    public static void showGammaHudMessage() {
        if (!GammaConfig.showHudMessage()) return;

        int gamma = GammaManager.getGammaPercentage();
        MutableComponent message = Component.translatable("text.resource_gamma_utils.message.gammaPercentage", gamma);

        int color;
        if (gamma < 0) color = 0xAA0000;
        else if (gamma > 100) color = 0xFFAA00;
        else color = 43520;

        message.withColor(color);
        client.gui.setOverlayMessage(message, false);
    }

    public static void showDynamicNightVisionHudMessage(boolean enabled) {
        MutableComponent message;
        if (enabled) {
            message = Component.translatable("text.resource_gamma_utils.message.dynamicNightVisionEnabled");
            message.withColor(43520);
        } else {
            message = Component.translatable("text.resource_gamma_utils.message.dynamicNightVisionDisabled");
            message.withColor(0xAA0000);
        }

        client.gui.setOverlayMessage(message, false);
    }

    public static void showNightVisionStatusHudMessage() {
        if (!NightVisionConfig.showHudMessage()) return;

        if (NightVisionConfig.isEnabled()) {
            showNightVisionStrengthHudMessage();
        } else {
            MutableComponent message = Component.translatable("text.resource_gamma_utils.message.nightVisionDisabled");
            message.withColor(0xAA0000);
            client.gui.setOverlayMessage(message, false);
        }
    }

    public static void showNightVisionStrengthHudMessage() {
        int nightVision = NightVisionManager.getNightVisionPercentage();
        MutableComponent message = Component.translatable("text.resource_gamma_utils.message.nightVisionPercentage", nightVision);

        int color;
        if (nightVision < 0) color = 0xAA0000;
        else if (nightVision > 100) color = 0xFFAA00;
        else color = 43520;

        message.withColor(color);
        client.gui.setOverlayMessage(message, false);
    }
}
