package net.xstopho.resource_gamma_utils.config;

import net.xstopho.resourceconfigapi.annotations.Config;
import net.xstopho.resourceconfigapi.annotations.ConfigEntry;
import net.xstopho.resourceconfigapi.annotations.RangedEntry;
import net.xstopho.resourceconfigapi.api.ConfigType;

@Config(fileName = "nightvision", type = ConfigType.CLIENT)
public class NightVisionConfig {

    @ConfigEntry
    public static boolean nightVisionEnabled = false;

    @ConfigEntry
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

    public void setStatus(boolean status) {
        nightVisionEnabled = status;
    }

    public boolean isEnabled() {
        return nightVisionEnabled;
    }

    public double getStrength() {
        return nightVisionStrength;
    }

    public void setStrength(double newValue) {
        nightVisionStrength = newValue;
    }

    public int getToggledStrength() {
        return toggledNightVision;
    }

    public void setToggledStrength(int newValue) {
        toggledNightVision = newValue;
    }

    public boolean isToggleUpdateEnabled() {
        return updateToggle;
    }

    public int getStepStrength() {
        return nightVisionStep;
    }

    public boolean isSmoothTransitionEnabled() {
        return smoothTransition;
    }

    public void setSmoothTransitionStatus(boolean status) {
        smoothTransition = status;
    }

    public double getTransitionSpeed(boolean dynamic) {
        return dynamic ? dynamicTransitionSpeed : transitionSpeed;
    }

    public boolean isLimiterEnabled() {
        return limitCheck;
    }

    public int getMaximumStrength() {
        return maxNightVisionLimiter;
    }

    public int getMinimumStrength() {
        return minNightVisionLimiter;
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

    public boolean isDimensionPreferenceEnabled() {
        return enableDimensionPreference;
    }

    public int getOverworldPreference() {
        return overworldPreference;
    }

    public int getNetherPreference() {
        return netherPreference;
    }

    public int getEndPreference() {
        return endPreference;
    }

    public boolean isDynamicNightVisionEnabled() {
        return enableDynamicNightVision;
    }

    public void setDynamicNightVisionStatus(boolean status) {
        enableDynamicNightVision = status;
    }

    public int getMinDynamicStrength() {
        return minNightVision;
    }

    public int getMaxDynamicStrength() {
        return maxNightVision;
    }

    public int getDynamicAveragingLightRange() {
        return averagingLightRange;
    }

    public float getSkyBrightnessOverride() {
        return skyBrightnessOverride / 100f;
    }
}
