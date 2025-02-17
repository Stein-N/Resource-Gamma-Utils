package net.xstopho.resource_gamma_utils.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import net.xstopho.resource_gamma_utils.util.LightLevelUtil;
import net.xstopho.resource_gamma_utils.util.MessageUtil;

import java.util.Timer;
import java.util.TimerTask;

public class NightVisionManager {
    private static final Minecraft client = Minecraft.getInstance();
    private static Timer transitionTimer = null;
    private static double dynamicNightVisionTarget = Double.NaN;

    private NightVisionManager() {
    }

    public static int getNightVisionPercentage() {
        return (int) Math.round(NightVisionConfig.getStrength());
    }

    public static void toggleNightVision() {
        if (NightVisionConfig.isEnabled()) {
            disableNightVision();
        }
        else {
            enableNightVision();
        }
    }

    public static void enableAndOrSetNightVision(int newValue) {
        if (NightVisionConfig.isEnabled()) {
            NightVisionManager.setNightVision(newValue, true, false, true);
        }
        else {
            enableNightVision(newValue);
        }
    }

    public static void enableNightVision() {
        enableNightVision(NightVisionConfig.getToggledStrength());
    }

    private static void enableNightVision(int newValue) {
        NightVisionManager.setNightVision(0, false, false, false);
        setNightVisionStatus(true);
        if (NightVisionConfig.isDynamicNightVisionEnabled()) {
            MessageUtil.showDynamicNightVisionHudMessage(true);
            dynamicNightVisionTarget = Double.NaN;
            setDynamicNightVision();
        }
        else {
            NightVisionManager.setNightVision(newValue, true, false, true);
        }
    }

    public static void disableNightVision() {
        if (NightVisionConfig.isDynamicNightVisionEnabled()) {
            MessageUtil.showDynamicNightVisionHudMessage(false);
            NightVisionManager.setNightVision(0, true, true, false, true);
        }
        else {
            NightVisionManager.setNightVision(0, true, true, true);
        }
    }

    public static void increaseNightVision(int value) {
        double newValue = NightVisionConfig.getStrength();
        newValue += value == 0 ? NightVisionConfig.getStepStrength() : value;
        setNightVision(newValue, false, false, true);
    }

    public static void decreaseNightVision(int value) {
        double newValue = NightVisionConfig.getStrength();
        newValue -= value == 0 ? NightVisionConfig.getStepStrength() : value;
        setNightVision(newValue, false, false, true);
    }

    public static void setDimensionPreference() {
        if (client.level == null || !NightVisionConfig.isDimensionPreferenceEnabled()) {
            return;
        }

        ResourceKey<Level> dimension = client.level.dimension();
        if (dimension.equals(Level.OVERWORLD)) {
            setNightVision(NightVisionConfig.getOverworldPreference(), false, false, false);
        }
        else if (dimension.equals(Level.NETHER)) {
            setNightVision(NightVisionConfig.getNetherPreference(), false, false, false);
        }
        else if (dimension.equals(Level.END)) {
            setNightVision(NightVisionConfig.getEndPreference(), false, false, false);
        }
    }

    public static void setDynamicNightVision() {
        if (!NightVisionConfig.isDynamicNightVisionEnabled()) {
            return;
        }

        double lightLevel = LightLevelUtil.getAverageLightLevel(NightVisionConfig.getDynamicAveragingLightRange(), NightVisionConfig.getSkyBrightnessOverride());
        double step = (NightVisionConfig.getMaxDynamicStrength() - NightVisionConfig.getMinDynamicStrength()) / 15.0;
        double target = (NightVisionConfig.getMinDynamicStrength() + step * (15 - lightLevel));
        if (dynamicNightVisionTarget != target) {
            dynamicNightVisionTarget = target;
            setNightVision(target, true, false, false, true);
        }
    }

    public static void setNightVision(double newValue, boolean smoothTransition, boolean disable, boolean showMessage) {
        if (NightVisionConfig.isDynamicNightVisionEnabled()) {
            if (showMessage) {
                Component message = Component.translatable("text.resource_gamma_utils.message.incompatibleWithDynamicNightVision");
                MessageUtil.sendMessage(message);
            }
            return;
        }

        setNightVision(newValue, smoothTransition, disable, showMessage, false);
    }

    private static void setNightVision(double newValue, boolean smoothTransition, boolean disable, boolean showMessage, boolean dynamic) {
        if (transitionTimer != null) {
            transitionTimer.cancel();
        }

        if (NightVisionConfig.isLimiterEnabled() && NightVisionConfig.getMaximumStrength() > NightVisionConfig.getMinimumStrength()) {
            newValue = Math.clamp(newValue, NightVisionConfig.getMinimumStrength(), NightVisionConfig.getMaximumStrength());
        }

        if (smoothTransition && (NightVisionConfig.isSmoothTransitionEnabled() || dynamic)) {
            double valueChangePerTick = NightVisionConfig.getTransitionSpeed(dynamic) / 100;
            if (newValue < NightVisionConfig.getStrength()) {
                valueChangePerTick *= -1;
            }
            startTransitionTimer(newValue, valueChangePerTick, disable, showMessage);
        }
        else {
            NightVisionConfig.setStrength(newValue);
            if (disable) {
                setNightVisionStatus(false);
            }
            if (showMessage) {
                MessageUtil.showNightVisionStatusHudMessage();
            }
        }

        if (NightVisionConfig.isToggleUpdateEnabled() && newValue != 0) {
            NightVisionConfig.setToggledStrength((int)Math.round(newValue));
        }
    }

    protected static void toggleDynamicNightVision() {
        boolean newStatus = !NightVisionConfig.isDynamicNightVisionEnabled();
        NightVisionConfig.setDynamicNightVisionStatus(newStatus);
        Component message = Component.translatable("text.resource_gamma_utils.message.dynamicNightVision" + (newStatus ? "On" : "Off"));
        MessageUtil.sendMessage(message);
    }

    protected static void toggleStatusEffect() {
        boolean newStatus = !NightVisionConfig.isStatusEffectEnabled();
        NightVisionConfig.setStatusEffectStatus(newStatus);
        MobEffectManager.updateNightVisionEffect();
        Component message = Component.translatable("text.resource_gamma_utils.message.statusEffectNightVision" + (newStatus ? "On" : "Off"));
        MessageUtil.sendMessage(message);
    }

    protected static void toggleSmoothTransition() {
        boolean newStatus = !NightVisionConfig.isSmoothTransitionEnabled();
        NightVisionConfig.setSmoothTransitionStatus(newStatus);
        Component message = Component.translatable("text.resource_gamma_utils.message.transitionNightVision" + (newStatus ? "On" : "Off"));
        MessageUtil.sendMessage(message);
    }

    private static void setNightVisionStatus(boolean status) {
        NightVisionConfig.setStatus(status);
        MobEffectManager.updateNightVisionEffect();
    }

    private static void startTransitionTimer(double newValue, double valueChangePerTick, boolean disable, boolean showMessage) {
        transitionTimer = new Timer();
        transitionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double nextValue = NightVisionConfig.getStrength() + valueChangePerTick;
                if ((valueChangePerTick > 0 && nextValue >= newValue) ||
                        (valueChangePerTick < 0 && nextValue <= newValue)) {
                    transitionTimer.cancel();
                    NightVisionConfig.setStrength(newValue);
                    if (disable) {
                        setNightVisionStatus(false);
                    }
                }
                else {
                    NightVisionConfig.setStrength(nextValue);
                }

                if (showMessage) {
                    MessageUtil.showNightVisionStatusHudMessage();
                }
            }
        }, 0, 10);
    }
}