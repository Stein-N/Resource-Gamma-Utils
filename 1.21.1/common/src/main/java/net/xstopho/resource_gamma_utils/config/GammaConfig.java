package net.xstopho.resource_gamma_utils.config;

import net.xstopho.resourceconfigapi.annotations.Config;
import net.xstopho.resourceconfigapi.annotations.ConfigEntry;
import net.xstopho.resourceconfigapi.annotations.RangedEntry;
import net.xstopho.resourceconfigapi.api.ConfigType;

@Config(fileName = "gamma", type = ConfigType.CLIENT)
public class GammaConfig {

    @ConfigEntry
    public static int defaultGamma = 100;

    @ConfigEntry
    public static int toggledGamma = 1500;

    @ConfigEntry
    public static boolean updateToggle = false;

    @ConfigEntry
    public static int gammaStep = 10;

    @ConfigEntry
    public static boolean showStatusEffect = false;

    @ConfigEntry
    public static boolean resetOnClose = false;

    @ConfigEntry(category = "Transition")
    public static boolean smoothTransition = false;

    @ConfigEntry(category = "Transition")
    public static int transitionSpeed = 3000;

    @ConfigEntry(category = "Dynamic Gamma")
    public static boolean enableDynamicGamma = false;

    @ConfigEntry(category = "Dynamic Gamma")
    public static int minGamma = 100;

    @ConfigEntry(category = "Dynamic Gamma")
    public static int maxGamma = 1000;

    @ConfigEntry(category = "Dynamic Gamma")
    public static int dynamicTransitionSpeed = 200;

    @ConfigEntry(category = "Dynamic Gamma")
    @RangedEntry(minValue = 0, maxValue = 16)
    public static int averagingLightRange = 8;

    @ConfigEntry(category = "Dynamic Gamma")
    @RangedEntry(minValue = 0, maxValue = 100)
    public static int skyBrightnessOverride = 0;

    @ConfigEntry(category = "Dimension Preference")
    public static boolean enableDimensionPreference = false;

    @ConfigEntry(category = "Dimension Preference")
    public static int overworldPreference = 1500;

    @ConfigEntry(category = "Dimension Preference")
    public static int netherPreference = 1500;

    @ConfigEntry(category = "Dimension Preference")
    public static int endPreference = 1500;

    @ConfigEntry(category = "Limiter")
    public static boolean limitCheck = true;

    @ConfigEntry(category = "Limiter")
    public static int minGammaLimiter = -750;

    @ConfigEntry(category = "Limiter")
    public static int maxGammaLimiter = 1500;

    public double getDefaultStrength() {
        return defaultGamma / 100.0;
    }

    public double getToggledStrength() {
        return toggledGamma / 100.0;
    }

    public void setToggledStrength(double newValue) {
        toggledGamma = (int)Math.round(newValue * 100);
    }

    public boolean isToggleUpdateEnabled() {
        return updateToggle;
    }

    public double getStepStrength() {
        return gammaStep / 100.0;
    }

    public boolean isSmoothTransitionEnabled() {
        return smoothTransition;
    }

    public void setSmoothTransitionStatus(boolean status) {
        smoothTransition = status;
    }

    public double getTransitionSpeed(boolean dynamic) {
        return (dynamic ? dynamicTransitionSpeed : transitionSpeed) / 100.0;
    }

    public boolean isStatusEffectEnabled() {
        return showStatusEffect;
    }

    public void setStatusEffectStatus(boolean status) {
        showStatusEffect = status;
    }

    public boolean isResetOnCloseEnabled() {
        return resetOnClose;
    }

    public boolean isLimiterEnabled() {
        return limitCheck;
    }

    public double getMinimumStrength() {
        return minGammaLimiter / 100.0;
    }

    public double getMaximumStrength() {
        return maxGammaLimiter / 100.0;
    }

    public boolean isDimensionPreferenceEnabled() {
        return enableDimensionPreference;
    }

    public double getOverworldPreference() {
        return overworldPreference / 100.0;
    }

    public double getNetherPreference() {
        return netherPreference / 100.0;
    }

    public double getEndPreference() {
        return endPreference / 100.0;
    }

    public boolean isDynamicGammaEnabled() {
        return enableDynamicGamma;
    }

    public void setDynamicGammaStatus(boolean status) {
        enableDynamicGamma = status;
    }

    public double getMinDynamicStrength() {
        return minGamma / 100.0;
    }

    public double getMaxDynamicStrength() {
        return maxGamma / 100.0;
    }

    public int getDynamicAveragingLightRange() {
        return averagingLightRange;
    }

    public float getSkyBrightnessOverride() {
        return skyBrightnessOverride / 100f;
    }
}
