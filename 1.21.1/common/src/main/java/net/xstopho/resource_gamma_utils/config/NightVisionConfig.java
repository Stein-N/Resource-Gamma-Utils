package net.xstopho.resource_gamma_utils.config;

import net.xstopho.resourceconfigapi.annotations.Config;
import net.xstopho.resourceconfigapi.annotations.ConfigEntry;
import net.xstopho.resourceconfigapi.annotations.RangedEntry;
import net.xstopho.resourceconfigapi.api.ConfigType;

@Config(fileName = "nightvision", type = ConfigType.CLIENT)
public class NightVisionConfig {

    public static boolean nightVisionEnabled = false;

    public static double nightVisionStrength = 100;

    @ConfigEntry
    public static int toggledNightVision = 100;

    @ConfigEntry
    public static boolean updateToggle = false;

    @ConfigEntry
    public static int nightVisionStep = 2;

    @ConfigEntry
    public static boolean showStatusEffect = false;

    @ConfigEntry
    public static boolean resetOnClose = false;

    @ConfigEntry
    public static boolean showHudMessage = true;

    @ConfigEntry(category = "Transition")
    public static boolean smoothTransition = false;

    @ConfigEntry(category = "Transition")
    public static int transitionSpeed = 200;

    @ConfigEntry(category = "Dynamic Night Vision")
    public static boolean enableDynamicNightVision = false;

    @ConfigEntry(category = "Dynamic Night Vision")
    public static int minNightVision = 0;

    @ConfigEntry(category = "Dynamic Night Vision")
    public static int maxNightVision = 100;

    @ConfigEntry(category = "Dynamic Night Vision")
    public static int dynamicTransitionSpeed = 15;

    @ConfigEntry(category = "Dynamic Night Vision")
    @RangedEntry(minValue = 0, maxValue = 16)
    public static int averagingLightRange = 8;

    @ConfigEntry(category = "Dynamic Night Vision")
    @RangedEntry(minValue = 0, maxValue = 100)
    public static int skyBrightnessOverride = 0;

    @ConfigEntry(category = "Dimension Preference")
    public static boolean enableDimensionPreference = false;

    @ConfigEntry(category = "Dimension Preference")
    public static int overworldPreference = 100;

    @ConfigEntry(category = "Dimension Preference")
    public static int netherPreference = 100;

    @ConfigEntry(category = "Dimension Preference")
    public static int endPreference = 100;

    @ConfigEntry(category = "Limiter")
    public static boolean limitCheck = true;

    @ConfigEntry(category = "Limiter")
    public static int minNightVisionLimiter = 0;

    @ConfigEntry(category = "Limiter")
    public static int maxNightVisionLimiter = 100;

    public static void setStatus(boolean status) {
        nightVisionEnabled = status;
    }

    public static boolean isEnabled() {
        return nightVisionEnabled;
    }

    public static double getStrength() {
        return nightVisionStrength;
    }

    public static void setStrength(double newValue) {
        nightVisionStrength = newValue;
    }

    public static int getToggledStrength() {
        return toggledNightVision;
    }

    public static void setToggledStrength(int newValue) {
        toggledNightVision = newValue;
    }

    public static boolean isToggleUpdateEnabled() {
        return updateToggle;
    }

    public static int getStepStrength() {
        return nightVisionStep;
    }

    public static boolean isSmoothTransitionEnabled() {
        return smoothTransition;
    }

    public static void setSmoothTransitionStatus(boolean status) {
        smoothTransition = status;
    }

    public static double getTransitionSpeed(boolean dynamic) {
        return dynamic ? dynamicTransitionSpeed : transitionSpeed;
    }

    public static boolean isLimiterEnabled() {
        return limitCheck;
    }

    public static int getMaximumStrength() {
        return maxNightVisionLimiter;
    }

    public static int getMinimumStrength() {
        return minNightVisionLimiter;
    }

    public static boolean isStatusEffectEnabled() {
        return showStatusEffect;
    }

    public static void setStatusEffectStatus(boolean status) {
        showStatusEffect = status;
    }

    public static boolean isResetOnCloseEnabled() {
        return resetOnClose;
    }

    public static boolean isDimensionPreferenceEnabled() {
        return enableDimensionPreference;
    }

    public static int getOverworldPreference() {
        return overworldPreference;
    }

    public static int getNetherPreference() {
        return netherPreference;
    }

    public static int getEndPreference() {
        return endPreference;
    }

    public static boolean isDynamicNightVisionEnabled() {
        return enableDynamicNightVision;
    }

    public static void setDynamicNightVisionStatus(boolean status) {
        enableDynamicNightVision = status;
    }

    public static int getMinDynamicStrength() {
        return minNightVision;
    }

    public static int getMaxDynamicStrength() {
        return maxNightVision;
    }

    public static int getDynamicAveragingLightRange() {
        return averagingLightRange;
    }

    public static float getSkyBrightnessOverride() {
        return skyBrightnessOverride / 100f;
    }

    public static boolean showHudMessage() {
        return showHudMessage;
    }
}
