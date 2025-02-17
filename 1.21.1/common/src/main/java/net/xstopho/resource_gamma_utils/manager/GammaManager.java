package net.xstopho.resource_gamma_utils.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.xstopho.resource_gamma_utils.config.GammaConfig;
import net.xstopho.resource_gamma_utils.util.LightLevelUtil;
import net.xstopho.resource_gamma_utils.util.MessageUtil;

import java.util.Timer;
import java.util.TimerTask;

public class GammaManager {
    private static final Minecraft client = Minecraft.getInstance();
    private static final OptionInstance<Double> gamma = client.options.gamma();
    private static Timer transitionTimer = null;
    private static double dynamicGammaTarget = Double.NaN;

    public static void toggleGamma() {
        double newValue = gamma.get() == GammaConfig.getDefaultStrength() ? GammaConfig.getToggledStrength() : GammaConfig.getDefaultStrength();
        dynamicGammaTarget = Double.NaN;
        setGamma(newValue, true, true);
    }

    public static double getGamma() {
        return gamma.get();
    }

    public static int getGammaPercentage() {
        return (int) Math.round(gamma.get() * 100);
    }

    public static void setGamma(double newValue, boolean smoothTransition, boolean showMessage) {
        if (GammaConfig.isDynamicGammaEnabled() && showMessage) {
            Component message = Component.translatable("text.resource_gamma_utils.message.incompatibleWithDynamicGamma");
            MessageUtil.sendMessage(message);
        }

        setGamma(newValue, smoothTransition, showMessage, false);
    }

    public static void setGamma(double newValue, boolean smoothTransition, boolean showMessage, boolean dynamic) {
        if (transitionTimer != null) transitionTimer.cancel();

        if (GammaConfig.isLimiterEnabled() && GammaConfig.getMaximumStrength() > GammaConfig.getMinimumStrength()) {
            newValue = Math.clamp(newValue, GammaConfig.getMinimumStrength(), GammaConfig.getMaximumStrength());
        }

        if (smoothTransition && (GammaConfig.isSmoothTransitionEnabled() || dynamic)) {
            double changeValuePerTick = GammaConfig.getTransitionSpeed(dynamic) / 100;
            if (newValue < gamma.get()) {
                changeValuePerTick *= -1;
            }
            startTransitionTimer(newValue, changeValuePerTick, showMessage);
        } else {
            gamma.set(newValue);
            MobEffectManager.updateGammaEffect();
            if (showMessage) MessageUtil.showGammaHudMessage();
        }

        if (GammaConfig.isToggleUpdateEnabled() && newValue != GammaConfig.getDefaultStrength()) {
            GammaConfig.setToggledStrength(newValue);
        }
    }

    public static void setDynamicGamma() {
        if (!GammaConfig.isDynamicGammaEnabled()) {
            return;
        }

        double lightLevel = LightLevelUtil.getAverageLightLevel(GammaConfig.getDynamicAveragingLightRange(), GammaConfig.getSkyBrightnessOverride());
        double step = (GammaConfig.getMaxDynamicStrength() - GammaConfig.getMinDynamicStrength()) / 15.0;
        double target = (GammaConfig.getMinDynamicStrength() + step * (15 - lightLevel));
        if (dynamicGammaTarget != target) {
            dynamicGammaTarget = target;
            setGamma(target, true, false, true);
        }
    }

    public static void increaseGamma(double value) {
        double newValue = gamma.get();
        newValue += value == 0 ? GammaConfig.getStepStrength() : value;
        setGamma(newValue, false, true);
    }

    public static void decreaseGamma(double value) {
        double newValue = gamma.get();
        newValue -= value == 0 ? GammaConfig.getStepStrength() : value;
        setGamma(newValue, false, true);
    }

    public static void minGamma() {
        setGamma(GammaConfig.getMinimumStrength(), true, true);
    }

    public static void maxGamma() {
        setGamma(GammaConfig.getMaximumStrength(), true, true);
    }

    public static void setDimensionPreference() {
        if (client.level == null || !GammaConfig.isDimensionPreferenceEnabled()) {
            return;
        }

        ResourceKey<Level> dimension = client.level.dimension();
        if (dimension.equals(Level.OVERWORLD)) {
            setGamma(GammaConfig.getOverworldPreference(), false, false);
        }
        else if (dimension.equals(Level.NETHER)) {
            setGamma(GammaConfig.getNetherPreference(), false, false);
        }
        else if (dimension.equals(Level.END)) {
            setGamma(GammaConfig.getEndPreference(), false, false);
        }
    }

    public static void toggleDynamicGamma() {
        boolean newStatus = !GammaConfig.isDynamicGammaEnabled();
        GammaConfig.setDynamicGammaStatus(newStatus);
        Component message = Component.translatable("text.resource_gamma_utils.message.dynamicGamma" + (newStatus ? "On" : "Off"));
        MessageUtil.sendMessage(message);
    }

    public static void toggleStatusEffect() {
        boolean newStatus = !GammaConfig.isStatusEffectEnabled();
        GammaConfig.setStatusEffectStatus(newStatus);
        MobEffectManager.updateGammaEffect();
        Component message = Component.translatable("text.resource_gamma_utils.message.statusEffectGamma" + (newStatus ? "On" : "Off"));
        MessageUtil.sendMessage(message);
    }

    public static void toggleSmoothTransition() {
        boolean newStatus = !GammaConfig.isSmoothTransitionEnabled();
        GammaConfig.setSmoothTransitionStatus(newStatus);
        Component message = Component.translatable("text.resource_gamma_utils.message.transitionGamma" + (newStatus ? "On" : "Off"));
        MessageUtil.sendMessage(message);
    }

    private static void startTransitionTimer(double newValue, double valueChangePerTick, boolean showMessage) {
        transitionTimer = new Timer();
        transitionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double nextValue = gamma.get() + valueChangePerTick;
                if ((valueChangePerTick > 0 && nextValue >= newValue) ||
                        (valueChangePerTick < 0 && nextValue <= newValue)) {
                    transitionTimer.cancel();
                    gamma.set(newValue);
                    MobEffectManager.updateGammaEffect();
                }
                else {
                    gamma.set(nextValue);
                }

                if (showMessage) {
                    MessageUtil.showGammaHudMessage();
                }
            }
        }, 0, 10);
    }
}
