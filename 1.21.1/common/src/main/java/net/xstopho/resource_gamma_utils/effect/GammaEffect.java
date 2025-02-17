package net.xstopho.resource_gamma_utils.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.xstopho.resource_gamma_utils.GammaConstants;

public class GammaEffect extends MobEffect {
    private final String key;

    public GammaEffect(String key, MobEffectCategory category, int color) {
        super(category, color);
        this.key = key;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    @Override
    protected String getOrCreateDescriptionId() {
        return String.format("effect.%s.%s", GammaConstants.MOD_ID, this.key);
    }

    public ResourceLocation getLocation() {
        return GammaConstants.of(this.key);
    }
}
